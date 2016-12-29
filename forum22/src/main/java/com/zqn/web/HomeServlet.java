package com.zqn.web;

import com.zqn.entitiy.Node;
import com.zqn.entitiy.Topic;
import com.zqn.service.TopicService;
import com.zqn.util.Page;
import com.zqn.util.StringUtils;

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
        String nodeid=req.getParameter("nodeid");
        String p=req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        if(StringUtils.isNotEmpty(nodeid)&&!StringUtils.isNumeric(nodeid)){
            forward("index.jsp",req,resp);
            return;
        }
        TopicService topicService=new TopicService();
        List<Node> nodeList=topicService.findAllNode();
        for(Node node : nodeList){
            node.setNodename(new String(node.getNodename().getBytes("ISO-8859-1"),"UTF-8"));
        }

        Page<Topic> page=topicService.findAllTopics(nodeid,pageNo);
        req.setAttribute("nodeList",nodeList);
        req.setAttribute("page",page);
        forward("index.jsp",req,resp);
    }
}
