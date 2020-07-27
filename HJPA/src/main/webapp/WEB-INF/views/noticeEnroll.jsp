<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Enroll</title>

<%-- <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script> --%>

	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
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
    	$('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  }).on("summernote.enter", function(we, e) {
		  $(this).summernote("pasteHTML", "<br><br>");
		  e.preventDefault();
		  });;
    	
    	/* $('#summernote').summernote ('code', '<b> hello world </ b>'); */
    	
        //공지사항 신규 등록
        $("#notice_regist").on("click",function(){
            var formData = $('#summernote').summernote('code');
            var title = $("#title").val();
            var writer = $("#writer").val();
            var deliminator = "!@#";
            var item = title + deliminator +
            			formData + deliminator + 
            			writer
            	
            $.ajax({
                type : "post",
                url : "/noticeInsert",
                data : item,
                contentType:"application/json;charset=UTF-8",
				dataType: "json",
                success : function(data){
                	console.log(data);
                    if(data=='1') alert("등록 완료");
                    else alert('등록 실패');
                    $("#notice_backPage").click();
                },
                error : function(error){
                    alert("등록 실패");
                    console.log("notice insert fail : "+error);
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
					<a class="btn btn-primary" id="loginBtn" href="login">Sign In</a>
      			</c:when>
      			<c:otherwise>
      				<a class="btn btn-outline-default btn-rounded waves-effect" href="/">${sessionScope.id}</a>
					<a class="btn btn-primary" id="logoutBtn" href="logout">Logout</a>
      			</c:otherwise>
      		</c:choose>
      </div>
    </div>
  </nav>
  
  <div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header" align="center">공지사항 등록</div>
					</br>

					<div>
						<div class="contents">
							<label class="col-md-2">제목:</label> 
							<input class="col-md-10" id="title"
								type="text" name="title" placeholder="제목" style="float: right;" />
						</div>
						<div class="contents">
							<label class="col-md-2">작성자:</label> 
							<input class="col-md-10" id="writer"
								type="text" name="writer" placeholder="작성자"
								style="float: right;" /><br>
						</div>

						<br>
						<textarea id="summernote" name="content"></textarea>
					</div>
					<br>
					
					<div class="footer" style="text-align:center;">
				            <c:if test="${null eq notice }">
				                <input type="button" id="notice_regist" value="등록">
				            </c:if>
				            <c:if test="${null ne notice }">
				                <input type="button" id="notice_edit" value="수정">
				                <input type="button" id="notice_delete" value="삭제">
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
