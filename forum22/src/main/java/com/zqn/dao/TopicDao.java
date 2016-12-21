package com.zqn.dao;

import com.zqn.entity.Topic;
import com.zqn.util.DbHelp;

/**
 * Created by dell on 2016/12/21.
 */
public class TopicDao {
    public Integer addNewTopic(Topic topic) {
        String sql="insert into t_topic (title,content,userid,nodeid)values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getUserid(),topic.getNodeid());
    }
}
