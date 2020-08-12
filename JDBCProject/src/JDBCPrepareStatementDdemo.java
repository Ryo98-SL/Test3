import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class JDBCPrepareStatementDdemo {
	private static final String URL = "jdbc:mysql://localhost:3306/zcy";
	private static final String USERNAME = "root"; // 姓名：周春燕
	private static final String PWD = "123456"; // 学号:170201029

	public static void update() {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 与数据库建立连接
			connection = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);
			/*
			 * statement
			 * 
			 * // c.发送sql，执行命令（增删改、查） stmt = (Statement) connection.createStatement();
			 * //String sql = "insert into student values(170201027,'zcy',23,98)"; //String
			 * sql = "update student set  s_name='hh' where s_name='zcy'"; String sql =
			 * "delete from student where s_id=1002"; // 执行sql语句 int count =
			 * stmt.executeUpdate(sql);// 返回值表示增删改了几行数据
			 */

			// PraparedStatemwent
			String sql = "insert into student values(?,?,?,?)";
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			pstmt.setString(2, "liling");
pstmt.setInt(1, 1020);

pstmt.setInt(3,36);
pstmt.setInt(4, 56);
			int count = pstmt.executeUpdate();
			// 处理结果集
			if (count > 0) {
				System.out.println("7班-周春燕170201029第七次作业  操作成功！");
			} else {

				System.out.println("操作失败！");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void query() {

		Connection connection = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//与数据库建立连接
			connection = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);
			// c.发送sql，执行命令（增删改、查）
			
			
			//String name = "h";
			//String sql = "select * from student where s_name like  '%" + name + "%' ";
			String sql = "select * from student where s_name like  ?";

			
			pstmt=(Statement) connection.prepareStatement(sql);
			// String sql = "insert into student values(170201027,'zcy',23,98)";
			// String sql = "update student set s_name='hh' where s_name='zcy'";
			// String sql = "select s_id,s_name,s_age,s_grades from student";

			((PreparedStatement) pstmt).setString(1,"%q%");
			// 执行sql语句(增删改是executeupdate（），查询是executequy())
			// 返回值表示增删改了几行数据
			// 处理结果集
			rs=pstmt.executeQuery(sql);
			while (rs.next()) {

				int s_id = rs.getInt("s_id");
				String s_name = rs.getString("s_name");
				int s_age = rs.getInt("s_age");
				int s_grades = rs.getInt("s_grades");
				// int s_id= rs.getInt(1);
				// String s_name=rs.getString(2);下标是从1开始的
				// int s_age=rs.getInt(3);
				// int s_grades=rs.getInt(4);
				System.out.println(s_id + "--" + s_name + "--" + s_age + "--" + s_grades);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//update();
		 query() ;

	}

}