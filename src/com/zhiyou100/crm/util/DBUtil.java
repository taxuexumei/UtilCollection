package com.zhiyou100.crm.util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DBUtil {

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/zycrm?characterEncoding=utf-8";
		String userName = "root";
		String password = "root";
		Connection con = null;
		// 1 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
  public static Object queryForList(String sql,Object[] paramters,Class<?> clz) {
		// 获取连接对象
		Connection con = getConnection();
		// 获取预处理对象
		PreparedStatement ps = null; 
		// 获取结果集对象
		ResultSet rs = null;
		List<Object> beans = new ArrayList<>();

	    try {
	    	// 获取预处理对象
		   ps =con.prepareStatement(sql);
		   
			// 设置参数
			for (int i = 0; i < paramters.length; i++) {	
				ps.setObject(i+1, paramters[i]);	
			}
			// 执行查询，返回一个结果集对象
			 rs = ps.executeQuery();
			while (rs.next()) {
				
				// 通过反射创建对象 Person per = new Person()
				Object obj = clz.newInstance();
				// 获取数据库元信息
				ResultSetMetaData meta = rs.getMetaData();
				
				int count = meta.getColumnCount();
				for (int i = 0; i < count; i++) {
					// 通过数据库元信息对象，获取字段名
					String columnName = meta.getColumnName(i+1);
					columnName = StringUtil.transferToNomal(columnName);
					// 获取对应字段的值
					Object value = rs.getObject(i+1);
					if (value == null) {
						
						continue;
					}
					Field field = clz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, value);
				}
				beans.add(obj);
				
				
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	    finally {
			
			release(con, ps, rs);
		}
		
		return beans;
	}
	
	
  
  public static void release(Connection con,PreparedStatement ps,ResultSet rs) {
	if(con != null)
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if (ps != null) {
		
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if (rs != null) {
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	  
}

}
