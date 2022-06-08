package com.example.dam;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "CookieServlet3", value = "/CookieServlet3")
//中文数据传递
public class CookieServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html"); //text/html的意思是将文件的content-type设置为text/html的形式，
        // 浏览器在获取到这种文件时会自动调用html的解析器对文件进行相应的处理
        response.setCharacterEncoding("utf-8");

       Cookie[] cookies= request.getCookies();
        PrintWriter out = response.getWriter();
        if (cookies != null){
            //如果存在遍历cookie数组
            out.write("你上次访问的时间是：");
//            for (Cookie cookie:cookies){}
            //遍历数组
            for (int i=0;i<cookies.length;i++){
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("name")){
                 System.out.println(cookie.getValue());
                    System.out.println("123");

                }

            }
        }
        else{
            //第一次访问一定没有值，只有访问后才会有
            out.write("这是您第一次访问本站");
        }




      Cookie cookie = new Cookie("name","秦灿");
      response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }

}
