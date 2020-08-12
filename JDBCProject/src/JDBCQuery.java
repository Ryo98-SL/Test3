import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCQuery {
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //ÐÕÃû£ºÊæµÂÁÁ
	private static final String PWD ="123456" ; //Ñ§ºÅ:170201048

	
public static void update() {
	Connection connection=null;
			Statement stmt = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	  stmt =  (Statement) connection.createStatement();
	  //String sql="insert into student values(1,'ÊæµÂÁÁ',21,170201048)";
	 //String sql="update student set stuname='shudeliang'where stuno=1";
	 String sql="delete from student where stuno=1";
	 int count = stmt.executeUpdate(sql);
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
	stmt.close();
	connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

public static void query() {
	Connection connection=null;
	Statement stmt = null;
	ResultSet rs = null;
try {
    Class.forName("com.mysql.jdbc.Driver");
    connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
    stmt =  (Statement) connection.createStatement();
    //String sql="select stuno,stuname from student";
   
	Scanner input = new Scanner(System.in);
    System.out.println("ÇëÊäÈëÐÕÃû£º");//ÊæµÂÁÁ
    String name=input.nextLine();
    System.out.println("ÇëÊäÈëÃÜÂë£º");//170201048
    String pwd=input.nextLine();
    String sql="select count(*) from login where uname = '"+name+"' and upwd='"+pwd+"'";
    rs = stmt.executeQuery(sql);
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
   if(stmt!=null) stmt.close();
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