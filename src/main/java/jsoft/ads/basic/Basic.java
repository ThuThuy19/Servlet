package jsoft.ads.basic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jsoft.*;


public interface Basic extends ShareControl{
	
	//PreparedStatement pre - đã được biên dịch, đã truyền đầy đủ giá trị
	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	public ResultSet get(String sql, int id);
	public ResultSet get(String sql, String name, String pass);
	public ResultSet gets(String sql);
	public ResultSet[] gets(String[] sql);
	public ArrayList<ResultSet> getMR(String multiSelect);
}
