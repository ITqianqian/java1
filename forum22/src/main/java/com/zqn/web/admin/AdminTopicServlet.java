package com.zqn.web.admin;


import com.zqn.entitiy.Node;
import com.zqn.entitiy.Topic;
import com.zqn.exception.ServiceException;
import com.zqn.service.AdminService;
import com.zqn.service.TopicService;
import com.zqn.util.Page;
import com.zqn.util.StringUtils;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2017/1/2.
 */
@WebServlet("/admin/topic")
public class AdminTopicServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        TopicService topicService = new TopicService();
        Page<Topic> page = topicService.findAllTopics("",pageNo);
        req.setAttribute("page",page);
        List<Node> nodeList = topicService.findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/topic.jsp",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AdminService adminService = new AdminService();
        try {
            adminService.deleteTopicById(id);
            renderText("success",resp);
        }catch (ServiceException e){
            renderText(e.getMessage(),resp);
        }
    }
}
