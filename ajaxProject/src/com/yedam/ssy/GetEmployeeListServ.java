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
	
	// db���� �ҷ��� ������ �۾�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmpDAO dao = new EmpDAO();	
		List<Employee> list = dao.getEmpList();	// employee�� ���� data�� 'list'�ν��Ͻ��� ����ش�.
//		 [{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},
//		{"id":employee_id, "firstName":first_name,...},...]

		//{"id":"3","first_name":"Pris","last_name":"Whittam"}
		String json = "[";
		int dataCnt = list.size();
		int i = 0;
		for (Employee emp : list) {
			json += "{\"id\":\"" + emp.getEmployeeId()	// set���� ����� data->get���� ��������->jsonŸ������ ���⺯��.
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

		response.getWriter().append(json);	//json��ü�� �߰��Ͽ� ��ȯ�Ѵ�.-> index.html�� <script>�� xhtp.open�� get.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
