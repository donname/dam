package com.example.dam;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//验证码
//前端实现
//后端实现，需要用到java的图片类，生产一个图片

@WebServlet(name = "ImageServlet", value = "/ImageServlet")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //如何让5s刷新一次
    response.setHeader("refresh","3");
    //在内存中创建一个图片
        BufferedImage bufferedImage = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
    //得到图片
        Graphics2D graphics = (Graphics2D)bufferedImage.getGraphics();//相当于笔
    //设置图片的背景颜色
      graphics.setColor(Color.WHITE);  //背景颜色
      graphics.fillRect(0,0,80,20);    //形状正方形
    //给图片写数据
    graphics.setColor(Color.BLUE);       // 字体颜色
    graphics.setFont(new Font(null,Font.BOLD,20));  //字体类型 斜体
    graphics.drawString(makeNum(),0,20);

//告诉浏览器，这个请求用图片的方式打开
        response.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        response.setDateHeader("expires",-1);
        response.setHeader("Cache-Control","no-cache");  //Cache-Control 缓存控制策略为不缓存
        response.setHeader("Pragma","no-cache");
        //把图片写给浏览器
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());


    }
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i<7-num.length();i++){    //在一定范围，无论num怎么变都保证输出七位数，不足七位填充，超过七位保错
            stringBuffer.append("0");
        }
        num = stringBuffer.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
