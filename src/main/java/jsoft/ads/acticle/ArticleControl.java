package jsoft.ads.acticle;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.library.ORDER;
import jsoft.object.ArticleObject;

public class ArticleControl {
	private ArticleModel am;
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	//------
	public boolean addArticle(ArticleObject item) {
		return this.am.addArticle(item);
	}
	public boolean editArticle(ArticleObject item) {
		return this.am.editArticle(item);
	}
	public boolean delArticle(ArticleObject item) {
		return this.am.delArticle(item);
	}
	//----------
	
	public ArrayList<String> getArticles(Triplet<ArticleObject,Integer,Byte> infos,Pair<ARTICLE_SORT,ORDER> to){
		Pair<ArrayList<ArticleObject>,Short> datas = this.am.getArticles(infos, to);
		ArrayList<String> views = new ArrayList<>();
		views.add(ArticleLibrary.viewArcticle(datas.getValue0()));
		return views;
	}
	public static void main(String[] arg) {
		ConnectionPool cp= new ConnectionPoolImpl();
		ArticleControl ac= new ArticleControl(cp);
		
		Triplet<ArticleObject, Integer, Byte> infos = new Triplet<ArticleObject, Integer, Byte>(null, 0, (byte)17);
		ArrayList<String> views = ac.getArticles(infos, new Pair<ARTICLE_SORT, ORDER>(ARTICLE_SORT.ID, ORDER.ASC));
		System.out.print(views);
	}
}
