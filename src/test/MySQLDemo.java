package test;

import java.sql.*;

public class MySQLDemo {
 
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String URL = "jdbc:mysql://115.29.227.78:3306/eecs";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String NAME = "root";
    static final String PWD = "123";
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(URL,NAME,PWD);
            System.out.println("连接数据库成功");
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM tb_user";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String url = rs.getString("user_pwd");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Pwd: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
                if(conn!=null) conn.close();
            }catch(Exception e2){
            	e2.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}