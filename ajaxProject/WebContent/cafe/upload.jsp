<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file upload</title>
</head>
<body>
	<form action="../FileUpload" method="POST"
		enctype="multipart/form-data">
		<input type="file" name="attach1"> <input type="submit"
			value="파일업로드">
	</form>
</body>
</html>