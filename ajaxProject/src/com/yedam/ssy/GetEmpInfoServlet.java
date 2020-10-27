package com.yedam.ssy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetEmpInfoServlet")
public class GetEmpInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEmpInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId = request.getParameter("id");	//?뒤의 아이디
		EmpDAO dao = new EmpDAO();
		Employee emp = dao.getEmpInfo(empId);
		String json = "{\"id\":\"" + emp.getEmployeeId() 
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
		
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
