package jsoft.ads.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.library.ORDER;
import jsoft.object.CategoryObject;
import jsoft.object.UserObject;

public class CategoryModel {
	private Category c;
	public CategoryModel(ConnectionPool cp) {
		this.c = new CategoryImpl(cp,"Category");
	}
	//------------
	public boolean addCategory(CategoryObject item) {
		return this.c.addCategory(item);
	}
	public boolean editCategory(CategoryObject item) {
		return this.c.editCategory(item);
	}
	public boolean delCategory(CategoryObject item) {
		return this.c.delCategory(item);
	}
	//---------------
	
	public CategoryObject getCategoryObject(int id,UserObject userLogined) {
		CategoryObject item = null;
		
		ResultSet rs = this.c.getCategory((short)id,userLogined);
		if(rs!=null) {
			try {
				if(rs.next()) {
			
					item = new CategoryObject();
					item.setCategory_id(rs.getShort("Category_id"));
					item.setCategory_name(rs.getNString("Category_name"));
					item.setCategory_notes(rs.getString("Category_notes"));
					item.setCategory_manager_id(rs.getShort("Category_manager_id"));
					item.setCategory_enable(rs.getBoolean("Category_enable"));
					item.setCategory_last_modified(rs.getString("last_modified"));
					item.setCategory_name_en(rs.getString("Category_name_en"));
					item.setCategory_language(rs.getByte("Category_language"));
					item.setCategory_id(rs.getShort("Category_id"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}

	public Pair<ArrayList<CategoryObject>,Short> getCategorys(Triplet<CategoryObject,Integer,Byte> infos,Pair<CATEGORY_SORT, ORDER> so) {
		ArrayList<CategoryObject> items= new ArrayList<>();
		
		CategoryObject item=null;
		
		ArrayList<ResultSet> res = this.c.getCategories(infos, so);
		
		ResultSet rs=res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
			
					item = new CategoryObject();
					item.setCategory_id(rs.getShort("Category_id"));
					item.setCategory_name(rs.getString("Category_name"));
					item.setCategory_notes(rs.getString("Category_notes"));
					item.setCategory_manager_id(rs.getShort("Category_manager_id"));
					item.setCategory_enable(rs.getBoolean("Category_enable"));
					item.setCategory_last_modified(rs.getString("Category_last_modified"));
					item.setCategory_name_en(rs.getString("Category_name_en"));
					item.setCategory_language(rs.getByte("Category_language"));
					item.setCategory_id(rs.getShort("Category_id"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs=res.get(1);//Lấy câu lệnh select đầu tiên
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
