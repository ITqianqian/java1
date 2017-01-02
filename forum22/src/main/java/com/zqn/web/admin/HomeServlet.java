package com.zqn.web.admin;

import com.zqn.entitiy.TopicReplyCount;
import com.zqn.service.TopicService;
import com.zqn.util.Page;
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
@WebServlet("/admin/home")
public class HomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;

        Page<TopicReplyCount> page = new TopicService().getTopicAndReplyNumByDayList(pageNo);
        req.setAttribute("page",page);
        forward("admin/adminhome.jsp",req,resp);
    }
}
