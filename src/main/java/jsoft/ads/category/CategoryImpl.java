package jsoft.ads.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.ads.user.User;
import jsoft.library.ORDER;
import jsoft.ads.user.UserImpl;
import jsoft.object.CategoryObject;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class CategoryImpl extends BasicImpl implements Category {

	public CategoryImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcategory (");
		sql.append("category_name, category_section_id, category_notes, category_created_date, ");
		sql.append("category_created_author_id, category_last_modified, category_manager_id, category_enable, category_delete, ");
		sql.append("category_image, category_name_en, category_language ) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getCategory_name());
			pre.setInt(2,item.getCategory_section_id());
			pre.setString(3,item.getCategory_notes());
			pre.setString(4,item.getCategory_created_date());
			
			pre.setInt(5,item.getCategory_created_author_id());
			pre.setString(6,item.getCategory_last_modified());
			pre.setInt(7,item.getCategory_manager_id());
			pre.setBoolean(8,item.isCategory_enable());
			pre.setBoolean(9,item.isCategory_delete());
			
			pre.setString(10,item.getCategory_image());
			pre.setString(11,item.getCategory_name_en());
			pre.setByte(12,item.getCategory_language());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblcategory SET ");
		sql.append("category_name = ?, category_section_id = ?, category_notes = ?, category_created_date = ?, ");
		sql.append("category_created_author_id = ?, category_last_modified = ?, category_manager_id = ?, category_enable = ?, category_delete = ?, ");
		sql.append("category_image = ?, category_name_en = ?, category_language = ? ");
		sql.append("WHERE category_id = ? ;");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getCategory_name());
			pre.setInt(2,item.getCategory_section_id());
			pre.setString(3,item.getCategory_notes());
			pre.setString(4,item.getCategory_created_date());
			
			pre.setInt(5,item.getCategory_created_author_id());
			pre.setString(6,item.getCategory_last_modified());
			pre.setInt(7,item.getCategory_manager_id());
			pre.setBoolean(8,item.isCategory_enable());
			pre.setBoolean(9,item.isCategory_delete());
			
			pre.setString(10,item.getCategory_image());
			pre.setString(11,item.getCategory_name_en());
			pre.setByte(12,item.getCategory_language());
			pre.setInt(13, item.getCategory_id());
			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tblcategory WHERE category_id = ?";
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getCategory_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	
	
	@Override
	public ResultSet getCategory(short id,UserObject user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM tblcategory c ");
		sql.append(" LEFT JOIN tblsection s ON c.category_section_id = s.section_id ");
		sql.append(" WHERE (c.category_id=?) AND (( c.category_created_author_id="+user.getUser_id()+") OR (c.category_manager_id="+user.getUser_id()+"))");
		return this.get(sql.toString(), id);
		
	}

	@Override
	public ArrayList<ResultSet> getCategories(Triplet<CategoryObject, Integer, Byte> infos, Pair<CATEGORY_SORT, ORDER> so) {
		// TODO Auto-generated method stub
		SectionObject similar = infos.getValue0();
		
		int at = infos.getValue1();
		
		byte totalperpage = infos.getValue2();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblcategory AS c ");
		
		sql.append("LEFT JOIN tblsection AS s ON c.category_section_id = s.section_id ");
		sql.append("LEFT JOIN tbluser AS u ON c.category_manager_id = u.user_id ");
		
		sql.append("");
		switch(so.getValue0()) {
			case NAME: sql.append(" ORDER BY c.category_name ").append(so.getValue1().name());break;
			case MANAGER:sql.append(" ORDER BY section_manager_id ").append(so.getValue1().name());break;
			default: sql.append(" ORDER BY c.category_id DESC");
		}
		
		sql.append(" LIMIT ").append(at).append(", ").append(totalperpage).append(" ; ");
		sql.append(" SELECT COUNT(section_id) AS total FROM tblcategory ;");
		
		return this.getMR(sql.toString());
		
	}
	
	public static void main(String[] arg) {
		ConnectionPool cp = new ConnectionPoolImpl();
		Category c = new CategoryImpl(cp, "Section");
		CategoryObject cte = new CategoryObject();
		
		User u = new UserImpl(cp);
		UserObject uo= new UserObject();
		uo.setUser_id(20);
		
		//cte.setCategory_id((short)50);
		
		
		
		ResultSet rs = c.getCategory((short)36,uo);
		if(rs!=null) {
			try {
				while(rs.next()) {
					String row =new String();
					row+="ID: "+ rs.getInt("category_id");
					row+="\tLAST MODIFIED: "+rs.getString("category_last_modified");
					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("-----KHONG CO BAN GHI-----------");
		}
	}

}
