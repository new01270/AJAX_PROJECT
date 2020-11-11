package com.yedam.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PutBookUpdateServ")
public class PutBookUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PutBookUpdateServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		
		BookDAO bo = new BookDAO();
		BookVO book = new BookVO(Integer.parseInt(no), title, author, Integer.parseInt(price));
		bo.updateBookList(book);
		
		response.getWriter().append(no);	// 수정 후 변경버튼 기능 때 넘겨주는 parameter
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
