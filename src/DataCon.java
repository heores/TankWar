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
		 
		 
		//加载数据库的函数
		static void jiazai()
		{
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				
			}
		}
		//连接数据库的函数并获得Statement对象
		static void getConn()
		{
			try {
				 con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Fenshu","sa","123456");
			     stmt=con.createStatement();//此时可以对数据库中的表进行操作了
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}			
		}
		//插入数据操作
		static void inserSQL()
		{
			 
			String sql ="insert into fenhsu values('"+user+"','"+fs+"')";
			System.out.println(sql);
			try {
				stmt.executeUpdate(sql);
				System.out.println("插入数据成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		} 
		
		static ResultSet selectSql()
		{
			try {
				rs=stmt.executeQuery("SELECT TOP 10 *  FROM fenhsu ORDER BY fenshu DESC");
				
				System.out.println("查询数据成功");
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