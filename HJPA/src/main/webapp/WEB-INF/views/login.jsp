<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
  
  
</head>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-light bg-light static-top">
	<div class="container">
		<a class="navbar-brand" href="/">HyoJeong</a>
		<div class="float-right">
			<a class="btn btn-primary" href="register">Sign In</a> 
			<a class="btn btn-primary" id="loginBtn" href="login">Login</a>
		</div>
	</div>
	</nav>

    <div class="container">
	    <div class="row justify-content-center">
	        <div class="col-md-8">
	            <div class="card">
	                <div class="card-header" align="center">Login</div>
					<form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
						<table align="center">
							<tr>
								<td>
									<form:label class="col-sd-3" path="username">Username: </form:label>
								</td>
								<td>
									<form:input class="col-sd-4" path="username" name="username" id="username" />
								</td>
							</tr>
							<tr>
								<td>
									<form:label class="col-sd-3" path="password">Password:</form:label>
								</td>
								<td>
									<form:password class="col-sd-4" path="password" name="password" id="password" />
								</td>
							</tr>
						</table>
						<div class="col-md-12" align="center">
						<div class="mdc-button__ripple"></div>
							<button class="col-md-2 btn btn-secondary" id="login" name="login">Login</button>
							<c:if test="${msg == 'fail'}">
								<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
							</c:if>

							<c:if test="${msg == 'logout'}">
								<div style="color: red">로그아웃 되었습니다.</div>
							</c:if>
						</div>
					</form:form>	
	                
	            </div>
	        </div>
	    </div>
	</div>
<footer class="main-footer">
	<div class="pull-right hidden-xs">
	    <b>Version</b> 0.1
	</div>
	<strong>Copyright © 2019 <a href="/">HJPA</a>.</strong> All rights reserved.
</footer>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</body>
</html>