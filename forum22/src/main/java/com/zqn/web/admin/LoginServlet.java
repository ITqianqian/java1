package com.zqn.web.admin;

import com.zqn.dto.JsonResult;
import com.zqn.entitiy.Admin;
import com.zqn.service.AdminService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/12/29.
 */
@WebServlet("/admin/login")
public class LoginServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断当前是否有用户
        req.getSession().removeAttribute("curr_admin");
        forward("admin/adminlogin.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminname = req.getParameter("adminname");
        String password = req.getParameter("password");
        //获取Ip
        String ip = req.getRemoteAddr();
        AdminService adminService = new AdminService();

        JsonResult jsonResult = null;
        try {
            Admin admin = adminService.login(adminname, password, ip);
            req.getSession().setAttribute("curr_admin",admin);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (Exception e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());
        }
        renderJson(jsonResult,resp);




    }
}
