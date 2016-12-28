package com.zqn.web.user;

import com.zqn.service.UserService;
import com.zqn.util.StringUtils;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validate/user")
public class ValidateUserServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        //处理URL中的中文问题
        username= StringUtils.isTouf8(username);
        UserService userService=new UserService();
        boolean result=userService.validateUsername(username);
        if(result){
            renderText("true",resp);
        }else {
            renderText("false",resp);
        }
    }
}
