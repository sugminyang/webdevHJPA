<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Search</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
  
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
  
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <style>
            .ui-autocomplete {
              max-height: 100px;
              overflow-y: auto;
              /* prevent horizontal scrollbar */
              overflow-x: hidden;
            }
            /* IE 6 doesn't support max-height
             * we use height instead, but this forces the menu to always be this tall
             */
            * html .ui-autocomplete {
              height: 100px;
            }
            table {
        		border: 1px solid #333333;
            	border-spacing: 5px;
            }
    </style>
</head>
<body>
  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="/">HyoJeong</a>
      <div class= "float-right">
	      	<c:choose>
      				<c:when test="${sessionScope.auth == null}">
      				</c:when>
      				<c:when test="${sessionScope.auth == 0}">      				
      					<a class="btn btn-primary" href="/studentInfo">내 정보 수정</a>
      				</c:when>
      				<c:when test="${sessionScope.auth == 1}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
      				</c:when>
      				<c:when test="${sessionScope.auth == 2}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
      					<a class="btn btn-primary" href="/search_domestic">국내학생 정보 검색</a>
      					<a class="btn btn-primary" href="/">관리자 모드</a>
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
  
<div class="container" >
	<div class="col-lg-12">
        <h3 class="page-header">학생 정보 조회</h3>
    </div>
	<div class="col-lg-12">
        <h3 class="page-header">1. 개인정보</h3>
		<table align="center" >
			<tr>
				<td>한교원 학번: </td>
				<td><input value="${student.getSno_acad()}"> </td>
				<td>학부 학번: </td>
				<td><input value="${student.getSno_univ()}"> </td>
			</tr>
			<tr>
				<td>name(한글): </td>
				<td><input value="${student.getName_kor()}"> </td>
				<td>name(영어): </td>	
				<td><input value="${student.getName_eng()}"> </td>
			</tr>
			<tr>
				<td>birth: </td>
				<td><input value="${student.getBirth()}"> </td>
				<td>sex: </td>
				<td><input value="${student.getSex()}"> </td>
			</tr>
			<tr> 
				<td>continent: </td>
				<td><input value="${student.getContinent()}"> </td>
				<td>nationality: </td>
				<td><input value="${student.getNationality()}"> </td>
			</tr>
			
			<tr>
				<td>college: </td>
				<td><input value="${student.getCollege()}"> </td>
				<td>dept: </td>
				<td><input value="${student.getDept()}"> </td>
			</tr>		
			
			<tr>
				<td>email: </td>
				<td><input value="${student.getEmail()}"> </td>
				<td>phone: </td>
				<td><input value="${student.getPhone()}"> </td>
			</tr>
							
			<tr>
				<td>snsType: </td>
				<td><input value="${student.getSnsType()}"> </td>
				<td>sns_id: </td>
				<td><input value="${student.getSns_id()}"> </td>
			</tr>	
			
		</table>
    </div>    
    
</div>
</body>
</html>