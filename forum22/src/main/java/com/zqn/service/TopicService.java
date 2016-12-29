package com.zqn.service;

import com.google.common.collect.Maps;
import com.zqn.dao.*;
import com.zqn.entitiy.*;
import com.zqn.exception.ServiceException;
import com.zqn.util.Config;
import com.zqn.util.Page;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class TopicService {
    NodeDao nodeDao=new NodeDao();
    TopicDao topicDao=new TopicDao();
    UserDao userDao=new UserDao();
    ReplyDao replyDao=new ReplyDao();
    FavDao favDao=new FavDao();
    NotifyDao notifyDao=new NotifyDao();
    public List<Node> findAllNode() {
        List<Node> nodeList=nodeDao.findAllNodes();
        return nodeList;
    }

    public Topic addNewTopic(String title, String content, Integer nodeid, Integer userid) {
        //封装topic对象
        Topic topic=new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNodeid(nodeid);
        topic.setUserid(userid);

        //暂时设置最后回复时间为当前时间
        topic.setLastreplytime(new Timestamp(new Date().getTime()));
        Integer topicid=topicDao.save(topic);
        topic.setId(topicid);

        //更新node表的topicnum
      Node node=nodeDao.findNodeById(nodeid);
      if(node!=null){
          node.setTopicnum(node.getTopicnum()+1);
          nodeDao.update(node);

      }else {
          throw new ServiceException("节点不存在");
      }
        return topic;
    }

    public Topic findTopicById(String topicid) {
        System.out.println(topicid);
        if(StringUtils.isNumeric(topicid)){
            Topic topic=topicDao.findTopicById(topicid);
            System.out.println(topic);
            if(topic!=null){
                //通过topic对象的userid、nodeid 获取user和node对象,并set到topic对象中
                User user=userDao.findById(topic.getUserid());
                Node node=nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
               topic.setUser(user);
               topic.setNode(node);

                //更新topic表中的clicknum字段
                topic.setClicknum(topic.getClicknum()+1);
                topicDao.update(topic);
               return topic;

            }else {
                throw new ServiceException("该帖不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }

    public void addNewReply(String topicid, User user, String content) {
        //新增回复到t_reply表
        Reply reply=new Reply();
        reply.setContent(content);
        reply.setUserid(user.getId());
        reply.setTopicid(Integer.valueOf(topicid));
        replyDao.addReply(reply);

        //更新t_topic表中的replynum 和 lastreplytime字段
        Topic topic=topicDao.findTopicById(topicid);
        if(topic!=null){
            topic.setReplynum(topic.getReplynum()+1);
            topic.setLastreplytime(new Timestamp(new Date().getTime()));
            topicDao.update(topic);
        }else {
            throw new ServiceException("回复的主题不存在或被删除");
        }

        //新增回复通知
        if (!user.getId().equals(topic.getUserid())){
            Notify notify = new Notify();
            notify.setUserid(topic.getUserid());
            notify.setContent("您的主题 <a href=\"/topicDetail?topicid="+topic.getId()+"\">["+ topic.getTitle()+"] </a> 有了新的回复,请查看.");
           // notify.setContent("ok?");
            notify.setState(Notify.NOTIFY_STATE_UNREAD);
            notifyDao.save(notify);
        }

    }

    public List<Reply> findReplyListByTopicid(String topicid){
        return replyDao.findReplyListByTopicid(topicid);
    }

    public void updateTopicById(String title, String content, String nodeid, String topicid) {
        Topic topic=topicDao.findTopicById(topicid);
        if(topic.isEdit()){
            topic.setTitle(title);
            topic.setContent(content);
            topic.setNodeid(Integer.valueOf(nodeid));
            topicDao.update(topic);
        }else {
            throw new ServiceException("该帖已经不可编辑");
        }

    }

    public Fav findFavByUseridAndTopicid(String topicid, User user) {
        return favDao.findFavByUseridAndTopicid(Integer.valueOf(topicid),user.getId());
    }

    public void favTopic(String topicid, User user) {
        Fav fav=new Fav();
        fav.setTopicid(Integer.valueOf(topicid));
        fav.setUserid(user.getId());
        favDao.addFav(fav);

        //topic表收藏字段 +1
        Topic topic=topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum()+1);
        topicDao.update(topic);
    }

    public void unfavTopic(String topicid, User user) {
        favDao.deleteFav(topicid,user.getId());

        //topic表收藏字段 -1
        Topic topic=topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum()-1);
        topicDao.update(topic);
    }

    public void updateTopic(Topic topic) {
        topicDao.update(topic);
    }

    public Page<Topic> findAllTopics(String nodeid, Integer pageNo) {
        HashMap<String,Object> map= Maps.newHashMap();
        int count=0;
        if(StringUtils.isEmpty(nodeid)){
            count=topicDao.count();
        }else {
            count=topicDao.count(nodeid);
        }
         /*Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
        count = node.getTopicnum();*/
         Page<Topic> topicPage=new Page<>(count,pageNo);
         map.put("nodeid",nodeid);
         map.put("start",topicPage.getStart());
         map.put("pageSize",topicPage.getPageSize());

         List<Topic> topicList=topicDao.findAll(map);
         topicPage.setItems(topicList);
         return topicPage;

    }
}
