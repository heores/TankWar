import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCon {
	 		 
		 static Connection con=null;
		 static java.sql.Statement stmt=null;
		 static String user;
		 static int  fs;
		 static ResultSet rs=null;
		 
		 
		//�������ݿ�ĺ���
		static void jiazai()
		{
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				
			}
		}
		//�������ݿ�ĺ��������Statement����
		static void getConn()
		{
			try {
				 con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Fenshu","sa","123456");
			     stmt=con.createStatement();//��ʱ���Զ����ݿ��еı���в�����
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}			
		}
		//�������ݲ���
		static void inserSQL()
		{
			 
			String sql ="insert into fenhsu values('"+user+"','"+fs+"')";
			System.out.println(sql);
			try {
				stmt.executeUpdate(sql);
				System.out.println("�������ݳɹ�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		} 
		
		static ResultSet selectSql()
		{
			try {
				rs=stmt.executeQuery("SELECT TOP 10 *  FROM fenhsu ORDER BY fenshu DESC");
				
				System.out.println("��ѯ���ݳɹ�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
		static void closeConn()
		{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
		}

}