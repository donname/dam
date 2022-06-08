package com.example.dam.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "jdbcServlet", value = "/jdbcServlet")
public class jdbcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //解决中文乱码

        String username="root";
        String password="637132@.";
        String url = "jdbc:mysql://127.0.0.1:3306/user?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("连接成功");
            //创建Statement对象,
            Statement statement= connection.createStatement();
            //建立结果集
            ResultSet resultSet = statement.executeQuery("SELECT id,name FROM csdn");
            System.out.println("查询成功");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+""+resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //连接数据库


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
