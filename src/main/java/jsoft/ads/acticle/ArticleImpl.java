package jsoft.ads.acticle;

import java.sql.PreparedStatement;
import jsoft.library.ORDER;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.ads.user.USER_SORT;
import jsoft.library.ORDER;
import jsoft.object.ArticleObject;
import jsoft.object.SectionObject;
import jsoft.object.UserObject;

public class ArticleImpl extends BasicImpl implements Article {

	public ArticleImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblarticle (");
		sql.append("article_title, article_summary, article_content, article_created_date, ");
		sql.append("article_last_modified, article_image, article_category_id, article_section_id, article_visited, ");
		sql.append("article_author_name, article_enable, article_url_link, article_tag, article_title_en, ");
		sql.append("article_summary_en, article_content_en, article_tag_en, article_fee, article_isfee, ");
		sql.append("article_delete, article_deleted_date, article_restored_date, article_modified_author_name, article_author_permission, ");
		sql.append("article_source, article_language, article_focus, article_type, article_forhome ) ");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setShort(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.isArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.getArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(22, item.getArticle_restored_date());
			pre.setString(23, item.getArticle_modified_author_name());
			pre.setByte(24, item.getArticle_author_permission());
			pre.setString(25, item.getArticle_source());
			pre.setByte(26, item.getArticle_language());
			pre.setBoolean(27, item.isArticle_focus());
			pre.setByte(28, item.getArticle_type());
			pre.setBoolean(29, item.isArticle_forhome());
			
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblarticle SET ");
		sql.append("article_title = ?, article_summary = ?, article_content = ?, article_created_date = ?, ");
		sql.append("article_last_modified = ?, article_image = ?, article_category_id = ?, article_section_id = ?, article_visited = ?, ");
		sql.append("article_author_name = ?, article_enable = ?, article_url_link = ?, article_tag = ?, article_title_en = ?, ");
		sql.append("article_summary_en = ?, article_content_en = ?, article_tag_en = ?, article_fee = ?, article_isfee = ?, ");
		sql.append("article_delete = ?, article_deleted_date = ?, article_restored_date = ?, article_modified_author_name = ?, article_author_permission = ?, ");
		sql.append("article_source = ?, article_language = ?, article_focus = ?, article_type = ?, article_forhome = ?  ");
		sql.append("WHERE article_id = ? ");
		sql.append(";");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setShort(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.isArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.getArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(22, item.getArticle_restored_date());
			pre.setString(23, item.getArticle_modified_author_name());
			pre.setByte(24, item.getArticle_author_permission());
			pre.setString(25, item.getArticle_source());
			pre.setByte(26, item.getArticle_language());
			pre.setBoolean(27, item.isArticle_focus());
			pre.setByte(28, item.getArticle_type());
			pre.setBoolean(29, item.isArticle_forhome());
			pre.setInt(30, item.getArticle_id());
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
	public boolean delArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblarticle WHERE article_id=?");
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getArticle_id());
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
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Integer, Byte> infos,Pair<ARTICLE_SORT,ORDER> to) {
		// TODO Auto-generated method stub
		ArticleObject similar = infos.getValue0();
		
		int at = infos.getValue1();
		byte total = infos.getValue2();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tblarticle ");
		sql.append(" LEFT JOIN tblcategory ON article_category_id = category_id");
		sql.append(" LEFT JOIN tblcategory ON category_section_id = section_id");
		sql.append("");
		switch (to.getValue0()){
			case NAME: sql.append(" ORDER BY article_title ").append(to.getValue1().name());break;
			case ID: sql.append(" ORDER BY article_id  ").append(to.getValue1().name());break;
			default : sql.append(" ORDER BY article_id ASC ");break;
			
		}
		sql.append(" LIMIT ").append(at).append(", ").append(total).append(" ;");
		
		//tổng số bản ghi để phân trang
		sql.append(" SELECT COUNT(article_id) AS total FROM tblarticle ;");
		
		
		return this.getMR(sql.toString());
	}
	
	@Override
	public ResultSet getacticle(short id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tblarticle ");
		sql.append(" LEFT JOIN tblcategory ON article_category_id = category_id ");
		sql.append(" LEFT JOIN tblcategory ON category_section_id = section_id");
		
		return this.get(sql.toString(), id);
	}
	
	public static void main(String[] args) {
		ConnectionPool con = new ConnectionPoolImpl();
		
		Article a = new ArticleImpl(con, "Article");
		
		ArticleObject ar = new ArticleObject();
		ar.setArticle_title("Bao Cao");
		ar.setArticle_category_id((short)70);
		ar.setArticle_section_id((short)44);
		ar.setArticle_author_name("Dinh Van Huy");
		
		//a.addArticle(ar);
		
		boolean result = a.addArticle(ar);
		if(!result) {
			System.out.println("\n------------KHÔNG THÀNH CÔNG---------------");
		}
		
		//Lấy tập kết quả (danh sách người sử dụng)
				Triplet<ArticleObject, Integer, Byte> infos= new Triplet<>(null, 0, (byte)10);
				
				ArrayList<ResultSet> res = a.getArticles(infos, new Pair<ARTICLE_SORT, ORDER> (ARTICLE_SORT.NAME, ORDER.ASC));
				
				ResultSet rs = res.get(0);//Lấy ra danh sách các bản ghi
				
				String row ;
				if(rs!=null) {
					try {
						while(rs.next()) {
							row= "\nID: "+rs.getInt("article_id");
							row+= "\tTITLE: "+rs.getString("article_title");
							row += "\tCATEGORY ID: "+rs.getString("article_category_id");
							row += "\tAUTHOR NAME: "+rs.getString("article_author_name");
							System.out.println(row);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				rs= res.get(1);//Lấy ra tổng số bản ghi
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

	

}
