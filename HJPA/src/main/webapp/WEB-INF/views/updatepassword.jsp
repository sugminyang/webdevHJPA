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
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
  
  <style>
  .btn-primary{
			background-color: gray;
    		border-color: gray;
		}
		#hjIcon {
        	border: none;
    		background: none;
		}
		
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() { 
			$("#pwClear").click(function() {
				var editData = "@!pw:" + $("#password").val();
				  //console.log(editData);
				  
				  $.ajax({ 
						data :  editData,
						type : "POST", 
						contentType:"application/json;charset=UTF-8",
						url : "/changepassword", 
						success : function(data) { 
							if(data != 0)	{
								alert("비밀번호가 변경되었습니다.");
								
								window.location.href = "/studentInfo";
							}
							else	{
								alert("비밀번호 변경을 실패하였습니다.");
							}
						}, 
						error : function(data) { 
							alert("error: 비밀번호 변경을 실패하였습니다."); 
						} 
					});
			});			            	
		});
	</script>
</head>
<body>

	 <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="/"><button id="hjIcon" type="button" ><img src="${pageContext.request.contextPath}/resources/img/hj_top_logo.png" alt=""></button></a>
      <div class= "float-right">
	      	<c:choose>
      				<c:when test="${sessionScope.auth == null}">
      				</c:when>
      				<c:when test="${sessionScope.auth == 0}">
      					<a class="btn btn-primary" href="/studentInfo">내 정보 수정</a>
      					<a class="btn btn-primary" href="/noticeList">공지 사항</a>   
      				</c:when>
      				<c:when test="${sessionScope.auth == 1}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
      					<a class="btn btn-primary" href="/noticeList">공지 사항</a>
      				</c:when>
      				<c:when test="${sessionScope.auth == 2}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
      					<a class="btn btn-primary" href="/noticeList">공지 사항</a>
      					<!-- <a class="btn btn-primary" href="/search_domestic">국내학생 정보 검색</a> -->
      					<a class="btn btn-primary" href="/adminpage">관리자 모드</a>
      				</c:when>     				
      				<c:otherwise>
      					alert('authority is unknown..')
      				</c:otherwise>
	      	</c:choose>
      		<c:choose>
      			<c:when test="${sessionScope.id == null}">
					<a class="btn btn-primary" href="register">Register</a> 
					<a class="btn btn-primary" id="loginBtn" href="login">Sign In</a>      				
      			</c:when>
      			<c:otherwise>
      				<a class="btn btn-outline-default btn-rounded waves-effect" href="/">${sessionScope.id}</a>
					<a class="btn btn-primary" id="logoutBtn" href="logout">Logout</a>
      			</c:otherwise>
      		</c:choose>
<!-- 	      <a class="btn btn-primary" id="register" href="register">Sign In</a>
	      <a class="btn btn-primary" id="loginBtn" href="login">Login</a> -->
      </div>
    </div>
  </nav>

    <div class="container">
	    <div class="row justify-content-center">
	        <div class="col-md-8">
				<div class="card">
					<div class="card-header" align="center">새로운 비밀번호 설정</div>
					<table>
					<tr>
						<td class="col-sd-3"><label style="text-align:center; width:100%">New Password:</label></td>
						<td class="col-sd-4"><input style="width:100%" name="password" id="password" type="password"/></td>
						<td class="col-sd-1"><button style="width:100%" type="button" id="pwClear">비밀번호 변경</button></td>
					</tr>
					</table>
				</div>
			</div>
	    </div>
	</div>
	
<footer class="main-footer">
	<div class="pull-right hidden-xs">
	    <b>Version</b> 0.1
	</div>
	<strong>Copyright © 2020 <a href="/">HJPA</a>.</strong> All rights reserved.
</footer>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</body>
</html>