package jsoft.ads.category;
import java.sql.*;
import java.util.ArrayList;

import jsoft.library.*;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.object.*;
public interface Category {
	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);

	public ResultSet getCategory(short id,UserObject user);
	//public ResultSet getCategories(CategoryObject similar,int at,byte total);
	
	public ArrayList<ResultSet> getCategories(Triplet<CategoryObject,Integer,Byte> infos, Pair<CATEGORY_SORT,ORDER> so);
}
