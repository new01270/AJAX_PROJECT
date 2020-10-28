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
	%>
	<h1>아이디:<%=empId%></h1>

	employeeId:<input type="text" id="empId" value="" ><br>
	firstName:<input type="text" id="fName" value="" ><br>
	lastName:<input type="text" id="lName" value="" ><br>
	email:<input type="text" id="email" value="" ><br>
	phoneNumber:<input type="text" id="pNumber" value="" ><br>
	hireDate:<input type="text" id="hireD" value="" ><br>
	jobId:<input type="text" id="jobId" value="" ><br>
	salary:<input type="text" id="salary" value="" ><br>
	commissionPct:<input type="text" id="cPct" value="" ><br>
	managerId:<input type="text" id="mId" value="" ><br>
	departmentId:<input type="text" id="dI" value="" ><br>
	<button>OK</button>

	<script>
		let xhtp = new XMLHttpRequest();
		xhtp.onreadystatechange = function() {
			if(xhtp.readyState == 4 && xhtp.status == 200) {
			console.log(xhtp.responseText);
			let data = JSON.parse(xhtp.responseText);	// json -> 자바스크립트 오브젝트.
			console.log(data.id, data.phoneNumber, data.email);
			
			document.getElementById('empId').value = data.id;
			document.getElementById('fName').value = data.firstName;
			document.getElementById('lName').value = data.lastName;
			document.getElementById('email').value = data.email;
			document.getElementById('pNumber').value = data.phoneNumber;
			document.getElementById('hireD').value = data.hireDate;
			document.getElementById('jobId').value = data.jobId;
			document.getElementById('salary').value = data.salary;
			document.getElementById('cPct').value = data.commissionPct;
			document.getElementById('mId').value = data.managerId;
			document.getElementById('dI').value = data.departmentId;
			
			
			}
		}
		xhtp.open('get', 'GetEmpInfoServlet?id=<%=empId%>');
		xhtp.send();
		
		
	</script>
</body>
</html>