package com.zqn.web.user;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.zqn.dto.JsonResult;
import com.zqn.entitiy.Notify;
import com.zqn.entitiy.User;
import com.zqn.service.UserService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notify")
public class NotifyServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=getCurrentUser(req);
        UserService userService=new UserService();
        List<Notify> notifyList=userService.findByUser(user);
        req.setAttribute("notifyList",notifyList);
        forward("user/notify.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        //1.根据用户id和通知状态查询未读列表

        //2.根据guava 的Collections2.filter 过滤未读消息数据
        UserService userService=new UserService();
        List<Notify> notifyList=userService.findByUser(user);
        List<Notify> unreadList= Lists.newArrayList(Collections2.filter(notifyList, new Predicate<Notify>() {
            @Override
            public boolean apply(Notify notify) {
                return notify.getState()==0;
            }
        }));
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(unreadList.size());
        jsonResult.setState(JsonResult.SUCCESS);
        renderJson(jsonResult,resp);
    }
}
