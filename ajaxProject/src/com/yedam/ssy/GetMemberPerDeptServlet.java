package com.yedam.ssy;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetMemberPerDeptServlet")
public class GetMemberPerDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetMemberPerDeptServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO(); // 반드시 기억. dao의 생성자 호출.
		Map<String, Integer> members = dao.getMemberPerDept();
		Set<String> keySet = members.keySet();	// ketSet(key),entrySet(key+value)
		String json = "[";
		int cnt = 0, dataLength = keySet.size();
		for(String key : keySet) {
//			System.out.println(key + ", " + members.get(key));
			// [{"Administration":1},{"Sales":3},{"Executive":3},{"Accounting":2},{"IT":4},{"Shipping":6},{"Marketing":2}]
			
			json += "{\""+key+"\":"+members.get(key)+"}";	//get(key) key의 값을 get하면 key의 value값을 가져온다는 메서드.
			cnt++;
			if(cnt != dataLength) {
				json += ",";
			}
		}
		json += "]";	// json의 배열로 표현.
		response.getWriter().append(json); // web에 데이터 출력.
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
