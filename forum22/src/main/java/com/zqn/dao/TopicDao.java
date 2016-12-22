package com.zqn.dao;

import com.zqn.entity.Node;
import com.zqn.entity.Topic;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by dell on 2016/12/21.
 */
public class TopicDao {
    public Integer save(Topic topic) {
        String sql="insert into t_topic (title,content,userid,nodeid)values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getUserid(),topic.getNodeid());
    }

    public Topic findByTopicId(String topicId) {
        String sql = "select * from t_topic where id = ?";
        Topic topic=DbHelp.query(sql,new BeanHandler<>(Topic.class),topicId);
        return topic;
    }

}
