import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class JDBCBlobDemo { //
	private static final String URL ="jdbc:mysql://localhost:3306/demo";
	private static final String USERNAME ="root"; //姓名：舒德亮
	private static final String PWD ="123456" ; //学号:170201048

	
public static void BlobDemo() {
	Connection connection=null;
			PreparedStatement pstmt = null;
	
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	String sql="insert into mymusic values(?,?)";
	  pstmt =  (PreparedStatement) connection.prepareStatement(sql);
	 pstmt.setInt(1,1);
	 File file = new File("d:\\舒德亮170201048.mp3");
	 InputStream in =new FileInputStream(file);
	 pstmt.setBinaryStream(2,in,(int)file.length());
	 
	 
	 
	 int count = pstmt.executeUpdate();
	if(count>0) {
		System.out.println("操作成功！");
	}
	in.close();
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
public static void BlobReaderDemo() {
	Connection connection=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	  connection= (Connection) DriverManager.getConnection(URL,USERNAME,PWD);
	String sql="select music from mymusic where id =?";
	  pstmt =  (PreparedStatement) connection.prepareStatement(sql);
	 pstmt.setInt(1,1);
     rs = pstmt.executeQuery();
	 if(rs.next())
	 {
		InputStream in= rs.getBinaryStream("music");
	    OutputStream out = new FileOutputStream("src/舒德亮170201048.mp3");
	    byte[] chs = new byte[100];
	    int len=-1;
	    while((len=in.read(chs))!=-1) {
	    	out.write(chs,0,len);
	    }
	    out.close();
	    in.close();
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
	BlobReaderDemo();
	//BlobDemo();
}
}
