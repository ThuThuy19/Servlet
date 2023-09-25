package jsoft.ads.acticle;
import java.sql.*;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.library.ORDER;
import jsoft.object.*;
public interface Article {
	public boolean addArticle(ArticleObject item);
	public boolean editArticle(ArticleObject item);
	public boolean delArticle(ArticleObject item);

	public ResultSet getacticle(short id);
	//public ResultSet getArticles(ArticleObject similar,int at,byte total);
	
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject,Integer,Byte> infos,Pair<ARTICLE_SORT,ORDER> to);
}
