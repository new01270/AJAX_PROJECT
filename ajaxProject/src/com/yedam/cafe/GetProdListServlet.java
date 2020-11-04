package com.yedam.cafe;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

@WebServlet("/GetProdListServlet")
public class GetProdListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProdListServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨짐 현상 보완.
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cat = request.getParameter("category");	//cat:index.html 통해서 넘어오는 카테고리명;
		ProductDAO dao = new ProductDAO();
		List<ProductVO> list = dao.getProductList(cat);

		JSONArray jAry = new JSONArray();
		for (ProductVO prod : list) {
			jAry.add(prod);
		}
		
		response.getWriter().append(JSONArray.fromObject(jAry).toString()); //string type이어야 해서 jAray를 입력 한 후, toString().

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
