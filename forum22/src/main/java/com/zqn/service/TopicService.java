package com.zqn.service;

import com.zqn.dao.NodeDao;
import com.zqn.dao.ReplyDao;
import com.zqn.dao.TopicDao;
import com.zqn.dao.UserDao;
import com.zqn.entity.Node;
import com.zqn.entity.Reply;
import com.zqn.entity.Topic;
import com.zqn.entity.User;
import com.zqn.exception.ServiceException;
import com.zqn.util.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by dell on 2016/12/21.
 */
public class TopicService {
    private UserDao userDao = new UserDao();
    private NodeDao nodeDao = new NodeDao();
    private TopicDao topicDao = new TopicDao();
    private ReplyDao replyDao = new ReplyDao();
    public List<Node> findAllNode() {
       return nodeDao.findAllnode();
    }

    public Topic addNewTopic(String title, String content, String nodeid,Integer userid) {
        Topic topic =new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNodeid(Integer.valueOf(nodeid));
        topic.setUserid(userid);

        Integer topicId = topicDao.save(topic);

        if(topicId != null){
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
            node.setTopicnum(node.getTopicnum()+1);
            nodeDao.update(node);
        }else {
            System.out.println("发帖失败");
        }

        topic.setId(topicId);




        return topic;


    }

    public Topic findByTopicId(String topicId) {
        if(StringUtils.isNumeric(topicId)){
            Topic topic = topicDao.findByTopicId(topicId);
            if(topic !=null){
                User user = userDao.findById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
                topic.setUser(user);
                return topic;
            }else{
                throw new ServiceException("该帖不存在");

            }
        }else{
            throw new ServiceException("参数错误");

        }

    }


    public void addNewReply(String topicid, String content, User user) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setTopicid(Integer.valueOf(topicid));
        reply.setUserid(user.getId());

        replyDao.addNewReply(reply);



    }
}
