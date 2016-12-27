package com.zqn.web;

import com.zqn.entity.Node;
import com.zqn.entity.Topic;
import com.zqn.service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
@WebServlet("/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TopicService topicService = new TopicService();
        List<Node> nodeList = topicService.findAllNode();


        req.setAttribute("nodeList",nodeList);
        forward("index.jsp",req,resp);
    }
}
