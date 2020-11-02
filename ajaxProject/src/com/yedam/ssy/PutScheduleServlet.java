package com.yedam.ssy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PutScheduleServlet")
public class PutScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PutScheduleServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// parameter
		String title = request.getParameter("title");	// xhtp.~에서 ?title= v1에서 불러온 값을 가져옴.
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");
		
		EmpDAO dao = new EmpDAO();
		FullCalendarVO cal = new FullCalendarVO(title, startDate, endDate); // 위에서 지정해준 parameter를 가져와서 dao.에 넘김.
		dao.makeSchedule(cal);	// cal을 makeSchedule에 넣어 DB query 실행.
		
		response.getWriter().append("success");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
