package LoginServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//退出界面

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 //清楚Session
        System.out.println("进入LogoutServlet，删除Session");
     request.getSession().removeAttribute(Constants.USER_SESSION);  //只是让这个常量变为null
     response.sendRedirect(request.getContextPath()+"/login.jsp");  //返回登录页面
        //request.getContextPath()  = = /dam_war_exploded/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
