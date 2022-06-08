package com.example.dam;

import java.io.*;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//两步    1.编写一个类，实现Servlet接口     2.把类部署到web服务器中


//1.编写一个简单程序，Servlet两个默认接口 Servlet接口--》GenericServlet--》HttpServlet--》我们自己的类继承HttpServlet
//2.ctrl+o 快捷键   由于get或者post只是请求的不同方式，可以相互调用，都是一样的。
//3.编写Servlet映射：我们写的是java程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务中注册我
// 们所写的Servlet，还需要个他能访问的路径。    web.xml注册servlet
//4.配置tomcat
//启动测试

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();//响应流
        writer.print("s========sss");
        System.out.println("666666");

        ServletContext context = this.getServletContext();   //可以保存很多东西，全局唯一，共用

        String username = "秦江";//数据
        context.setAttribute("username",username);    //将一个数据保存在了ServletContext中，名字为username。值username

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}