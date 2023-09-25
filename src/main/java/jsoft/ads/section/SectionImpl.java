package jsoft.ads.section;

import java.sql.PreparedStatement;
import jsoft.ads.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.ads.user.USER_SORT;
import jsoft.library.ORDER;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class SectionImpl extends BasicImpl implements Section {
	
	

	public SectionImpl(ConnectionPool cp) {
		super(cp, "Section");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub
		if(this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql =new StringBuilder();
		sql.append("INSERT INTO tblsection (");
		sql.append(" section_name, section_notes, section_created_date, section_manager_id, ");
		sql.append(" section_enable, section_delete, section_last_modified, section_created_author_id, section_name_en,");
		sql.append(" section_language )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			
			pre.setByte(10, item.getSection_language());
			
			return this.add(pre);
		}catch(SQLException ex) {
			ex.printStackTrace();
			
			try {
				this.con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tblsection SET ");
		sql.append("section_name=? , section_notes=?, section_manager_id=?, ");
		sql.append(" section_enable=?, section_last_modified=?, section_name_en=?,");
		sql.append(" section_language=?");
		sql.append("WHERE section_id = ?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());			
			pre.setString(1, item.getSection_name());			
			pre.setString(2, item.getSection_notes());			
			pre.setInt(3, item.getSection_manager_id());			
			pre.setBoolean(4, item.isSection_enable());		
			pre.setString(5, item.getSection_last_modified());			
			pre.setString(6, item.getSection_name_en());			
			pre.setByte(7, item.getSection_language());
			pre.setShort(8, item.getSection_id());
			return this.edit(pre);
			
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	private boolean isExisting(SectionObject item) {
		boolean flag =false;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblsection WHERE section_name = '").append(item.getSection_name()).append("' ;");
		ResultSet rs = this.get(sql.toString(),0);
		if(rs!=null) {
			try {
				if(rs.next()) {
					flag=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	private boolean isEmpty(SectionObject item) {
		boolean flag = true ;
		String sql  = new String();
		sql="SELECT category_id FROM tblcategory WHERE category_section_id = ?";
		
		ResultSet rs =  this.get(sql,item.getSection_id());
		
		if(rs!=null) {
			try {
				if(rs.next()) {
					flag=false;
				}rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return flag;
	}
	
	

	@Override
	public boolean delSection(SectionObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblsection WHERE (section_id= ?) AND (section_created_author_id=?) OR (section_manager_id=?))");
		if(!this.isEmpty(item)) {
			return false;
		}
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setShort(1, item.getSection_id());
			pre.setInt(2,item.getSection_created_author_id());
			pre.setInt(3, item.getSection_manager_id());
			
			return this.del(pre);
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ArrayList<ResultSet> getSections(Triplet<SectionObject, Integer, Byte> infos, Pair<SECTION_SORT, ORDER> so) {
		// TODO Auto-generated method stub
		SectionObject similar = infos.getValue0();
		
		int at = infos.getValue1();
		
		byte totalperpage = infos.getValue2();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblsection s ");
		
		sql.append("LEFT JOIN tbluser u ON s.section_manager_id = u.user_id ");
		
		sql.append("");
		switch(so.getValue0()) {
			case NAME: sql.append(" ORDER BY section_name "+so.getValue1().name());break;
			case MANAGER:sql.append(" ORDER BY section_manager_id "+so.getValue1().name());break;
			default: sql.append(" ORDER BY section_id DESC");
		}
		
		sql.append(" LIMIT ").append(at).append(", ").append(totalperpage).append(" ; ");
		sql.append(" SELECT COUNT(section_id) AS total FROM tblsection ;");
		
		return this.getMR(sql.toString());
		
	}

	@Override
	public ResultSet getSection(int id,UserObject userlogined) {
		// TODO Auto-generated method stub
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT * FROM tblsection AS s ");
		sql.append("LEFT JOIN tbluser AS u ON s.section_manager_id=u.user_id ");
		sql.append("WHERE (s.section_id=?) AND (s.section_manager_id = '").append(userlogined.getUser_id()).append("') ");
		return this.get(sql.toString(), id);
	}

	@Override
	public ResultSet getSections(SectionObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		
		Section s = new SectionImpl(cp);
		
		SectionObject section = new SectionObject();
		section.setSection_name("Buon ban");
		
		boolean result  = s.addSection(section);
		if(!result) {
			System.out.println("---------Khong thanh cong --------------");
		}
		
		Triplet<SectionObject, Integer, Byte> infos = new Triplet<>(null, 0, (byte)50);
		
		ArrayList<ResultSet> res = s.getSections(infos,new Pair<SECTION_SORT, ORDER>(SECTION_SORT.NAME, ORDER.DESC));
		

		ResultSet rs = res.get(0);
		String row;
		if(rs!=null) {
			try {
				while(rs.next()) {
					row = "ID: "+rs.getInt("section_id");
					row+="\tNAME: "+rs.getString("section_name");
					row+="\tFULLNAME: "+rs.getString("section_notes");
					System.out.println(row);
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		rs=res.get(1);
		if(rs!=null) {
			try {
				if(rs.next()) {
					System.out.print("Tổng số tài khoản trong CSDL là: "+rs.getShort("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<ResultSet> getSections(Quartet<SectionObject, Integer, Byte, SECTION_SORT> infos) {
		SectionObject similar = infos.getValue0();
		
		int at = infos.getValue1();
		
		byte totalperpage = infos.getValue2();
		
		SECTION_SORT so = infos.getValue3();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblsection s ");
		
		sql.append("LEFT JOIN tbluser u ON s.section_manager_id = u.user_id ");
		
		sql.append("");
		switch(so) {
			case NAME: sql.append("order by user_name ASC ");
			;break;
			case MANAGER:sql.append("order by user_fullname ASC ");;
			break;
			default: sql.append(" ORDER BY section_id DESC");
		}
		
		sql.append(" LIMIT ").append(at).append(", ").append(totalperpage).append(" ; ");
		sql.append(" SELECT COUNT(section_id) AS total FROM tblsection ;");
		
		return this.getMR(sql.toString());
	}

	

	

}
