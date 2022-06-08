package com.example.dam;

import com.example.dam.bean.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//Session
//1.服务器会给每一个用户创建一个Session对象
//2.一个Session独占一个浏览器，只要浏览器没有关闭，这个Session就存在
//3.用户登录后整个网站都可以访问

//Session和Cookie的区别
//1.Cookie是把用户的数据写给用户的浏览器，浏览器保存(可以保存多个)
//2.Session把用户的数据写到用户独占的Session中，服务器端保存（可以保存重要的信息，减少服务器的浪费）
//3.Session对象由服务器创建

//Session使用场景
//1.保存一个登录用户的信息
//2.购物车信息
//3.在整个网站中经常使用的数据

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码问题
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");  //设置浏览器响应的格式
        //得到Session
        HttpSession session = request.getSession();
        //给Session存东西
        session.setAttribute("name",new Person("秦江",46));
        //获取Session的ID
        String id = session.getId();

        //Session在创建的时候都干了什么
        //创建一个Cookie     Cookie  cookie = newCookie("JSESSIONID",id);
        //将cookie响应回去了  response.addCookie(cookie);

        //判断Session是不是新创建的
        if (session.isNew()){
            response.getWriter().write("session创建成功,ID:"+id);
        }else {
            response.getWriter().write("Session已经在服务器中存在了"+id);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
