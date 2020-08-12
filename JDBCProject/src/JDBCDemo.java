import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCDemo {
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //姓名：舒德亮
	private static final String PWD ="123456" ; //学号:170201048

	
public static void update() {
	Connection connection=null;
			Statement stmt = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	  stmt =  (Statement) connection.createStatement();
	  //String sql="insert into student values(1,'舒德亮',21,170201048)";
	 //String sql="update student set stuname='shudeliang'where stuno=1";
	 String sql="delete from student where stuno=1";
	 int count = stmt.executeUpdate(sql);
	if(count>0) {
		System.out.println("操作成功！");
	}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
	stmt.close();
	connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void main(String[] args) {
	update();
}
}