package com.zqn.web.user;

import com.google.common.collect.Maps;
import com.zqn.service.UserService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dell on 2016/12/16.
 */
@WebServlet("/reg")
public class RegisterServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/user/register",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Map<String,Object> result = Maps.newHashMap();
        try {
            UserService userService = new UserService();
            userService.saveNewUser(username, password, email, phone);

            result.put("state","success");
        } catch (Exception ex) {
            ex.printStackTrace();
            result.put("state","error");
            result.put("message","注册失败，请稍后再试");
        }
        renderJson(result,resp);

    }
}
