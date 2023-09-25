package jsoft.ads.basic;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import java.sql.*;

import jsoft.*;

public class BasicImpl implements Basic {
	//bộ quản lý kết nối của riêng Basic
	private ConnectionPool cp;
	
	//kết nối để basic làm việc với CSDL
	protected Connection con;//là con của connectionPool
	
	//đối tượng làm việc với basic
	private String objectName;
	
	public BasicImpl(ConnectionPool cp,String objectName) {
		//xác định đối tượng làm việc với basic
		this.objectName = objectName;
		
		//xác định bộ quản lý kết nối
		if(cp==null) {
			this.cp=new ConnectionPoolImpl();
		}else {
			this.cp = cp;
		}
		
		// hỏi xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection(this.objectName);
			
			//kiểm tra trạng thái thực thi của kết nối
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);//hủy thực thi tự động
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized boolean exe(PreparedStatement pre) {//synchronized
		if(pre!=null) {
			try {
				int result =pre.executeUpdate();// thực thi. nhận về số bản ghi được tác động bởi câu lệnh sql
				if(result == 0) {
					this.con.rollback();
					return false;
				}
				
				//xác nhận thực thi sau cùng
				this.con.commit();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				// trở lại trạng thái an toàn của kết nối
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				pre=null;
			}
			
		}
		
		
		return false;
	}
	
	@Override
	public synchronized boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
	
		return this.exe(pre);
	}

	@Override
	public synchronized boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public synchronized boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ResultSet get(String sql, int id) {
		// TODO Auto-generated method stub
		
		// biên dịch
		try {
			PreparedStatement pre =  this.con.prepareStatement(sql);
			if(id>0) {
				pre.setInt(1, id);
			}
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			sql=null;
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, pass);
			
			return pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			sql=null;
		}
		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		// TODO Auto-generated method stub
		return this.get(sql, 0);
		
	}

	@Override
	public ResultSet[] gets(String[] sql) {
		// TODO Auto-generated method stub
		ResultSet[] tmp = new ResultSet[sql.length];
		for(int i=0;i<sql.length;i++) {
			tmp[i] = this.get(sql[i], 0);
		}
		return tmp;
	}

	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		
		
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ResultSet> getMR(String multiSelect) {
		// TODO Auto-generated method stub
		ArrayList<ResultSet> res= new ArrayList<>();
		
		try {
			PreparedStatement pre=this.con.prepareStatement(multiSelect);
			
			boolean result =pre.execute();//ktra xem có bản ghi hay không
			
			do {
				if(result) {					
					res.add(pre.getResultSet());
				}
				
				
				//di chuyển sang resultset tiếp tiếp theo
				result = pre.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);// không được đóng bản ghi hiện tại, giữ bản ghi hiện tại
			}while(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

}
