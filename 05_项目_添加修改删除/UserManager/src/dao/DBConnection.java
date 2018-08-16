package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
	
	private static String Driver_Class="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/mydb";
	private String user="root";
	private String password="123456";
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	//加载驱动器
	static{
		try {
			Class.forName(Driver_Class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//登陆数据库
	public void getConnection() {
		//驱动器管理类 负责 连接数据
		//url: jdbc[主协议]:子协议://主机名或IP:端口号/数据库实例名
		//浏览器端口:80  tomcat端口:8080 MySQL端口:3306  Oracle端口:1521
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn!=null) {
			System.out.println("连接成功!");
		} else {
			System.out.println("连接失败!");
		}
	}
	//统一SQL执行
	public void doSQL(String sql,String[] param) {
		try {
			getConnection();//登陆数据库
			/* Statement只能执行静态SQL命令[不含有占位符]
			 * PreparedStatement是Statement的子类,可以执行静态SQL和含有占位符的SQL
			 */
			pstmt=conn.prepareStatement(sql);//创建执行对象
			//insert into user(name,sex) values(?,?)
			//param= param==null?new String[0]:param;
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i+1, param[i]);
			}
			pstmt.execute();//执行SQL
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//处理执行结果
	public int getUpCount() {
		int i=-1;//-1表示执行失败
		try {
			i=pstmt.getUpdateCount();//获得影响行数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public ResultSet getRS() {
		try {
			rs=pstmt.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//关闭资源: 连接 执行对象  结果集
	public void getClose() {
		try {
			if (rs!=null) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if (pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}finally{
				try {
					if (conn!=null) {
						conn.close();
					}
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
	}
	
}
