<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String empId = request.getParameter("id");
		String firstName = request.getParameter("firstName");
	%>
	<h1>아이디:<%=empId%></h1>

	employeeId:<input type="text" value=<%request.getParameter("id"); %> ><br>
	firstName:<input type="text" value="Steven" ><br>
	lastName:<input type="text" value="King" ><br>
	email:<input type="text" value="SKING" ><br>
	phoneNumber:<input type="text" value="515.123.4567" ><br>
	hireDate:<input type="text" value="1987-06-17 00:00:00" ><br>
	jobId:<input type="text" value="AD_PRES" ><br>
	salary:<input type="text" value="26400" ><br>
	commissionPct:<input type="text" value="0.0" ><br>
	managerId:<input type="text" value="0" ><br>
	departmentId:<input type="text" value="0" ><br>
	<button>OK</button>

	<script>
		let xhtp = new XMLHttpRequest();
		xhtp.onreadystatechange = function() {
			if(xhtp.readyState == 4 && xhtp.status == 200) {
			console.log(xhtp.responseText);
			let data = JSON.parse(xhtp.responseText);	// json -> 자바스크립트 오브젝트.
			console.log(data.id, data.phoneNumber, data.email);
			}
		}
		xhtp.open('get', 'GetEmpInfoServlet?id=<%=empId%>');
		xhtp.send();
	</script>
</body>
</html>