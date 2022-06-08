package com.example.dam;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

//HttpServletResponse
//web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest对象，代表响应一个HttpServletResponse
//如果要获取客户端请求来的参数：找HttpServletRequest
//如果要给客户端响应一些信息：HttpServletResponse

// 负责向浏览器发送数据的方法     getOutputStream()平常   getWriter()中文
// 负责向浏览器发送响应头的方法
// 相应的状态码常量

//常见应用： 1.向浏览器输出消息
//         2.下载文件
//           1.获取下载文件路径
//           2.下载的文件名
//           3.设置想办法让浏览器能够支持下载我们需要的东西
//           4.获取下载文件的输入流
//           5.创建缓冲区
//           6.获取OutputStream对象
//           7.将FileOutputStream写入到Buffer缓冲区
//           8.使用OutPutStream将缓冲区中的数据输出到客户端

@WebServlet(name = "FileServlet", value = "/FileServlet")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//下载文件
// 1.获取下载文件路径
        //String realPath ="C:\\Users\\86166\\IdeaProjects\\dam\\src\\main\\resources\\1.jpg";
        String realPath ="C:\\Users\\86166\\IdeaProjects\\dam\\src\\main\\resources\\1.jpg";  //当前web寻找
        System.out.println("下载的的路径"+realPath);

// 2.下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\" + 1));//截取最后一个/+1的位置就是文件名
// 3.设置想办法让浏览器能够支持（Content-Disposition）下载我们需要的东西  中文文件名使用URLEncoder.encode编码，否则容易乱码
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));  //attache附件

// 4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
// 5.创建缓冲区
        int len=0;
        byte[] buffer = new byte[1024];
// 6.获取OutputStream对象
        ServletOutputStream outputStream = response.getOutputStream();
// 7.将FileOutputStream写入到Buffer缓冲区
        while ((len=in.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        in.close();
// 8.使用OutPutStream将缓冲区中的数据输出到客户端
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
