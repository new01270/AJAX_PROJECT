package com.yedam.comments;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

/**
 * Servlet implementation class CommentsServ
 */
@WebServlet("/CommentsServ")
public class CommentsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 교환
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");
		HashMap<String, String> msg;

		if (cmd == null) { // cmd == null 일 때 화면에 보여줌
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("<code>error</code>");
			sb.append("<data><![CDATA[ ");
			sb.append("cmd null");
			sb.append("]]></data>");
			sb.append("</result>");
			out.print(sb.toString()); // print 해서 web page에 보여줌

		} else if (cmd.equals("selectAll")) {
			/*
			 * http://localhost:8088/ajaxProject/CommentsServ?cmd=selectAll 
			 * => DB에서 insert한  data받아옴
			 */
			try {
				String no = request.getParameter("no"); // 게시글번호  parameter
				List<HashMap<String, Object>> list = CommentsDAO.getInstance().selectAll(no); // 받은 no parameter넘겨줌
				out.print(selectAll(list)); //

			} catch (Exception e) {
				StringBuffer sb = new StringBuffer();
				msg = new HashMap<String, String>() {
					{
						put("msg", e.getMessage());
					}
				};
				out.print(toXML("error", msg));
			}

		} else if (cmd.equals("insert")) { // 입력부분으로 parameter.
			try {
				//
				Comments bean = new Comments();
				bean.setContent(request.getParameter("content"));
				bean.setName(request.getParameter("name"));

				HashMap<String, Object> map = CommentsDAO.getInstance().insert(bean);
				out.print(toXML("success", map));

			} catch (Exception e) {
				e.printStackTrace();
				msg = new HashMap<String, String>() {
					{
						put("msg", e.getMessage());
					}
				};
				out.print(toXML("error", msg));
			}

		} else if (cmd.equals("update")) {
			try {
				Comments bean = new Comments();
				bean.setContent(request.getParameter("content"));
				bean.setName(request.getParameter("name"));
				bean.setId(request.getParameter("id"));

				HashMap<String, Object> map = CommentsDAO.getInstance().update(bean);
				out.print(toXML("success", map));

			} catch (Exception e) {
				e.printStackTrace();
				msg = new HashMap<String, String>() {
					{
						put("msg", e.getMessage());
					}
				};
				out.print(toXML("error", msg));
			}

		} else if (cmd.equals("delete")) {
			try {
				Comments bean = new Comments();
				bean.setId(request.getParameter("id"));

				HashMap<String, Object> map = CommentsDAO.getInstance().delete(bean);
				out.print(toXML("success", map));

			} catch (Exception e) {
				e.printStackTrace();
				HashMap map = new HashMap<String, String>() {
					{
						put("msg", e.getMessage());
					}
				};
				out.print(toXML("error", map));
			}

		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("<code>error</code>");
			sb.append("<data><![CDATA[ ");
			sb.append("cmd not found");
			sb.append("]]></data>");
			sb.append("</result>");
			out.print(sb.toString());
		}

	}

	private String toXML(String result, HashMap data) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>");
		sb.append(result);
		sb.append("</code>");
		sb.append(" <data><![CDATA[ ");
		StringWriter sw = new StringWriter();
		JSONValue.writeJSONString(data, sw);
		System.out.println(sw.toString());
		sb.append(sw.toString());
		sb.append("]]></data>");
		sb.append("</result>");
		return sb.toString(); // String type으로 출력한다.toString();
	}

	private String selectAll(List<HashMap<String, Object>> list) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append(" <data><![CDATA[ ");
		sb.append(" [ ");
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			sb.append("{ ");
			sb.append(" id:" + map.get("id"));
			sb.append(", name:'" + map.get("name") + "'");
			sb.append(", content:'" + map.get("content") + "'");
			sb.append("}");
			if (i != list.size()) {
				sb.append(", ");
			}
		}
		sb.append("]\n");
		sb.append("]]></data>");
		sb.append("</result>");
		return sb.toString();
	}
}
