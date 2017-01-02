package com.zqn.web.admin;


import com.zqn.dto.JsonResult;
import com.zqn.entitiy.Node;
import com.zqn.exception.ServiceException;
import com.zqn.service.NodeService;
import com.zqn.service.TopicService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/1/2.
 */
@WebServlet("/admin/nodeUpdate")
public class NodeUpdateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeId = req.getParameter("nodeId");
        TopicService topicService = new TopicService();
        try {
            Node node = topicService.findNodeById(nodeId);
            req.setAttribute("node", node);
            forward("admin/nodeUpdate.jsp",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeId = req.getParameter("nodeid");
        String nodeName = req.getParameter("nodename");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult = new JsonResult();
        try {
            nodeService.updateNode(nodeId, nodeName);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJson(jsonResult,resp);

    }
}
