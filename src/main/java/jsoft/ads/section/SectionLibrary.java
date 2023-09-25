package jsoft.ads.section;

import java.util.ArrayList;

import jsoft.object.SectionObject;

public class SectionLibrary {
	public static String viewSection(ArrayList<SectionObject> items) {
		StringBuilder tmp= new StringBuilder();
		
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Table with stripped rows</h5>\n");
	
		tmp.append("<table class=\"table table-striped\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\">STT</th>");
		tmp.append("<th scope=\"col\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\">Tên</th>");
		tmp.append("<th scope=\"col\">ID người quản lý</th>");
		tmp.append("<th scope=\"col\">ID người tạo</th>");
		tmp.append("<th scope=\"col\">Lưu ý</th>");
		tmp.append("<th scope=\"col\" colspan=\"3\">Thực hiện</th>");
		tmp.append("<th scope=\"col\">#</th>");
		tmp.append("</tr>");
		tmp.append("</thead>\n");
		
		tmp.append("<tbody>");
		
		items.forEach(item->{
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\">"+(items.indexOf(item)+1)+"</th>");
			
			tmp.append("<td>"+item.getSection_created_date()+"</td>");
			tmp.append("<td>"+item.getSection_name()+"</td>");
			tmp.append("<td>"+item.getSection_manager_id()+"</td>");
			tmp.append("<td>"+item.getSection_created_author_id()+"</td>");
			tmp.append("<td>"+item.getSection_notes()+"</td>");
			tmp.append("<td> Chi tiết </td>");
			tmp.append("<td>Sửa</td>");
			tmp.append("<td>Xóa</td>");
			tmp.append("<th scope=\"row\">"+item.getSection_id()+"</th>");
			tmp.append("</tr>\n");
			
		});
		tmp.append("</tbody>");
		tmp.append("</table>");
		tmp.append("</div>");
		return tmp.toString();
	}
	
}
