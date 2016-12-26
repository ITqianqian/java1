package com.zqn.web.topic;

import com.zqn.entity.User;
import com.zqn.exception.ServiceException;
import com.zqn.service.TopicService;
import com.zqn.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/12/24.
 */
@WebServlet("/newReply")
public class ReplyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        String content = req.getParameter("content");

        TopicService topicService = new TopicService();
        User user = (User) req.getSession().getAttribute("curr_user");

        if (StringUtils.isNumeric(topicid)){
            try {
                topicService.addNewReply(topicid, content, user);
            }catch(ServiceException e){
                resp.sendError(404,e.getMessage());
            }
        }else{
            resp.sendError(404);
        }
        resp.sendRedirect("/topicDetail?topicid="+topicid);
    }


}
