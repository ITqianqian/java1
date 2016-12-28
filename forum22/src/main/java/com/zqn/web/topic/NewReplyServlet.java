package com.zqn.web.topic;

import com.zqn.entitiy.User;
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
 * Created by Administrator on 2016/12/22 0022.
 */
@WebServlet("/newReply")
public class NewReplyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid=req.getParameter("topicid");
       // System.out.println(req.getRequestURI());
        String content=req.getParameter("content");
        User user= (User) req.getSession().getAttribute("curr_user");
        TopicService topicService=new TopicService();
        if(StringUtils.isNumeric(topicid)){
            try {
                topicService.addNewReply(topicid, user, content);
            }catch (ServiceException e){
                resp.sendError(404,e.getMessage());
            }
        }else {
            resp.sendError(404);
        }
        resp.sendRedirect("/topicDetail?topicid="+topicid);
    }
}
