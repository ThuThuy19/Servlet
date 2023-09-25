package jsoft.ads.section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.library.ORDER;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class SectionModel {
	private Section s;
	public SectionModel(ConnectionPool cp) {
		this.s = new SectionImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.s.getCP(); 
	}
	
	public void releaseConnection() {
		this.s.releaseConnection();
	}
	//------------
	public boolean addSection(SectionObject item) {
		return this.s.addSection(item);
	}
	public boolean editSection(SectionObject item) {
		return this.s.editSection(item);
	}
	public boolean delSection(SectionObject item) {
		return this.s.delSection(item);
	}
	//---------------
	
	public SectionObject getSectionObject(int id,UserObject userLogined) {
		SectionObject item = null;
		
		ResultSet rs = this.s.getSection(id, userLogined);
		if(rs!=null) {
			try {
				if(rs.next()) {
			
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getNString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_manager_id(rs.getShort("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_last_modified(rs.getString("last_modified"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
					item.setSection_id(rs.getShort("section_id"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	public SectionObject getSectionObject(SectionObject similar,int at,byte total) {
		SectionObject item=null;
		return item;
	}
	
	public ArrayList<SectionObject> getSectionObjects(Triplet<SectionObject, Short, Byte>infos, Pair<SECTION_SORT, ORDER> so){
		SectionObject similar = infos.getValue0();
		int at = (infos.getValue1()-1*infos.getValue2());
		byte total = infos.getValue2();
		Triplet<SectionObject, Integer, Byte> infos2 = new Triplet<>(similar, at, total);
		
		ArrayList<SectionObject> items = new ArrayList<>();
		
		SectionObject item = null;
		ArrayList<ResultSet> res = this.s.getSections(infos2, so);
		ResultSet rs=res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
			
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_manager_id(rs.getShort("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
					item.setSection_id(rs.getShort("section_id"));
					
					items.add(item);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return items;
	}
	
	public Pair<ArrayList<SectionObject>,Short> getSectionObject(Triplet<SectionObject,Integer,Byte> infos,Pair<SECTION_SORT, ORDER> so) {
		ArrayList<SectionObject> items= new ArrayList<>();
		
		SectionObject item=null;
		
		ArrayList<ResultSet> res = this.s.getSections(infos, so);
		
		ResultSet rs=res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
			
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_manager_id(rs.getShort("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
					item.setSection_id(rs.getShort("section_id"));
					
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
	
	public Pair<ArrayList<SectionObject>,Short> getSectionObject(Quartet<SectionObject,Integer,Byte,SECTION_SORT> infos) {
		ArrayList<SectionObject> items= new ArrayList<>();
		
		SectionObject item=null;
		
		ArrayList<ResultSet> res = this.s.getSections(infos);
		
		ResultSet rs=res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
			
					item = new SectionObject();
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_manager_id(rs.getShort("section_manager_id"));
					item.setSection_enable(rs.getBoolean("section_enable"));
					item.setSection_last_modified(rs.getString("section_last_modified"));
					item.setSection_name_en(rs.getString("section_name_en"));
					item.setSection_language(rs.getByte("section_language"));
					item.setSection_id(rs.getShort("section_id"));
					
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
