package com.zqn.web.user;



import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        //强制清楚session
        session.invalidate();

       // resp.sendRedirect("/login?state=logout");
        req.setAttribute("message","已安全退出");
        forward("user/login.jsp",req,resp);
    }
}
