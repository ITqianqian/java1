package com.zqn.web.user;

import com.zqn.entity.User;
import com.zqn.service.UserService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/12/19.
 */
@WebServlet("/validate/email")
public class ValidateEmail extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserService userService = new UserService();
        User user=userService.findByEmail(email);

        if(user==null){
            renderText("ture",resp);
        }else{
           renderText("false",resp);
        }

    }
}
