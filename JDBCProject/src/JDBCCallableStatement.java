import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class JDBCCallableStatement {
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //姓名：舒德亮
	private static final String PWD ="123456" ; //学号:170201048

	
public static void invokeProcedure() {
	Connection connection=null;
			CallableStatement cstmt = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	  cstmt = (CallableStatement) connection.prepareCall("{call addTwoNumber(?,?,?)}");
	  cstmt.setInt(1,10);
	  cstmt.setInt(2,11);
	  cstmt.registerOutParameter(3,java.sql.Types.INTEGER);
	  cstmt.execute();
	 
	  int result = cstmt.getInt(3);
	  
	System.out.println(result+"    舒德亮170201048");  
	
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
	
	if(cstmt!=null)cstmt.close();
	if(connection!=null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void invokeFunction() {
	Connection connection=null;
			CallableStatement cstmt = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	  cstmt = (CallableStatement) connection.prepareCall("{? = call addTwoNum(?,?)}");
	  cstmt.setInt(2,12);
	  cstmt.setInt(3,15);
	  cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
	  cstmt.execute();
	 
	  int result = cstmt.getInt(1);
	  
	System.out.println(result+"    舒德亮170201048");  
	
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
	
	if(cstmt!=null)cstmt.close();
	if(connection!=null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void main(String[] args) {
	invokeProcedure();
	//invokeFunction();
}
}