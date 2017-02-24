package com.zqn.mapper;

import com.zqn.pojo.Node;
import com.zqn.pojo.Topic;

/**
 * Created by dell on 2017/1/4.
 */
public interface NodeMapper {
    void save (Node node);
    Node findById(Integer id);
}
