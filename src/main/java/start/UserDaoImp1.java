package start;


import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImp1 implements UserDao{

//编写用户登录类
    @Override
    public User getLoginUser(Connection connection, String username) { //接口是个约束

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        //判断数据库是否连接成功
        if (connection!= null){
            System.out.println("UserDaoImp1数据库连接成功");
            String sql = "select * from account where username=?";  //username=?   username是传进来的参数
            Object[] params={username};

            try {
                rs = BaseDao.execute(connection,pstm,rs,sql,params);
                if (rs.next()){         //遍历rs，但是只查询一次
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));

                }
                BaseDao.closeResource(null,pstm,rs);    //连接此时还不能关，因为此时有可能存在业务，需要在业务层关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


     return user;         //返回一个用户
    }




}
