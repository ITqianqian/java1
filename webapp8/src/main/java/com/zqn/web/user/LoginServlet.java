package com.zqn.web.user;

import com.google.common.collect.Maps;
import com.zqn.entity.User;
import com.zqn.exception.ServiceException;
import com.zqn.service.UserService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dell on 2016/12/15.
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/user/login",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //获取用户IP
        String ip = req.getLocalAddr();

        Map<String,Object> result = Maps.newHashMap();
        UserService userService = new UserService();

        try {
            User user = userService.login(username, password, ip);

            //将登录成功的用户放入Session
            HttpSession session = req.getSession();
            session.setAttribute("curr_user",user);

            result.put("state","success");
        } catch (ServiceException ex) {
            result.put("state","error");
            result.put("message",ex.getMessage());
        }

        renderJson(result,resp);


    }
}
