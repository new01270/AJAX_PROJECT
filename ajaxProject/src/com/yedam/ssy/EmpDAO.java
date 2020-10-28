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
 * PreparedStatement �������̽��� Connection ��ü�� prepareStatement() �޼ҵ带 ����ؼ� ��ü ����.
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
			
			int r = psmt.executeUpdate();	//update�� ��ŭ r�� ��� ������Ʈ ����.
			System.out.println(r + "�� ������Ʈ �Ϸ�");
			
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
	 * *DB�� query�� �ʿ��� ������ �ҷ����� -> GetMemberPerDeptServlet���� web�� ��ȯ.(return memberPerDept).
	 * Map�� ����Ǵ� �����ʹ� ��key-value�� pair��� ����.
	 */
	public Map<String, Integer> getMemberPerDept() {
		conn = DBconnect.getCon();
		String sql = "SELECT d.department_name, e.cnt  " + 
				"FROM (select department_id, count(*) as cnt " + 
				"from employees group by department_id) e, departments d " + 
				"WHERE e.department_id = d.department_id";	
		// employees ���̺��� department_id���� ������ department_id �� cnt (e) + departments ���̺�(d) = (e,d��) deparment_id �� ���������� select. 
		
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
	
	/*DB�� query�� �ʿ��� ������ �ҷ����� -> GetEmpInfoServlet���� web�� ��ȯ(return emp)*/
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

	/*DB�� employees table �� �ʿ��� ������ �ҷ����� -> GetEmployeeListServ ���� web ��ȯ(return employees)*/
	public List<Employee> getEmpList() {
		List<Employee> employees = new ArrayList<>();
		try {
			conn = DBconnect.getCon();	// db����
			psmt = conn.prepareStatement("select * from employees order by 1");	// db�����->����������

			rs = psmt.executeQuery();
			while (rs.next()) {	// rs�� ������ ���������� ����.
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));	// db�������� �÷��� �����ؾ���. db data�������� ���̹Ƿ�.
				emp.setFirstName(rs.getString("first_name"));	//first_name�� getString�Ͽ� rs�� ��� setString���� ������ emp�� ��´�.
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));

				employees.add(emp);	// emp�� �ʿ��� db data��� List<employee>�� ��´�.
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
