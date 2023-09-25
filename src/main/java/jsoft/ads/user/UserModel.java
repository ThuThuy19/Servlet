package jsoft.ads.user;
import jsoft.*;
import jsoft.library.ORDER;
import jsoft.object.*;
import java.sql.*;
import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
public class UserModel {
	private User u;
	public UserModel(ConnectionPool cp) {
		this.u =new UserImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.u.getCP(); 
	}
	
	public void releaseConnection() {
		this.u.releaseConnection();
	}

	//---------------------------------------
	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}
	public boolean editUser(UserObject item) {
		return this.u.editUser(item);
	}
	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}
	
	//-------------------
	public UserObject getUserObject(int id) {
		UserObject item =null;
		
		ResultSet rs= this.u.getUser(id);
		if(rs != null) {
			try {
				if(rs.next()){
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modifier"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public UserObject getUserObject(String username, String userpass) {
		UserObject item =null;
		
		ResultSet rs= this.u.getUser(username,userpass);
		if(rs != null) {
			try {
				if(rs.next()){
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modifier"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					
					
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	
	public Pair<ArrayList<UserObject>,Short> getUserObject(Triplet<UserObject, Integer, Byte> infos,Pair<USER_SORT,ORDER> so) {
		ArrayList<UserObject> items =new ArrayList<>();
		
		UserObject item =null;
		
		ArrayList<ResultSet> res= this.u.getUsers(infos,so);
		ResultSet rs= res.get(0);//Lấy câu lệnh select thứ 1 trong Uerimpl getUsers
		if(rs != null) {
			try {
				while(rs.next()){
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs=res.get(1);//Lấy câu lệnh select thu 2
		short total=0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total =rs.getShort("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items,total);
	}
	
	public Pair<ArrayList<UserObject>,Short> getUserObject(Quartet<UserObject, Integer, Byte,USER_SORT> infos) {
		ArrayList<UserObject> items =new ArrayList<>();
		
		UserObject item =null;
		
		ArrayList<ResultSet> res= this.u.getUsers(infos);
		ResultSet rs= res.get(0);//Lấy câu lệnh select thứ 1 trong Uerimpl getUsers
		if(rs != null) {
			try {
				while(rs.next()){
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_created_date(rs.getString("user_created_date"));
					item.setUser_last_modified(rs.getString("user_last_modified"));
					item.setUser_homephone(rs.getString("user_homephone"));
					item.setUser_mobilephone(rs.getString("user_mobilephone"));
					item.setUser_permission(rs.getByte("user_permission"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs=res.get(1);//Lấy câu lệnh select thu 2
		short total=0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total =rs.getShort("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items,total);
	}
	
}
