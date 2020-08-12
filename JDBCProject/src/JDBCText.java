import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class JDBCText {
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //姓名：舒德亮
	private static final String PWD ="123456" ; //学号:170201048

	
public static void textDemo() {
	Connection connection=null;
			PreparedStatement pstmt = null;
	
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	String sql="insert into mynovel values(?,?)";
	  pstmt =  (PreparedStatement) connection.prepareStatement(sql);
	 pstmt.setInt(1,1);
	File file = new File("E:\\170201048舒德亮 .txt");
	Reader reader = new BufferedReader(new FileReader(file));
	pstmt.setCharacterStream(2,reader,(int)file.length());
	 int count = pstmt.executeUpdate();
	if(count>0) {
		System.out.println("操作成功！");
	}
	reader.close();
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
    if(pstmt!=null)pstmt.close();
    if(connection!=null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void textReaderDemo() {
	Connection connection=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	String sql="select novel from mynovel where id =?";
	  pstmt =  (PreparedStatement) connection.prepareStatement(sql);
	 pstmt.setInt(1,1);
     rs = pstmt.executeQuery();
	 if(rs.next())
	 {
		Reader reader = rs.getCharacterStream("novel");
	    Writer writer = new FileWriter("src/舒德亮.txt");
	    char[] chs = new char[100];
 	    int len = -1;
	    while((len = reader.read(chs))!=-1) {
	    	writer.write(chs,0,len);
	    }
	        writer.close();
	        reader.close();
	}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch (SQLException e){
		e.printStackTrace();
	}catch (Exception e){
		e.printStackTrace();
	}finally {
	
		try {
    if(pstmt!=null)pstmt.close();
    if(connection!=null)connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
public static void main(String[] args) {
	//textDemo();
	textReaderDemo();
}
}