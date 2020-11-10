package com.yedam.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/GetBookServlet")
public class GetBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		JSONArray jAry = new JSONArray();
		
		BookDAO bo = new BookDAO();
		List<BookVO> list = bo.getBookList();
		
		// DB data를 json type으로 변환.
		for(BookVO books : list) {
			JSONObject obj = new JSONObject();
			obj.put("bookNo", books.getBookNo());
			obj.put("bookTitle", books.getBookTitle());
			obj.put("bookAuthor", books.getBookAuthor());
			obj.put("price", books.getPrice());
			jAry.add(obj);
		}
		
		response.getWriter().append(JSONArray.fromObject(jAry).toString());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
