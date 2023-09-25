package jsoft.ads.user;
import jsoft.ShareControl;
import jsoft.ads.basic.Basic;
import jsoft.library.*;
import jsoft.object.*;
import java.util.*;
import org.javatuples.*;
import java.sql.*;
public interface User extends ShareControl{
	//Các chức năng cập nhật
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item);
	public boolean delUser(UserObject item);
	
	//Các chức năng lấy dữ liệu
	public ResultSet getUser(int id);
	public ResultSet getUser(String username,String userpass);
	//public ResultSet getUsers(UserObject similar,int at,byte total);
	
	//public ResultSet getUser(UserObject similar, Integer at, Byte total);
	
	public ArrayList<ResultSet> getUsers(Triplet<UserObject,Integer,Byte> infos,Pair<USER_SORT,ORDER> to);
	
	public ArrayList<ResultSet> getUsers(Quartet<UserObject, Integer, Byte, USER_SORT> infos);
}
