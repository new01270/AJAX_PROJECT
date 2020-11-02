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
		
		// web에서 client가 title, start, end값을 입력->그값을 parameter로 받아와 객체를 생성한 후, parameter 만들고 (아래)
		String title = request.getParameter("title");
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");
		
		EmpDAO dao = new EmpDAO();
		FullCalendarVO cal = new FullCalendarVO(title, startDate, endDate); // 위에서 지정해준 parameter를 가져와서 dao.에 넘김.
		dao.makeSchedule(cal);
		// (연결) cal 객체에 위에서 받아온 값을 넣은 뒤 makeSchedule DB query update.
		
		response.getWriter().append("success");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
