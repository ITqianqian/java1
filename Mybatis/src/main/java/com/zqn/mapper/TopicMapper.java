package com.zqn.mapper;

import com.zqn.pojo.Node;
import com.zqn.pojo.Topic;

import java.util.List;

/**
 * Created by dell on 2017/1/5.
 */
public interface TopicMapper {
    void save(Topic topic);
    void update(Topic topic);
    Topic findById(Integer id);
    List<Topic> findAll();
    List<Topic> findByNodeid(Integer nodeid);
    void bachSave(List<Topic> topicList);
}
