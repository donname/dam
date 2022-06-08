package com.example.dam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LgServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        乱码
        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String[] hobbys = req.getParameterValues("hobbys");
        System.out.println("==========================");
        System.out.println(password);
        System.out.println(username);
        System.out.println(Arrays.toString(hobbys));
        System.out.println("==========================");


        //通过请求转发

        req.getRequestDispatcher("/Succeed.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
