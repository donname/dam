package com.context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "GetWebServlet", value = "/GetWebServlet")
public class GetWebServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这两个代码不能同时
        response.setContentType("text/html;charset=utf-8");//设置页面内容是html，编码格式是utf-8。
        ServletContext context =  this.getServletContext();
        String url = context.getInitParameter("url");    //从web.xml中获得  <context-param>
        response.getWriter().print(url);                        //获取初始化参数

////请求转发  访问没有变化 A访问B B访问C C把东西给B B再给A
////重定向              A访问B B告诉A要访问C 然后A去访问C
//        System.out.print("进入了GetWebServlet");
//        ServletContext context1 = this.getServletContext();     //请求转发，现在转到Hello界面
////        RequestDispatcher requestDispatcher=context1.getRequestDispatcher("/");      //forward转发的请求路径
////        requestDispatcher.forward(request,response);    //调用forward请求转发   只是转发请求，所以目前我们只有请求和响应
//        context1.getRequestDispatcher("/").forward(request,response);     //合并上面两句

System.out.print("hahahah");
//需要一个文件流，

        //读取资源文件 使用Properties
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        //拿到文件的流

        Properties properties = new Properties();
        properties.load(is);
        String username = properties.getProperty("username");
        String passward = properties.getProperty("password");

        response.getWriter().print("用户"+username+"密码"+passward);

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
