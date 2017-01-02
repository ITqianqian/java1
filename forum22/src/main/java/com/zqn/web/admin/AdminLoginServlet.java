package com.zqn.web.admin;

import com.zqn.dto.JsonResult;
import com.zqn.entitiy.Admin;
import com.zqn.exception.ServiceException;
import com.zqn.service.AdminService;
import com.zqn.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/1/2.
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("admin/adminLogin.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminname = req.getParameter("adminname");
        String password = req.getParameter("password");

        String ip = req.getRemoteAddr();

        AdminService adminService = new AdminService();
        JsonResult jsonResult = new JsonResult();
        try {
            Admin admin = adminService.login(adminname, password, ip);
            System.out.println(admin);
            req.getSession().setAttribute("curr_admin",admin);
            jsonResult.setState(JsonResult.SUCCESS);
        }catch (ServiceException e){
            jsonResult.setState(JsonResult.ERROR);
            jsonResult.setMessage(e.getMessage());

        }
        renderJson(jsonResult,resp);



    }
}
