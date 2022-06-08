package start;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImp1 implements UserService {
    //业务层都会调用dao层，所以我们引入Dao层
    private UserDao userDao;

    public UserServiceImp1() {
        userDao = new UserDaoImp1();   //实例化操作
    }

    @Override

    public User login(String username, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();          //得到连接
            //通过业务层调用对应的具体的数据库操作
            user = userDao.getLoginUser(connection, username); //调用前面定义好的userDao.getLoginUser

        } finally {
            BaseDao.closeResource(connection, null, null); //connection就可以关闭了
        }
        if (Objects.equals(password, user.getPassword()))
            return user;
        else
            return null;
    }
     @Test
    public void test(){
        UserServiceImp1 userService = new UserServiceImp1();
        User admin = userService.login("zhailiping","123456");  //password目前没有用到
        System.out.println(admin.getPassword()+admin.getName());
    }





}


