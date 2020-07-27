<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Detail</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeList.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
    	.btn-primary{
			background-color: gray;
    		border-color: gray;
		}
		#hjIcon {
        	border: none;
    		background: none;
		}
		.notice_title {
			border-top: 2px solid gray;
			border-bottom: 2px solid gray;
		}
		p	{
			vertical-align: middle;
		    display: table-cell;
		}
		p#notice_title	{
		    font-weight: bold;
		}
	</style>
	
<script>
    $(document).ready(function(){
         
        //공지사항 수정
        $("#notice_edit").on("click",function(){
            var id = ${notice.notice_id};
            $.ajax({
                type : "post",
                url : "/noticeUpdate",
                data : formData,
                success : function(data){
                    if(data==1) alert("수정 완료");
                    else alert('수정 실패');
                },
                error : function(error){
                    alert("수정 실패");
                    console.log("notice update fail : "+error);
                }
            });
        });
         
        //공지사항 삭제
        $("#notice_delete").on("click",function(){
            $.ajax({
                type : "post",
                url : "/noticeDelete/" + ${notice.notice_id},
                success : function(data){
                    if(data==1){
                        alert("삭제 완료");
                        location.href="/noticeList";
                    }else alert('삭제 실패');
                },
                error : function(error){
                    alert("삭제 실패");
                    console.log("notice delete fail : "+error);
                }
            });
        });
         
        $("#notice_backPage").on("click",function(){
            location.href="/noticeList";
        });
    })
</script>

</head>
<body>
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
					<a class="btn btn-primary" id="loginBtn" href="/login">Sign In</a>
      			</c:when>
      			<c:otherwise>
      				<a class="btn btn-outline-default btn-rounded waves-effect" href="/">${sessionScope.id}</a>
					<a class="btn btn-primary" id="logoutBtn" href="/logout">Logout</a>
      			</c:otherwise>
      		</c:choose>
      </div>
    </div>
  </nav>
  
  <div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header" align="center">공지사항 추가/변경/삭제</div>
					</br>
					
				 	<div class="big_contents">
						<form id="notice_form">
							<input type="hidden" id="notice_id" name="notice_id"
								value="${notice.notice_id}">
							<div class="rows notice_title">
								<%-- <input type="text" id="notice_title" name="notice_title" value="${notice.notice_title}"> --%>
								<p type="text" id="notice_title" name="notice_title">${notice.notice_title}</p>
							</div>
							<div class="rows">
								<p id="notice_coments" name="notice_coments">${notice.notice_coments}</p>
							</div>
						</form>
				
						<div class="footer" style="text-align:center;">
							<c:if test="${null ne notice }">
								<c:choose>
									<c:when test="${sessionScope.auth == 1}">
										<input type="button" id="notice_delete" value="삭제">
										<input type="button" id="notice_edit" value="수정">
									</c:when>
									<c:when test="${sessionScope.auth == 2}">
										<input type="button" id="notice_delete" value="삭제">
										<input type="button" id="notice_edit" value="수정">
									</c:when>
								</c:choose>
							</c:if>
							<input type="button" id="notice_backPage" value="뒤로">
				            
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
   
     
     
</body>
</html>
