package com.zqn.service;

import com.zqn.dao.NodeDao;
import com.zqn.dao.TopicDao;
import com.zqn.dto.JsonResult;
import com.zqn.entity.Node;
import com.zqn.entity.Topic;
import com.zqn.entity.User;

import java.util.List;

/**
 * Created by dell on 2016/12/21.
 */
public class TopicService {
    private NodeDao nodeDao = new NodeDao();
    private TopicDao topicDao = new TopicDao();
    public List<Node> findAllNode() {
       return nodeDao.findAllnode();
    }

    public Topic addNewTopic(String title, String content, String nodeid,User user) {
        Topic topic =new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNodeid(Integer.parseInt(nodeid));
        topic.setUserid(user.getId());

        Integer topiId = topicDao.addNewTopic(topic);

        topic.setId(topiId);

        return topic;


    }
}
