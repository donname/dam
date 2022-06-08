package com.context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


//访问hello，就会显示秦江
//context 实现数据共享


@WebServlet(name = "GetServlet", value = "/GetServlet")
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               ServletContext context =  this.getServletContext();
               String username =(String) context.getAttribute("username");  //将object强转成String,从Hello中获取
              response.setContentType("text/html;charset=utf-8");//设置页面内容是html，编码格式是utf-8。
//              response.setContentType("text/html");
//              response.setCharacterEncoding("utf-8");      等价意思
               response.getWriter().print("名字"+username);       //实现context会话共享

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
