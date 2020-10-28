package com.yedam.ssy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PutEmpInfoServlet")
public class PutEmpInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PutEmpInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String pNumber = request.getParameter("pNumber");
		String hireD = request.getParameter("hireD");
		String jobId = request.getParameter("jobId");
		String salary = request.getParameter("salary");
		String cPct = request.getParameter("cPct");
		String mId = request.getParameter("mId");
		String dI = request.getParameter("dI");
		
		//데이터베이스에서 수정하는 기능 추가.empDAO
		
		EmpDAO dao = new EmpDAO();
		dao.updateEmpInfo(empId, jobId, salary);
	
		response.getWriter().append("complete");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
