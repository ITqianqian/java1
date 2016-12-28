package com.zqn.dao;


import com.zqn.entitiy.Topic;
import com.zqn.entitiy.User;
import com.zqn.util.Config;
import com.zqn.util.DbHelp;
import com.zqn.util.StringUtils;
import org.apache.commons.dbutils.BasicRowProcessor;

import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TopicDao {
    public Integer save(Topic topic) {
        String sql="insert into t_topic (title,content,nodeid,userid) values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getNodeid(),topic.getUserid());
    }

    public Topic findTopicById(String topicid) {
        String sql="select * from t_topic where id=?";
        return DbHelp.query(sql,new BeanHandler<>(Topic.class),topicid);
    }

    public void update(Topic topic) {
        String sql="update t_topic set title=?,content=?,clicknum=?,favnum=?,thankyounum=?,replynum=?,lastreplytime=?,nodeid=?,userid=? where id=?";
        DbHelp.update(sql,topic.getTitle(),topic.getContent(), topic.getClicknum(),
                topic.getFavnum(),topic.getThankyounum(),topic.getReplynum(),topic.getLastreplytime(),
                topic.getNodeid(),topic.getUserid(),topic.getId());
    }

    public int count() {
        String sql="select count(*) from t_topic";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public int count(String nodeid) {
        String sql="select count(*) from t_topic where nodeid=?";
        return DbHelp.query(sql,new ScalarHandler<Long>(),nodeid).intValue();
    }

    public List<Topic> findAll(HashMap<String,Object> map) {
        String sql="SELECT tu.username,tu.avatar,tt.* FROM t_topic tt,t_user tu WHERE tt.userid = tu.id ";
        String nodeid=map.get("nodeid")==null ?null:String.valueOf(map.get("nodeid"));
        String where="";
       List<Object> array=new ArrayList<>();
       if (StringUtils.isNotEmpty(nodeid)){
           where+="and nodeid=?";
           array.add(nodeid);
       }
       where+=" order by tt.lastreplytime desc limit ?,?";
       array.add(map.get("start"));
       array.add(map.get("pageSize"));
       sql +=where;

       return DbHelp.query(sql, new AbstractListHandler<Topic>() {
           @Override
           protected Topic handleRow(ResultSet rs) throws SQLException {
               Topic topic=new BasicRowProcessor().toBean(rs,Topic.class);
               User user=new User();
               user.setId(rs.getInt("userid"));
               user.setUsername(rs.getString("username"));
               user.setAvatar(Config.get("qiniu.domain")+rs.getString("avatar"));
               topic.setUser(user);
               return topic;
           }
       },array.toArray());
    }
}
