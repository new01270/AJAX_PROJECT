<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="public/jquery-3.5.1.min.js"></script>
<script>
<%
String empId = request.getParameter("id");
%>
	$(document).ready(function() {
		// ajax 호출 결과를 #result 에 보여줌.
		$.ajax({			
			url : 'GetEmpInfoServlet?id=<%=empId%>',
			success : function(result) {
				console.log(result);
				let data = result;
				$('#empId').val(data.id);
				$('#fName').val(data.firstName);
				$('#lName').val(data.lastName);
				$('#email').val(data.email);
				$('#pNumber').val(data.phoneNumber);
				$('#hireD').val(data.hireDate);
				$('#salary').val(data.salary);
				$('#jobId').val(data.jobId);
				$('#cPct').val(data.commisionPct);
				$('#mId').val(data.managerId);
				$('#dI').val(data.departmentId);				
				
			},
			error : function(reject) {
				console.log(new Error('실행 중 에러 발생.')); // 에러 결과를 reject에 받아옴.
			},
			dataType : 'json'
		})
	
		// 버튼불러오기
		$('#changeBtn').on('click', myChangeFunc);
		
		
		//button event callback function.
		function myChangeFunc() {
			let empId = $('#empId').val();
			let fName = $('#fName').val();
			let lName = $('#lName').val();
			let email = $('#email').val();
			let pNumber = $('#pNumber').val();
			let hireD = $('#hireD').val();
			let jobId = $('#jobId').val();
			let salary = $('#salary').val();
			let cPct = $('#cPct').val();
			let mId = $('#mId').val();
			let dI = $('#dI').val();
			
			//let param = 'empId='+empId+'&fName='+fName+'&lName='+lName+'&salary='+salary+'&jobId='+jobId;
			//console.log(param);
			
			$.ajax({
				
				url : 'PutEmpInfoServlet?',	//+param
				data: {	//key value , key value 방식
					empId : empId,
					fName : fName,
					lName : lName,
					email : email,
					pNumber : pNumber,
					hireD : hireD,
					jobId : jobId,
					salary : salary,
					cPct : cPct,
					mId : mId,
					dI : dI
					
				},
				success : function(){
					alert("수정 성공!")
					location.href = "jquery_index.html";
				},
				error : function(reject) {
					console.log(new Error('실행 중 에러 발생.')); // 에러 결과를 reject에 받아옴.
				}
				
			})
					
		}//end.myfunction
		
	}); //end.ready
	</script>
<style>
div#detail {
	border: 10px dotted white;
	background: lightblue;
}

div#detail>label {
	background: black;
	color: white;
	width: 80px;
	display: inline-block;
}

div#detail>button {
	border-radius: 3px;
	background: red;
	color: white;
	border: 1px solid green;
	position: relative;
	left: 30px
}
</style>
</head>
<body>
	
	<h1>
		아이디:<%=empId%></h1>
	<div id="detail">

		<label for="empId">EmpId</label> <input type="text" id="empId"
			value="" readonly><br> <label for="fName">FirstName</label>
		<input type="text" id="fName" value=""><br> <label
			for="lName">LastName</label> <input type="text" id="lName" value=""><br>

		<label for="email">Email</label> <input type="text" id="email"
			value=""><br> <label for="pNumber">PhoneNumber</label>
		<input type="text" id="pNumber" value=""><br> <label
			for="hireD">HireDate</label> <input type="text" id="hireD" value=""><br>

		<label for="salary">Salary</label> <input type="text" id="salary"
			value=""><br> <Label for="jobId">Job</Label> 
			<select	id="jobId">
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
		<label for="cPct">CommissionPct</label> <input type="text" id="cPct" value=""><br> <label for="mId">ManagerId</label>
		<input type="text" id="mId" value=""><br> 
		<label	for="dI">DepartmentId</label> <input type="text" id="dI" value=""><br>

		<button id="changeBtn">수정</button>

	</div>

</body>
</html>