<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>upload 된 파일</h3>
	<form name="download_form" action="download" method="post">
	<input type="hidden" name="filename" value="${filename}">
		파일명 : <a href="javascript:download_form.submit();">${filename}</a>
	</form>

</body>
</html>