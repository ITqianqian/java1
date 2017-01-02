package com.zqn.web.admin;

import com.zqn.dto.JsonResult;
import com.zqn.exception.ServiceException;
import com.zqn.service.NodeService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/1/2.
 */
@WebServlet("/admin/newnode")
public class NewNodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("admin/newNode.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodename = req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult = new JsonResult();
        try {
            nodeService.addnode(nodename);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJson(jsonResult,resp);
    }
}
