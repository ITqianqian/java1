package com.zqn.web.admin;



import com.zqn.entitiy.Node;
import com.zqn.service.TopicService;
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
@WebServlet("/admin/node")
public class NodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Node> nodeList = new TopicService().findAllNode();
        req.setAttribute("nodeList",nodeList);
        forward("admin/node.jsp",req,resp);
    }
}
