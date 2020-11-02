package com.yedam.ssy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DataTableServlet")
public class DataTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DataTableServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * {"draw": 1, "recordsTotal": 57, "recordsFiltered": 57, "data":[[val1, val2,
		 * val3, val4, val5, val6],[val1, val2, val3, val4, val5, val6][val1, val2,
		 * val3, val4, val5, val6]...] }
		 */

		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> employees = dao.getEmpList();
		int dataCnt = employees.size();
		String json = "{\"draw\":1, \"recordsTotal\": " + dataCnt + ", \"recordsFiltered\": " + dataCnt + ",";

		json += "\"data\": [";

		for (int i = 0; i < dataCnt; i++) { // data건수
			json += "[";
			// []안의 값
			json += employees.get(i).getEmployeeId() + ", " 
					+ "\"" + employees.get(i).getFirstName() + "\", "
					+ "\"" +employees.get(i).getEmail() + "\", " 
					+ "\"" +employees.get(i).getPhoneNumber() + "\", "
					+ "\"" +employees.get(i).getHireDate() + "\", " 
					+ employees.get(i).getSalary();
			json += "]";
			if((i+1)!=dataCnt)
				json+= ",";
		}
		

		
	json += "]}";
		
		response.getWriter().append(json);	// 화면 출력
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
