package com.yedam.cafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PutProductServlet")
public class PutProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PutProductServlet() {
        super();

    }

    // 홈페이지 오른쪽상단 'Service' tab 누를때 동작하는 servlet.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// request: webpage에서 넘어온 파라미터 값을 String에 담아주겠다.
		String itemNo = request.getParameter("itemNo"); 
		String itemName = request.getParameter("itemName"); 
		String price = request.getParameter("price"); 
		String itemDesc = request.getParameter("itemDesc"); 
		String likeIt = request.getParameter("likeIt"); 
		String category = request.getParameter("category"); 
		String itemImg = request.getParameter("itemImg"); 
				
		ProductVO prd = new ProductVO();
		prd.setItemNo(itemNo);
		prd.setItemName(itemName);
		prd.setPrice(Integer.parseInt(price));	// string -> int 파싱
		prd.setItemDesc(itemDesc);
		prd.setLikeIt(Double.parseDouble(likeIt));	// string -> double type 파싱
		prd.setCategory(category);;
		prd.setItemImg(itemImg);
		
		
		ProductDAO dao = new ProductDAO();
		dao.insertProduct(prd);
		
		PrintWriter out= response.getWriter();	//getWriter 는 interface
//		out.print("<script>alert(\"OK\")</script>");
		out.print("<script>location.href=\"cafe/index.html\";</script>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
