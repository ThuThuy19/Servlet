package jsoft.ads.acticle;

import java.util.ArrayList;

import jsoft.object.ArticleObject;

public class ArticleLibrary {
	public static String viewArcticle(ArrayList<ArticleObject> items) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Table with stripped rows</h5>\n");
	
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">STT</th>");
		tmp.append("<th scope=\"col\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\">Tiêu đề</th>");
		tmp.append("<th scope=\"col\">Tác giả</th>");
		tmp.append("<th scope=\"col\">ID Category</th>");
		tmp.append("<th scope=\"col\">ID Section</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("</tr>");
		tmp.append("</thead>\n");
		
		tmp.append("<tbody>");
		items.forEach(item->{
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+(items.indexOf(item)+1)+"</th>");
			tmp.append("<td>"+item.getArticle_created_date()+"</td>");
			tmp.append("<td>"+item.getArticle_title()+"</td>");
			tmp.append("<td>"+item.getArticle_author_name()+"</td>");
			tmp.append("<td>"+item.getArticle_category_id()+"</td>");
			tmp.append("<td>"+item.getArticle_section_id()+"</td>");
			tmp.append("<td>Chi tiết</td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("<th scope=\"row\">"+item.getArticle_id()+"</th>");
			tmp.append("</tr>\n");
		});
		tmp.append("</tbody>");
		tmp.append("</table>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp.toString();
	}
}
