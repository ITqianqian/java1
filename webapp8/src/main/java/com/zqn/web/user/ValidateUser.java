package com.zqn.web.user;

import com.zqn.service.UserService;
import com.zqn.util.StringUtils;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/12/19.
 */
@WebServlet("/validate/user")
public class ValidateUser extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        // 处理URL中 的中文问题
        username = StringUtils.isoToUtf8(username);

        UserService userService=new UserService();
         Boolean result =userService.validatevUserName(username);

         if(result){
             renderText("ture",resp);
         }else{
             renderText("false",resp);
         }
    }
}
