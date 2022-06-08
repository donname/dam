package start;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    //得到登录用户
    public User getLoginUser(Connection connection,String username);



}
