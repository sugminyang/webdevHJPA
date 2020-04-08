<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	<H1>Welcome ${student.getName_eng()}</H1>
	<table>
		<tr>
			<td>birth: ${student.getBirth()}</td>
		</tr>
		<tr> 
			<td>nationality: ${student.getNationality()}</td>
		</tr>
		<tr>
			<td>email: ${student.getEmail()}</td>
		</tr>
		<tr>
			<td>college: ${student.getCollege()}</td>
		</tr>		
		<tr>
			<td>dept: ${student.getDept()}</td>
		</tr>		


		<tr>
			<td><a href="/">Home</a></td>
		</tr>
		<%-- <tr>
			<td>
				<form:form id="logoutForm" modelAttribute="logout" action="logout" method="post">
					<form:button id="logout" name="logout">Logout</form:button>
				</form:form>
			</td>
		</tr> --%>
	</table>
</body>
</html>