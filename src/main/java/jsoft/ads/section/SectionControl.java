package jsoft.ads.section;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.library.ORDER;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class SectionControl {
	private SectionModel sm;
	
	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}
	public ConnectionPool getCP() {
		return this.sm.getCP(); 
	}
	
	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	//-----4
	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}
	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}
	public boolean deleteSection(SectionObject item) {
		return this.sm.delSection(item);
	}
	//-------
	
	public SectionObject getSectionObject(int id,UserObject userLogin) {
		return this.sm.getSectionObject(id, userLogin);
	}
	public SectionObject getSectionObject(SectionObject similar,int at,byte total) {
		return this.sm.getSectionObject(similar, at, total);
	}
	public ArrayList<String> viewSection(Triplet<SectionObject,Integer,Byte> infos,Pair<SECTION_SORT, ORDER> so){
		Pair<ArrayList<SectionObject>,Short> datas = this.sm.getSectionObject(infos, so);
		ArrayList<String> views = new ArrayList<>();
		
		views.add(SectionLibrary.viewSection(datas.getValue0()));
		
		
		return views;
	}
	
	public ArrayList<String> viewSection(Quartet<SectionObject,Integer,Byte,SECTION_SORT> infos){
		Pair<ArrayList<SectionObject>,Short> datas = this.sm.getSectionObject(infos);
		ArrayList<String> views = new ArrayList<>();
		
		views.add(SectionLibrary.viewSection(datas.getValue0()));
		return views;
	}
	
	
	public static void main(String[] args) {
		ConnectionPool cp= new ConnectionPoolImpl();
		SectionControl sc = new SectionControl(cp);
		
		Triplet<SectionObject, Integer, Byte> infos = new Triplet<>(null, 0, (byte)20);
		
		ArrayList<String> views= sc.viewSection(infos, new Pair<SECTION_SORT, ORDER>(SECTION_SORT.NAME, ORDER.DESC));
		
		Quartet<SectionObject, Integer, Byte,SECTION_SORT> infos1 = new Quartet<>(null, 0, (byte)20, SECTION_SORT.NAME);
		
		ArrayList<String> views1= sc.viewSection(infos, new Pair<SECTION_SORT, ORDER>(SECTION_SORT.NAME, ORDER.DESC));
		
		System.out.print(views1);
		sc.releaseConnection();
	}
	
}
