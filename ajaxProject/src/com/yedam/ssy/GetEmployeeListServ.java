package com.yedam.ssy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetEmployeeListServ")
public class GetEmployeeListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEmployeeListServ() {
		super();

	}
	
	// db에서 불러온 데이터 작업.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmpDAO dao = new EmpDAO();	
		List<Employee> list = dao.getEmpList();	// employee에 담은 data를 'list'인스턴스에 담아준다.
//		 [{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},...]

		//{"id":"3","first_name":"Pris","last_name":"Whittam"}
		String json = "[";
		int dataCnt = list.size();
		int i = 0;
		for (Employee emp : list) {
			json += "{\"id\":\"" + emp.getEmployeeId()	// set으로 담아준 data->get으로 가져오기->json타입으로 수기변경.
	         + "\", \"firstName\":\"" + emp.getFirstName()
	         + "\", \"lastName\":\"" + emp.getLastName() 
	         + "\", \"email\":\"" + emp.getEmail()
	         + "\", \"phoneNumber\":\"" + emp.getPhoneNumber() 
	         + "\", \"hireDate\":\"" + emp.getHireDate()
	         + "\", \"jobId\":\"" + emp.getJobId() 
	         + "\", \"salary\":\"" + emp.getSalary()
	         + "\", \"commisionPct\":\"" + emp.getCommissionPct() 
	         + "\", \"managerId\":\"" + emp.getManagerId()
	         + "\", \"departmentId\":\"" + emp.getDepartmentId() + "\"}";

			i++;
			if (i != dataCnt) {
				json += ",";
			}
		}
		json += "]";

		response.getWriter().append(json);	//json객체를 추가하여 반환한다.-> index.html의 <script>의 xhtp.open에 get.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
