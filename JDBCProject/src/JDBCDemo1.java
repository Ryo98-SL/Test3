import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCDemo1 {
	private  static final String URL = "jdbc:mysql://localhost:3306/zcy";

	private static final String USERNAME = "root";
	private  static final String PWD = "123456";

	public static  void update() {// ��ɾ�Ĳ�
		Connection connection = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
//�����ݿ⽨������

			connection = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);
			// c.����sql��ִ�������ɾ�ġ��飩
			stmt = (Statement) connection.createStatement();
			String sql = "insert into student values(170201038,'za',23,98)";
			
			//String sql = "update student set  studentname='lh' where studentname='zcy'";
			//String sql = "delete from student where studentid=170201003";
			// ִ��sql���
			int count = stmt.executeUpdate(sql);// ����ֵ��ʾ��ɾ���˼�������
			// ��������
			if (count > 0) {
				System.out.print("�����ɹ�!");
			}
			// ���¹ؿ��ԣ����ǲ���ȫ
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		catch (ClassNotFoundException    e) {
			e.printStackTrace();
		}
		catch (Exception    e) {
			e.printStackTrace();
		}
		
		finally {
			try {
			if(stmt!=null) {stmt.close();}
			if(connection!=null) {connection.close();}
			}catch(SQLException e) 
			{	e.printStackTrace();
				
			}
			
		}
	}
public static void main(String[ ] args) {
	update();
}

	}