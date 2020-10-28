package com.yedam.ssy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedom.common.DBconnect;

/*
 * PreparedStatement 인터페이스는 Connection 객체의 prepareStatement() 메소드를 사용해서 객체 생성.
 */

public class EmpDAO {
	PreparedStatement psmt;
	ResultSet rs;
	Connection conn;
	
	public void updateEmpInfo(String eid, String j, String s) {
		System.out.println("s:"+s + "eid:"+eid);
		conn = DBconnect.getCon();
		String sql = "UPDATE employees SET salary=?, job_id=? WHERE employee_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s);
			psmt.setString(2, j);
			psmt.setString(3, eid);
			
			int r = psmt.executeUpdate();	//update된 만큼 r에 담아 업데이트 실행.
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
	 * DB로 query에 필요한 데이터 불러오기 -> GetMemberPerDeptServlet통해 web과 교환.(return memberPerDept).
	 * Map에 저장되는 데이터는 ‘key-value’ pair라는 형식.
	 */
	public Map<String, Integer> getMemberPerDept() {
		conn = DBconnect.getCon();
		String sql = "SELECT d.department_name, e.cnt  " + 
				"FROM (select department_id, count(*) as cnt " + 
				"from employees group by department_id) e, departments d " + 
				"WHERE e.department_id = d.department_id";	
		// employees 테이블의 department_id별로 정렬한 department_id 와 cnt (e) + departments 테이블(d) = (e,d의) deparment_id 가 같은조건의 select. 
		
		Map<String, Integer> memberPerDept = new HashMap<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				memberPerDept.put(rs.getString("department_name"), rs.getInt("cnt"));
				// key: department_name, value: cnt.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberPerDept;		
	}
	
	/*DB로 query에 필요한 데이터 불러오기 -> GetEmpInfoServlet통해 web과 교환(return emp)*/
	public Employee getEmpInfo(String empId) {
		conn = DBconnect.getCon();
		Employee emp = new Employee();
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
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	/*DB로 employees table 의 필요한 데이터 불러오기 -> GetEmployeeListServ 통해 web 교환(return employees)*/
	public List<Employee> getEmpList() {
		List<Employee> employees = new ArrayList<>();
		try {
			conn = DBconnect.getCon();	// db연결
			psmt = conn.prepareStatement("select * from employees order by 1");	// db연결고리->쿼리문실행

			rs = psmt.executeQuery();
			while (rs.next()) {	//  rs에 데이터 담을때까지 실행.
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));	// db데이터의 컬럼과 동일해야함. db data가져오는 것이므로.
				emp.setFirstName(rs.getString("first_name"));	//first_name을 getString하여 rs에 담아 setString으로 가져와 emp에 담는다.
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));

				employees.add(emp);	// emp에 필요한 db data담아 List<employee>에 담는다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employees;

	}
}
