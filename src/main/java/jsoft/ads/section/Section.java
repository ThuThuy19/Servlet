package jsoft.ads.section;
import java.util.*;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.sql.*;
import jsoft.object.*;
import jsoft.ShareControl;
import jsoft.library.*;

public interface Section extends ShareControl{
	public boolean addSection(SectionObject item);
	public boolean editSection(SectionObject item);
	public boolean delSection(SectionObject item);

	public ResultSet getSection(int id,UserObject userLogined);
	public ResultSet getSections(SectionObject similar,int at,byte total);
	
	//public ResultSet getSections(Triplet<SectionObject,Integer,Byte> infos);
	public ArrayList<ResultSet> getSections(Triplet<SectionObject,Integer,Byte> infos,Pair<SECTION_SORT, ORDER> so);
	public ArrayList<ResultSet> getSections(Quartet<SectionObject,Integer,Byte ,SECTION_SORT> infos);

}
