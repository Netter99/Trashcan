package org.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//通用的数据操作方法
public class DBUtil {

		private static final String URL = "jdbc:mysql://127.0.0.1:3306/test";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "123456";
		public static Connection connection = null;
		public static PreparedStatement pstmt = null;
		public static ResultSet rs = null;

		//与服务器连接
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}

		//初始化，并获取PreparedStatement
		public static PreparedStatement createPreparedStatement(String sql, Object[] params) throws ClassNotFoundException, SQLException {
			pstmt = getConnection().prepareStatement(sql);
			 if(params!=null) {
				 for (int i = 0; i < params.length; i++) {
						pstmt.setObject(i+1, params[i]);
				 }
			 }
			 return pstmt;
		}
		
		//通用的增删改
		public static boolean executeUpdate(String sql, Object[] params) {//{"zs",23,170,"xa"}
			try {
				 pstmt = createPreparedStatement(sql, params);
				 int count = pstmt.executeUpdate();
				 if(count>0)
					 return true;
				 else
					 return false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally {
				closeAll(null, pstmt, connection);
			}
		}

		//关闭所有连接
		public static void closeAll(ResultSet rs, PreparedStatement stmt, Connection connection) {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 
		//通用的查：通用表示适合与任何查询
		public static ResultSet executeQuery(String sql, Object[] params) {//select xxx from xx where name=? or id=?
			try {
				 pstmt = createPreparedStatement(sql, params); 
				 rs = pstmt.executeQuery();
				 return rs;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		//查询总数
		public static int getTotalCount(String sql) {
			int count = -1;
			try {
				pstmt = createPreparedStatement(sql, null);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
				return count;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeAll(rs, pstmt, connection);
			}
			return count;
		}
}
