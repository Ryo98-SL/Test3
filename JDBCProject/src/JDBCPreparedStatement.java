import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JDBCPreparedStatement {
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //ÐÕÃû£ºÊæµÂÁÁ
	private static final String PWD ="123456" ; //Ñ§ºÅ:170201048

	
public static void update() {
	Connection connection=null;
	 PreparedStatement pstmt = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	/*  
	  stmt =  (Statement) connection.createStatement();
	  //String sql="insert into student values(1,'ÊæµÂÁÁ',21,170201048)";
	 //String sql="update student set stuname='shudeliang'where stuno=1";
	 String sql="delete from student where stuno=1";
	 int count = stmt.executeUpdate(sql);
	 
	 */
	  String sql="insert into student values(?,?,?,?)";
	pstmt = (PreparedStatement) connection.prepareStatement(sql);
	pstmt.setInt(1, 5);
	pstmt.setString(2, "shudeliang");
	pstmt.setInt(3, 22);
	pstmt.setInt(4, 170201048);
	
	 int count = pstmt.executeUpdate();
	if(count>0) {
		System.out.println("²Ù×÷³É¹¦£¡");
	}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
	pstmt.close();
	connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
   public static void query() {
	Connection connection=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    try {
    Class.forName("com.mysql.jdbc.Driver");
    connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
    Scanner input = new Scanner(System.in);
    System.out.println("ÇëÊäÈëÐÕÃû£º");
    String name = input.nextLine();
    System.out.println("ÇëÊäÈëÃÜÂë£º");
    String pwd  = input.nextLine();
    
//    String sql="select * from student where stuname like ?";
    String sql="select count(*) from login where uname = ? and upwd=?";
    pstmt = (PreparedStatement) connection.prepareStatement(sql);
    pstmt.setString(1,name);
    pstmt.setString(2,pwd);
    //String sql="select stuno,stuname from student";
    
    rs = pstmt.executeQuery();
    int count=-1;
    while(rs.next()){
   count = rs.getInt(1);
    }
    if(count>0) {
    	System.out.println("µÇÂ½³É¹¦");
    }else {
    	System.out.println("µÇÂ½Ê§°Ü");
    }

  }catch(ClassNotFoundException e) {
    e.printStackTrace();
   }catch (SQLException e){
    e.printStackTrace();
   }catch (Exception e){
    e.printStackTrace();
   }finally {
   
   try {
	   
   if(rs!=null) rs.close();
   if(pstmt!=null) pstmt.close();
   if(connection!=null) connection.close();
   }catch(SQLException e) {
	e.printStackTrace();
   }
   }
   }

public static void main(String[] args) {
	//update();
	query();
}
}