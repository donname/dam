package start;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候就初始化
    static {
        Properties properties = new Properties();
        //通过类加载器读取对应资源
        //getResourceAsStream("db.properties")  将资源变成一个流
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(is);    //载入完便可以获取了

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        password = properties.getProperty("password");
        username = properties.getProperty("username");
    }
    //获取数据库的连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //编写查写公共类
    public static ResultSet execute(Connection connection,PreparedStatement preparedStatement,ResultSet  resultSet,String sql,Object[] params) throws SQLException {
        //目前不知道sql会有几个参数，所以我们就用params代替执行
        //预编译的sql，在后面直接执行就可以了
        preparedStatement = connection.prepareStatement(sql);

       for (int i=0;i<params.length;i++)
           //setObject，占位符从1开始，但是我们数组是从0开始的
       preparedStatement.setObject(i+1,params[i]);
       //无论传了什么都会站位
        resultSet = preparedStatement.executeQuery();
       return resultSet;

    }

    //编写增删改公共方法（重载）
    public static int execute(Connection connection, PreparedStatement preparedStatement,String sql,Object[] params) throws SQLException {
        //目前不知道sql会有几个参数，所以我们就用params代替执行
        preparedStatement = connection.prepareStatement(sql);

        for (int i=0;i<params.length;i++)
            //setObject，占位符从1开始，但是我们数组是从0开始的
            preparedStatement.setObject(i+1,params[i]);
        //无论传了什么都会站位
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;

    }

    //释放资源
    public  static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if (resultSet!= null) {
            try {
                resultSet.close();
                //垃圾回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement!= null) {
            try {
               preparedStatement.close();
                //垃圾回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection!= null) {
            try {
                connection.close();
                //垃圾回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
