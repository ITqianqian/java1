package com.zqn.web.admin;



import com.zqn.dto.JsonResult;
import com.zqn.exception.ServiceException;
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
@WebServlet("/admin/topicUpdate")
public class AdminTopicUpdateServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("id");
        String nodeid = req.getParameter("nodeid");

        TopicService topicService = new TopicService();
        JsonResult result = new JsonResult();
        try {
            topicService.updateTopicNode(topicid, nodeid);
            result.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            result.setState(JsonResult.ERROR);
            result = new JsonResult("参数异常");

        }
        renderJson(result,resp);
    }
}
