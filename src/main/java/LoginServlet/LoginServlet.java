package LoginServlet;

import start.User;
import start.UserService;
import start.UserServiceImp1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//处理登录请求


public class LoginServlet extends HttpServlet {
    //Servlet：控制层调用业务层代码
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入LoginServlet");
        //获取用户输入的用户名和登录密码
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        //和数据库的密码进行对比，调用业务层；
        UserService userService = new UserServiceImp1();
        User user = userService.login(userName,userPassword);  //此时已经把登录的人给查出来了
       System.out.println("开始登录");
        if(user!=null){   //查有此人，可以登录
            System.out.println("查有此人");
            //将用户的信息放到Session；
            request.getSession().setAttribute(Constants.USER_SESSION,user);  //将user储存到Session
            //跳转到内部主页
            response.sendRedirect("/dam_war_exploded/jsp/frame.jsp");
        }else{ //查无此人
            // 转发回登录页面，顺便提示它，用户或者密码错误
            System.out.println("查无此人");
            request.setAttribute("error","用户名或者密码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
