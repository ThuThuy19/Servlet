package jsoft.ads.acticle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.library.ORDER;
import jsoft.object.ArticleObject;

public class ArticleModel {
	private Article a;
	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp, "Acticle");
	}
	
	//-------------
	public boolean addArticle(ArticleObject item) {
		return this.addArticle(item);
	}
	public boolean editArticle(ArticleObject item) {
		return this.editArticle(item);
	}
	public boolean delArticle(ArticleObject item) {
		return this.delArticle(item);
	}
	//-------------
	
	public Pair<ArrayList<ArticleObject>,Short> getArticles(Triplet<ArticleObject,Integer,Byte> infos,Pair<ARTICLE_SORT,ORDER> to){
		ArrayList<ArticleObject> items = new ArrayList<>();
		ArticleObject item = null;
		ArrayList<ResultSet> res = this.a.getArticles(infos, to);
		ResultSet rs = res.get(0);
		if(rs!=null) {
			try {
				while(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getShort("article_id"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_category_id(rs.getShort("article_category_id"));
					item.setArticle_section_id(rs.getShort("article_section_id"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					
					items.add(item); 
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs=res.get(1);
		short total = 0;
		if(rs!=null) {
			try {
				if(rs.next()) {
					total = rs.getShort("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items,total);
	}

}
