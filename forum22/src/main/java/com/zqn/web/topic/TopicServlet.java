package com.zqn.web.topic;


import com.zqn.dto.JsonResult;
import com.zqn.entity.Node;
import com.zqn.entity.Topic;
import com.zqn.entity.User;
import com.zqn.service.TopicService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2016/12/21.
 */
@WebServlet("/newtopic")
public class TopicServlet extends BaseServlet {
    private TopicService topicService = new TopicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodeList = topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("topic/newtopic.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");

        //获取session里面的User
        User user = (User) req.getSession().getAttribute("curr_user");

        Topic topic = topicService.addNewTopic(title,content,nodeid,user);

        JsonResult jsonResult = new JsonResult(topic);

        renderJson(jsonResult,resp);


    }
}
