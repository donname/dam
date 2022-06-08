package com.example.dam;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//重定向
@WebServlet(name ="RedirectServlet", value ="/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //重定向response
        //重定向到/dam_war_exploded/img
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.sendRedirect("/dam_war_exploded/image");    // response.sendRedirect("/index.jsp");
         System.out.println("重定向");
         System.out.println(username+password);


         //request
        //HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器Http中的所有信息会被封装到HttpServletRequest，
        // 通过这个HttpServletRequest，获得客户端的所有信息


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
