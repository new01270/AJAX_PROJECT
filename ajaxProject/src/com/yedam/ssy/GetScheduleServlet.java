package com.yedam.ssy;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * Developer commit.
CREATE table fullcalendar (
title varchar2(100), 
start_date varchar2(25),
end_date varchar2(25)
);

insert into FULLCALENDAR values('meeting1', '2020-11-02', '2020-11-04');
insert into FULLCALENDAR values('meeting2', '2020-11-09T16:00:00', '2020-11-09T18:00:00');
insert into FULLCALENDAR values('meeting3', '2020-11-11', null);
 */


@WebServlet("/GetScheduleServlet")
public class GetScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetScheduleServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONArray jAry = new JSONArray();
		
		EmpDAO dao = new EmpDAO();
		List<FullCalendarVO> list = dao.getSchedules();	// query결과를 반환하는 method를 가져와 list에 담는다.
		
		// DB에서 가져온 data를 json type으로 출력하기위해 만들어줌.
		for (FullCalendarVO cal : list) {
			JSONObject obj = new JSONObject();
			obj.put("title", cal.getTitle());
			obj.put("start", cal.getStartDate());
			obj.put("end", cal.getEndDate());
			jAry.add(obj);
		}
		
		
		response.getWriter().append(JSONArray.fromObject(jAry).toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
