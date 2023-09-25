package jsoft;

import java.sql.*;
import java.util.*;
import java.sql.SQLException;

public class ConnectionPoolImpl implements ConnectionPool {

	//Trình điều khiển làm việc với MySQL
	private String driver;
	
	//đường dẫn thực thi
	private String url;
	
	//Tài khoản làm việc với csdl
	private String username;
	private String userpass;
	
	//Nạp trình điều khiển
	private Stack<Connection> pool;
	
	//Đối tượng lưu trữ kết nối
	
	public ConnectionPoolImpl()
	{
		//Xác định trình điều khiển
		this.driver = "com.mysql.cj.jdbc.Driver";
		
		//xác định đường dẫn thực thi
		this.url = "jdbc:mysql://localhost:3306/jp225_data?allowMultiQueries=true";
		
		//Xác định tài khoản làm việc
		this.username = "jp225_khanh";
		this.userpass = "khanhson0702";
		
		this.loadDriver();
		
		// khởi tạo đối tượng lưu trưdx kết nối
		this.pool = new Stack<>();
	}	
	
	private void loadDriver() {//Nạp trình điều khiển
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		if(this.pool.isEmpty()) {
			//KHởi tạo kết nối mới
			System.out.print(objectName  + " have created a new connection.\n");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			//Lấy kết nối đã được lưu trữ
			System.out.print(objectName + " popped the connection.");
			
			
			return this.pool.pop();
		}
		
		
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		
		//yếu cầu đới tượng trả về kết nối
		System.out.print(objectName + " have pushed the connection.");
		this.pool.push(con);
	}
	
	protected void finalize() throws Throwable{
		//đóng kết nối 
		this.pool.clear();
		this.pool=null;
		
		System.out.print("ConnectionPool is closed.");
	}

}
