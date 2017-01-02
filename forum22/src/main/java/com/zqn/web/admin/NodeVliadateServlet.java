package com.zqn.web.admin;



import com.zqn.service.NodeService;
import com.zqn.util.StringUtils;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by dell on 2017/1/2.
 */
@WebServlet("/admin/nodeValidate")
public class NodeVliadateServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nodeId = req.getParameter("nodeid");
        String nodeName = req.getParameter("nodename");
        nodeName = StringUtils.isTouf8(nodeName);
        //nodeName = new String(nodeName.getBytes("IOS8859-1"),"UTF-8");
        NodeService nodeService = new NodeService();
        String res = nodeService.validateNodeName(nodeId,nodeName);
        renderText(res,resp);
    }
}
