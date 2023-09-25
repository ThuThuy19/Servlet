package jsoft.ads.category;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.library.ORDER;
import jsoft.object.CategoryObject;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class CategoryControl {
	private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}
	
	//-------------
	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}
	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}
	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}
	//----------------
	
	public SectionObject getSectionObject(int id,UserObject userLogined) {
		return this.cm.getCategoryObject(id, userLogined);
	}
	public ArrayList<String> getCategorys(Triplet<CategoryObject,Integer,Byte> infos,Pair<CATEGORY_SORT, ORDER> so) {
		Pair<ArrayList<CategoryObject>,Short> datas=this.cm.getCategorys(infos, so);
		
		ArrayList<String> views = new ArrayList<>();
		views.add(CategoryLibrary.viewsCategory(datas.getValue0()));
		
		return views;		
	}
	public static void main(String[] args) {
		ConnectionPool cp= new ConnectionPoolImpl();
		CategoryControl cc = new CategoryControl(cp);
		
		Triplet<CategoryObject, Integer, Byte> infos = new Triplet<>(null, 0, (byte)15);
		ArrayList<String> views = cc.getCategorys(infos, new Pair<CATEGORY_SORT, ORDER>(CATEGORY_SORT.NAME,ORDER.DESC));
		System.out.print(views);
	}
}
