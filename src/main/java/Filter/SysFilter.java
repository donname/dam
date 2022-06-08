package Filter;

import LoginServlet.Constants;
import start.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //拿到Session
         HttpServletRequest request = (HttpServletRequest) req;
         //重定向
         HttpServletResponse response = (HttpServletResponse) resp;
        //过滤用户，从Session中获取用户
         User user =(User) request.getSession().getAttribute(Constants.USER_SESSION);
         if(user == null){  //说明已经被注销，或者未登录
             System.out.println("Session已经失效，需要重新进入");
             response.sendRedirect(request.getContextPath()+"/error.jsp");
         } else  {
             chain.doFilter(req,resp);  //寻找下一个Filter
         }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
