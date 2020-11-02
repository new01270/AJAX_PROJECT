package com.yedam.ssy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fullcalendar-5.3.2/examples/DelScheduleServlet")	// servlet url edit.
public class DelScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DelScheduleServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String startDate = request.getParameter("start");
		EmpDAO dao = new EmpDAO();
		dao.deleteSchedule(title, startDate);	// 위에서 만들어준 파라미터.
		// http://localhost:8088/ajaxProject/fullcalendar-5.3.2/examples/DelScheduleServlet?title=meeting3&start=2020-11-11
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
