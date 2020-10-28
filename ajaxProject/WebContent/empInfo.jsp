<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div#detail {
	border: 10px dotted white;
	background: greenyellow;
}
div#detail>label {
	background: black;
	color: white;
	width: 80px;
	display: inline-block;
}
div#detail>button {
	border-radius : 3px;
	background: red;
	color: white;
	border: 1px solid green;
	position: relative;
	left:30px
}
</style>
</head>
<body>
	<%
		String empId = request.getParameter("id");		
	%>
	<h1>아이디:<%=empId%></h1>
	<div id="detail">
	
	<label for="empId">EmpId</label>
	<input type="text" id="empId" value="" readonly><br>
	
	<label for="fName">FirstName</label>
	<input type="text" id="fName" value="" ><br>
	
	<label for="lName">LastName</label>
	<input type="text" id="lName" value="" ><br>
	
	<label for="email">Email</label>
	<input type="text" id="email" value="" ><br>
	
	<label for="pNumber">PhoneNumber</label>
	<input type="text" id="pNumber" value="" ><br>
	
	<label for="hireD">HireDate</label>
	<input type="text" id="hireD" value="" ><br>
	
	
	
	<label for="salary">Salary</label>
	<input type="text" id="salary" value="" ><br>
	
	<Label for="jobId">Job</Label>
		<select id="jobId">
			<option value="AD_PRES">President</option>
			<option value="AD_VP">Administration Vice President</option>
			<option value="AD_ASST">Administration Assistant</option>
			<option value="AC_MGR">Accounting Manager</option>
			<option value="AC_ACCOUNT">Public Accountant</option>
			<option value="SA_MAN">Sales Manager</option>
			<option value="SA_REP">Sales Representative</option>
			<option value="ST_MAN">Stock Manager</option>
			<option value="ST_CLERK">Stock Clerk</option>
			<option value="IT_PROG">Programmer</option>
			<option value="MK_MAN">Marketing Manager</option>
			<option value="MK_REP">Marketing Representative</option>
		</select><br>
	
	<label for="cPct">CommissionPct</label>
	<input type="text" id="cPct" value="" ><br>
	
	<label for="mId">ManagerId</label>
	<input type="text" id="mId" value="" ><br>
	
	<label for="dI">DepartmentId</label>
	<input type="text" id="dI" value="" ><br>
	
	<button id="changeBtn">수정</button>
	
	</div>
	

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
		//ajax 호출.
		
		//button event.
		let btn = document.getElementById('changeBtn');
		btn.addEventListener('click', myChangeFunc);
		
		//button event callback function.
		function myChangeFunc() {
			let empId = document.getElementById('empId').value;
			let fName = document.getElementById('fName').value;
			let lName = document.getElementById('lName').value;
			let email = document.getElementById('email').value;
			let pNumber = document.getElementById('pNumber').value;
			let hireD = document.getElementById('hireD').value;
			let jobId = document.getElementById('jobId').value;
			let salary = document.getElementById('salary').value;
			let cPct = document.getElementById('cPct').value;
			let mId = document.getElementById('mId').value;
			let dI = document.getElementById('dI').value;
			
			let param = 'empId='+empId+'&fName='+fName+'&lName='+lName+'&salary='+salary+'&jobId='+jobId;
			
			let xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(xhttp.readyState == 4 && xhttp.status == 200) {
					alert(xhttp.responseText);
					location.href = "index.html";
				}
				
				
			}
			xhttp.open('get','PutEmpInfoServlet?'+param);
			xhttp.send();
		}
		
		
	</script>
</body>
</html>