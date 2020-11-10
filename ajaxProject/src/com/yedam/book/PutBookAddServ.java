package com.yedam.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PutBookAddServ")
public class PutBookAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PutBookAddServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		
		// 받아온 parameter값을 VO에 맞춰넣어 DAO의 DB컬럼에 넣어준다.
		BookDAO bo = new BookDAO();
		BookVO book = new BookVO( title, author, Integer.parseInt(price));
		String sqe = bo.bookListSeq(book);
		
		response.getWriter().append(sqe);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
