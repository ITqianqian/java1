package com.zqn.web;



import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2016/12/15.
 */
public class BaseServlet extends HttpServlet {
    public void forward(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views" + path+".jsp").forward(req, resp);
    }

    public void renderText(String str,HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(str);
        out.flush();
        out.close();
    }
    public void renderJson(Object obj,HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("application/json;charset=UTF-8");

        String json = new Gson().toJson(obj);
        PrintWriter out = resp.getWriter();

        out.print(json);
        out.flush();
        out.close();
    }
}