package com.example.dam;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "CookieServlet", value = "/CookieServlet")
//保存用户上次访问的时间
public class CookieServlet1 extends HttpServlet {
    //保存会话的两种技术
    //cookie服务端给客户端一个信件，客户端下次访问服务端带上信件就可以了
    //session服务端登记过你来过，下次再来的时候自动匹配

    //cookie    一般会保存在本地文件夹中
    //1.从请求中拿到cookie信息
    //2.服务器响应给客户端
    // Cookie[] cookies = request.getCookies();   获得cookie
    // cookie.getName()                           获得cookie中的key
    // cookie.getValue();                         获得cookie中的值
    // new Cookie("LastLoginTime",System.currentTimeMillis()+"");    新建一个cookie
    // cookie.setMaxAge(24*60*60);                设置cookie有效期
    // response.addCookie(cookie);                响应给客户端cookie，可以多个
    // 细节:1.一个cookie只能保存一个信息； 2.一个web站点可以给浏览器发送多个cookie，最多存放20个  3.cookie大小限制4kb  4.300个浏览器上限
    //删除cookie 不设置有效期，浏览器关闭即消失。  有效期设置为0

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//服务器，告诉你，你来的时间，把这个时间封装成一个“信件”，下次带来
        //解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html"); //text/html的意思是将文件的content-type设置为text/html的形式，
        // 浏览器在获取到这种文件时会自动调用html的解析器对文件进行相应的处理
        response.setCharacterEncoding("utf-8");
        //客户端返回一些字符串，响应给输出对象response
        PrintWriter out = response.getWriter();
        //cookie 服务器从客户端获取
      Cookie[] cookies = request.getCookies();          //从请求里拿到cookie,这里返回数组，说明cookie可能有多个

        //判断cookie是否存在
        if (cookies != null){
            //如果存在遍历cookie数组
            out.println("你上次访问的时间是：");
//            for (Cookie cookie:cookies){}
            //遍历数组
            for (int i=0;i<cookies.length;i++){
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("LastLoginTime")){
                    //获取cookie中的值
                    //cookie.getValue();  //获取字符串，想让他输出成一个“date”
                    long lastLoginTime = Long.parseLong(cookie.getValue());    //现在将取出的字符串转换成长整型
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());   //过期了但是能用，本地时间

                }

            }
        }
        else{
            //第一次访问一定没有值，只有访问后才会有
            out.write("这是您第一次访问本站");
        }

        //服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("LastLoginTime",System.currentTimeMillis()+"");
        //cookie有效期为一天，不怎么安全
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);   //可以响应多个




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
