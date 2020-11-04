package com.yedam.cafe;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

// 메인에서 사진 선택했을 떄, 띄워지는 창에서 불러오는 정보.

@WebServlet("/GetProdSelectServlet")
public class GetProdSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProdSelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String item = request.getParameter("item_no");
		ProductDAO dao = new ProductDAO();
		ProductVO prd = dao.getProduct(item);

		// 선택된 item_no의 컬럼값을 json type의 배열에 담아서 response해줌.
		JSONArray jAry = new JSONArray();

		jAry.add(prd);

		response.getWriter().append(JSONArray.fromObject(jAry).toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
