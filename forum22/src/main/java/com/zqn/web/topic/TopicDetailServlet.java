package com.zqn.web.topic;

import com.zqn.entitiy.Fav;
import com.zqn.entitiy.Reply;
import com.zqn.entitiy.Topic;
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
import java.util.List;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid=req.getParameter("topicid");
        TopicService topicService=new TopicService();
        try {
            Topic topic=topicService.findTopicById(topicid);
            //更新topic表中的clicknum字段
            topic.setClicknum(topic.getClicknum()+1);
            topicService.updateTopic(topic);
            //获取该topicid对应的回复列表
            List<Reply> replyList=topicService.findReplyListByTopicid(topicid);
            req.setAttribute("replyList",replyList);
            req.setAttribute("topic",topic);

            User user= (User) req.getSession().getAttribute("curr_user");
            //判断用户是否收藏该主题
            if (user != null && StringUtils.isNumeric(topicid)) {
                Fav fav=topicService.findFavByUseridAndTopicid(topicid,user);
                req.setAttribute("req",req);
            }
            forward("/topic/topicDetail.jsp",req,resp);
        }catch (ServiceException e){
            resp.sendError(404);

        }
    }
}
