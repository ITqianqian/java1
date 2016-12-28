package com.zqn.web.topic;

import com.zqn.dto.JsonResult;
import com.zqn.entitiy.Topic;
import com.zqn.entitiy.User;
import com.zqn.service.TopicService;
import com.zqn.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topicFav")
public class FavServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action=req.getParameter("action");
    String topicid=req.getParameter("topicid");
    User user= (User) req.getSession().getAttribute("curr_user");

        TopicService topicService=new TopicService();
        JsonResult jsonResult=new JsonResult();

        if(StringUtils.isNotEmpty(action)&&StringUtils.isNumeric(topicid)){
            if(action.equals("fav")){
                topicService.favTopic(topicid,user);
                jsonResult.setState(jsonResult.SUCCESS);
            }else if(action.equals("unfav")){
                topicService.unfavTopic(topicid,user);
                jsonResult.setState(jsonResult.SUCCESS);
            }

            Topic topic=topicService.findTopicById(topicid);
           jsonResult.setData(topic.getFavnum());
        }else {
            jsonResult.setMessage("参数异常");
        }
        renderJson(jsonResult,resp);
    }
}
