package com.yedam.ssy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.common.DBconnect;

/*
 * PreparedStatement 인터페이스는 Connection 객체의 prepareStatement() 메소드를 사용해서 객체 생성.
 */

public class EmpDAO {
	PreparedStatement psmt;
	ResultSet rs;
	Connection conn;
	/*PreparedStatement 인터페이스는 Connection 객체의 prepareStatement() 메소드를 사용해서 객체를 생성
	 *PreparedStatement 객체는 SQL 문장이 미리 컴파일되고, 실행시간동안 인수 값을 위한 공간을 확보할 수 있다
	 *PreparedStatement의 배치기능은 다수의 쿼리를 한꺼번에 실행하기 위한 기능
	 */
	
	public void deleteSchedule(String title, String startDate) {
		conn = DBconnect.getCon();
		String sql = "delete from fullcalendar where title=? and substr(start_date,1,10)=?";
		// '2020-11-09T16:00:00' 일 떄 substr함수 활용.
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, startDate);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제 완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void makeSchedule(FullCalendarVO cal) {
		conn = DBconnect.getCon();
		String sql = "insert into fullcalendar values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getTitle());
			psmt.setString(2, cal.getStartDate());
			psmt.setString(3, cal.getEndDate());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력 완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<FullCalendarVO> getSchedules() {
		conn = DBconnect.getCon();
		String sql = "select * from fullcalendar";
		List<FullCalendarVO> schedules = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			// DB data의 생성자 가져옴. DB column명과 동일해야함.
			while (rs.next()) {
				FullCalendarVO cal = new FullCalendarVO(rs.getString("title"), 
														rs.getString("start_date"),
														rs.getString("end_date"));
				schedules.add(cal);
				//VO의 생성자에 cal에서 담은 DBdata를 넣어준다.-> query의 결과가됨.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return schedules; // query 결과 반환.
	};

	public void updateEmpInfo(String eid, String j, String s) {
		System.out.println("s:" + s + "eid:" + eid);
		conn = DBconnect.getCon();
		String sql = "UPDATE employees SET salary=?, job_id=? WHERE employee_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			psmt.setString(2, j);
			psmt.setString(3, eid);

			int r = psmt.executeUpdate(); // update된 만큼 r에 담아 업데이트 실행.
			System.out.println(r + "건 업데이트 완료");

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * DB로 query에 필요한 데이터 불러오기 -> GetMemberPerDeptServlet통해 web과 교환.(return
	 * memberPerDept). Map에 저장되는 데이터는 ‘key-value’ pair라는 형식.
	 */
	public Map<String, Integer> getMemberPerDept() {
		conn = DBconnect.getCon();
		String sql = "SELECT d.department_name, e.cnt  " + "FROM (select department_id, count(*) as cnt "
				+ "from employees group by department_id) e, departments d "
				+ "WHERE e.department_id = d.department_id";
		// employees 테이블의 department_id별로 정렬한 department_id 와 cnt (e) + departments
		// 테이블(d) = (e,d의) deparment_id 가 같은조건의 select.

		Map<String, Integer> memberPerDept = new HashMap<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				memberPerDept.put(rs.getString("department_name"), rs.getInt("cnt"));
				// key: department_name, value: cnt.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberPerDept;
	}

	/* DB로 query에 필요한 데이터 불러오기 -> GetEmpInfoServlet통해 web과 교환(return emp) */
	public EmployeeVO getEmpInfo(String empId) {
		conn = DBconnect.getCon();
		EmployeeVO emp = new EmployeeVO();
		try {
			psmt = conn.prepareStatement("select * from employees where employee_id = ?");
			psmt.setString(1, empId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	/*
	 * DB로 employees table 의 필요한 데이터 불러오기 -> GetEmployeeListServ 통해 web 교환(return
	 * employees)
	 */
	public List<EmployeeVO> getEmpList() {
		List<EmployeeVO> employees = new ArrayList<>();
		try {
			conn = DBconnect.getCon(); // db연결
			psmt = conn.prepareStatement("select * from employees order by 1"); // db연결고리->쿼리문실행

			rs = psmt.executeQuery();
			while (rs.next()) { // rs에 데이터 담을때까지 실행.
				EmployeeVO emp = new EmployeeVO();
				emp.setEmployeeId(rs.getInt("employee_id")); // db데이터의 컬럼과 동일해야함. db data가져오는 것이므로.
				emp.setFirstName(rs.getString("first_name")); // first_name을 getString하여 rs에 담아 setString으로 가져와 emp에
																// 담는다.
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));

				employees.add(emp); // emp에 필요한 db data담아 List<employee>에 담는다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employees;

	}
}
