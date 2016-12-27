package com.zqn.web.topic;

import com.zqn.dto.JsonResult;
import com.zqn.entity.Reply;
import com.zqn.entity.Topic;
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
@WebServlet("/topicdetail")
public class TopicDetailServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicId = req.getParameter("topicid");

        TopicService topicService = new TopicService();
        JsonResult jsonResult = null;
        try {
            Topic topic = topicService.findByTopicId(topicId);
            List<Reply> replyList =  topicService.findReplyByTopic(topicId);

            req.setAttribute("topic", topic);
            req.setAttribute("replyList",replyList);

            forward("/topic/topicDetail.jsp", req, resp);
        }catch (ServletException e){
            resp.sendError(404);

        }


    }


}
