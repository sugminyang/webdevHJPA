<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" action="registerProcess" method="post"> 
		<table align="center">
			<tr>
				<td><label>Username</label></td>
				<td><input name="username" id="username" /></td>
			</tr>
			<tr>
				<td><label >Password</label></td>
				<td><password name="password" id="password" /></td>
			</tr>
			<tr>
				<td><label >FirstName</label></td>
				<td><input name="firstname" id="firstname" /></td>
			</tr>
			<tr>
				<td><label path="lastname">LastName</label></td>
				<td><form:input path="lastname" name="lastname" id="lastname" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" name="email" id="email" /></td>
			</tr>
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input path="address" name="address" id="address" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone</form:label></td>
				<td><form:input path="phone" name="phone" id="phone" /></td>
			</tr>

			<tr>
				<td></td>
				<td><form:button id="register" name="register">Register</form:button></td>
			</tr>
		</table>
	</form:form>

</body>
</html>