package jsoft.ads.user;

import jsoft.*;
import jsoft.library.ORDER;
import jsoft.object.*;

import java.util.*;
import org.javatuples.*;

public class UserControl {
	private UserModel um;
	
	public UserControl(ConnectionPool cp) {
		this.um= new UserModel(cp);
	}
	public ConnectionPool getCP() {
		return this.um.getCP(); 
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	//---------------------------------------
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}
	public boolean editUser(UserObject item) {
		return this.um.editUser(item);
	}
	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	//-------------------
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	public UserObject getUserObject(String Username, String UserPass) {
		return this.um.getUserObject(Username, UserPass);
	}
	public ArrayList<String> viewUsers(Triplet<UserObject, Integer, Byte> infos,Pair<USER_SORT,ORDER> so){
		Pair<ArrayList<UserObject>,Short> datas = this.um.getUserObject(infos, so);
		
		ArrayList<String> views= new ArrayList<>();
		views.add(UserLibrary.viewUsers(datas.getValue0()));
		
		return views;
		
	}
	
	public ArrayList<String> viewUsers(Quartet<UserObject, Short, Byte, USER_SORT > infos){
		int at = (infos.getValue1() - 1) * infos.getValue2();

		Quartet<UserObject, Integer, Byte, USER_SORT> infos2 = new Quartet<>(infos.getValue0(), at, infos.getValue2(),
				infos.getValue3());
		
		Pair<ArrayList<UserObject>,Short> datas = this.um.getUserObject(infos2);
		
		
		ArrayList<String> views= new ArrayList<>();
		
		views.add(UserLibrary.viewUsers(datas.getValue0()));
		
		return views;
		
	}
	
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		UserControl uc = new UserControl(cp);
		
		Triplet<UserObject, Integer, Byte> infos= new Triplet<>(null, 0, (byte)10);
		ArrayList<String> views = uc.viewUsers(infos,new Pair<USER_SORT, ORDER>(USER_SORT.FULLNAME, ORDER.DESC));
		
		Quartet<UserObject, Short, Byte,USER_SORT> infos2= new Quartet<>(null, (short)3, (byte)10, USER_SORT.FULLNAME);
		ArrayList<String> views2 = uc.viewUsers(infos2);
		
		uc.releaseConnection();// yêu cầu trả về kết nối 
		
		System.out.println(views2);
	}
}
