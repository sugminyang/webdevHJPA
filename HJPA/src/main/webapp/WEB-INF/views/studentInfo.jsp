<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Student Information</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>	

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
  
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
           	div {
           		margin-bottom: 2px;
           		margin-top: 2px;
/*            		font-family: Consolas, monospace;
        		font-size: 13px; */
           	}
           	.sinfo	{
           		border: 1px dashed #bcbcbc;
           	}
           	select {
           		width:100%;
           		margin-bottom: 9px;
           	}
           	.inputLabel{
           		width:100%;
           	}
    </style>
    
<script type="text/javascript">
		$(document).on('click', 'input[name="registerGroup"]', function() {
			if (document.getElementById("btnKorEdu").checked == true) {
				/* alert('한교원') */
				document.getElementById("sno_acad").disabled = false
				document.getElementById("sno_univ").disabled = true
			} else {
				/* alert('대학교') */
				document.getElementById("sno_acad").disabled = true
				document.getElementById("sno_univ").disabled = false
			}
		});
		
    	
        $(document).ready( function () {
        	$("#btnUpdate").on("click", function() {
        		var deliminator = "!@#";
				var sendData = 
					$('#sno_univ').val()+deliminator+$('#sno_acad').val()+deliminator
					+ $('#grade').val()+deliminator+$('#status').val()+deliminator
					+ $('#awardStatus').val()+deliminator+$('#email').val()+deliminator
					+ $('#phone').val()+deliminator+$('#college').val()+deliminator
					+ $('#dept').val()+deliminator+$('#snsType').val()+deliminator
					+ $('#sns_id').val()+deliminator+$('#address').val()+deliminator
					+ $('#profile').prop("src");
					
				//console.log(sendData)
				$.ajax({ 
					data :  sendData,
					type : "POST", 
					contentType:"application/json;charset=UTF-8",
					url : "/updateStudentInfo", 
					success : function(data) { 
						if(data == 1)	{
							alert("수정 사항이 올바르게 변경되었습니다.");
						}
						else	{
							alert("변경 사항이 올바르지 않습니다.");
						}
					}, 
					error : function(data) { 
						alert("데이터 변경을 실패하였습니다."); 
					} 
					}); 				
        	})        	
        	
        	/* console.log(${data}) */
        	
        	file = "${student.profile}"
        	if(file == "")	{
        		file = "/resources/img/image.jpeg"	
        	}
        	
        	$('#profile').prop("src", file);
        	
        	//권한 값에 따라서 tag를 설정
        	//일단 학생 : 0
        	//console.log(${sessionScope.auth})
        	if(${sessionScope.auth} == 2) 	{	// 관리자 
        		
        	}
        	else if(${sessionScope.auth} == 1)	{	//선생님
        		
        	}
        	else {	//학생 & 그 이외..
        		
        		//이름
        		document.getElementById("name_kor").disabled = true
        		document.getElementById("name_eng").disabled = true

        		//성별
        		if(${student.getSex()} == 1)	{ //male : 1
        			document.getElementById("male").checked = true
        		}
        		else	{ //female : 0
        			document.getElementById("female").checked = true
        		}
        		document.getElementById("male").disabled = true
        		document.getElementById("female").disabled = true
        		
        		//생년월일
        		document.getElementById("birth").disabled = true
        		
        		//대륙, 국적
        		document.getElementById("continent").disabled = true
        		document.getElementById("nationality").disabled = true
        	}
        	
        	
        	//공통적으로 모두 열람 및 수정할 수 있는 사항들
        	/****************************************************************************/
        	//단과대, 학과
    		$("#college").val("${student.college}").prop("selected", true);
    		itemChange()
    		$("#dept").val("${student.dept}").prop("selected", true);
    		
    		//SNS 정보 
    		$("#snsType").val("${student.snsType}").prop("selected", true);
    		
    		$("#grade").val("${student.grade}").prop("selected", true);
    		
    		$("#status").val("${student.status}").prop("selected", true);
    		$("#awardStatus").val("${student.awardStatus}").prop("selected", true);
    		
    		
    		type = "${student.sno_univ}"
    		if(type.length == 0 || type == "null")	{ // 한교원 
    			document.getElementById("sno_acad").disabled = false
				document.getElementById("sno_univ").disabled = true
				document.getElementById("btnKorEdu").checked = true
    		}
    		else	{ //대학교
    			document.getElementById("sno_acad").disabled = true
				document.getElementById("sno_univ").disabled = false
				document.getElementById("btnFstudent").checked = true
    		}        	
    		/****************************************************************************/
    		
    		
        	//TODO: 데이터 controller에서 받아와야 함
        	
			// 학적 정보 테이블        	
        	data = ${semesterInfo}
        	if(data != null)	{
        		$('#semesterInfo').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		//var vars = ['학기','이수 학점','성적','경고']
        		var vars = ['semester','credit','degree','warnings']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		//var bindings = ${data}
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#semesterInfo').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: [],
                'paging': true,
                'lengthChange': false,
                'searching': false,
                'ordering': true,
                'info': true,
                "bFilter": true,
                "bSort": true,
                "order": [[ 1, "asc" ]],
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        	}
        	else {
        		$('#semesterInfo').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#semesterInfo').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
        		})
        	} 
        	
        	
        	//신앙 정보 테이블
			data = ${holyInfo}
        	
        	//TODO: 데이터 controller에서 받아와야 함
        	if(data != null)	{
        		$('#holyInfo').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		var vars = ['semester', 'reading_session','worship','warnings']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		//var bindings = ${data}
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#holyInfo').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: [],
                'paging': true,
                'lengthChange': false,
                'ordering': true,
                'info': true,
                'searching': false,
                "bFilter": true,
                "bSort": true,
                "order": [[ 1, "asc" ]],
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        	}
        	else {
        		$('#holyInfo').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#holyInfo').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
        		})
        	} 
        	
        	//프로그램 참여 정보 테이블
			data = ${activeInfo}
        	
        	//TODO: 데이터 controller에서 받아와야 함
        	if(data != null)	{
        		$('#activeInfo').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		var vars = ['year',	'content', 'memo']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		//var bindings = ${data}
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#activeInfo').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: [],
                'paging': true,
                'lengthChange': false,
                'ordering': true,
                'info': true,
                'searching': false,
                "bFilter": true,
                "bSort": true,
                "order": [[ 1, "asc" ]],
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        	}
        	else {
        		$('#activeInfo').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#activeInfo').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
        		})
        	} 
			
        	//수상 이력 정보 테이블
			data = ${awardsInfo}
        	
        	//TODO: 데이터 controller에서 받아와야 함
        	if(data != null)	{
        		$('#awardsInfo').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		var vars = ['year','content','organization','memo']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		//var bindings = ${data}
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#awardsInfo').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: [],
                'paging': true,
                'lengthChange': false,
                'ordering': true,
                'info': true,
                'searching': false,
                "bFilter": true,
                "bSort": true,
                "order": [[ 1, "asc" ]],
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        	}
        	else {
        		$('#awardsInfo').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#awardsInfo').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
        		})
        	} 
			
			
        	//상담 이력 정보 테이블
			data = ${consultInfo}
        	
        	//TODO: 데이터 controller에서 받아와야 함
        	if(data != null)	{
        		$('#consultInfo').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		var vars = ['date','topic','memo','consultant']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		//var bindings = ${data}
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#consultInfo').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: [],
                'paging': true,
                'lengthChange': false,
                'ordering': true,
                'info': true,
                'searching': false,
                "bFilter": true,
                "bSort": true,
                "order": [[ 1, "asc" ]],
                scrollCollapse: true,
                "retrieve": true
        		})
        		
        	}
        	else {
        		$('#consultInfo').empty()
        		error = JSON.stringify(${data})
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#consultInfo').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
        		})
        	} 
        	
        });
        
        function itemChange() {

    		var collegeOfEngineering = [ "건축학과", "토목방재공학과", "기계공학과", "정보통신공학과",
    				"디스플레이반도체공학과", "전자공학과", "신소재공학과", "환경생명화학공학과", "산업경영공학과" ];
    		var collegeOfHumanitiesNSocialSciences = [ "국어국문학과", "사회복지학과",
    				"상담산업심리학과", "역사문화콘텐츠학부", "미디어커뮤니케이션학과", "법/경찰학과", "글로벌한국학과",
    				"시각디자인학과" ];
    		var collegeOfGlobalBusiness = [ "외국어자율전공학과", "경영학과", "IT경영학과",
    				"국제경제통상학과", "글로벌관광학과", "국제관계학과", "행정학과" ];
    		var collegeOfTheologicalCelibacy = [ "신학순결학과" ];
    		var collegeOfHealthNHealth = [ "제약생명공학과", "식품과학과", "수산생명의학과", "간호학과",
    				"물리치료학과", "치위생학과", "응급구조학과", "스포츠과학과", "무도경호학과" ];
    		var collegeOfSWConvergence = [ "스마트자동차공학과", "컴퓨터공학과", "글로벌소프트웨어학과" ];

    		var selectItem = $("#college").val();

    		var changeItem;

    		if (selectItem == "공과대학") {
    			changeItem = collegeOfEngineering;
    		} else if (selectItem == "인문사회대학") {
    			changeItem = collegeOfHumanitiesNSocialSciences;
    		} else if (selectItem == "글로벌비즈니스대학") {
    			changeItem = collegeOfGlobalBusiness;
    		} else if (selectItem == "신학순결대학") {
    			changeItem = collegeOfTheologicalCelibacy;
    		} else if (selectItem == "건강보건대학") {
    			changeItem = collegeOfHealthNHealth;
    		} else if (selectItem == "SW융합대학") {
    			changeItem = collegeOfSWConvergence;
    		}

    		$('#dept').empty();

    		for (var count = 0; count < changeItem.length; count++) {
    			var option = $("<option>" + changeItem[count] + "</option>");
    			$('#dept').append(option);
    		}
    	}        
</script>       
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
		<div class="row" >
			<div class="col-sm-12" style="text-align: center;">
	        	<h3 class="page-header">학생 정보 조회</h3>
	        </div>
	    </div>
		<div class="row">
			<div class="col-sm-6">
	        	<h3 class="page-header">기본 정보</h3>
	        </div>
	        <div class="col-sm-6" style="text-align: right;">
	        		<button id="btnUpdate" name="register">정보 수정</button> 
	        </div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<img id="profile" src="" width="200px" height="200px" alt="profile" title="${student.profile}">
				<form action="/uploadform" enctype="multipart/form-data" method="POST">
					<input type="file" name="file" style="width:76%;">
					<input type="submit" value="업로드"> 
				</form>
			</div>
			
			<div class="col-sm-3 sinfo" >
				<div>
					<label>구분:</label>
				</div>
				<div>
					<input type="radio" name="registerGroup" id="btnFstudent" value="대학교학생"/>
					<label for="btnFstudent">대학교학생</label>
					<input type="radio" name="registerGroup" id="btnKorEdu" value="한교원학생"/>
					<label for="btnKorEdu">한교원학생</label>
				</div>
				
				<div>
		        	<label>학부 학번:</label> 
		        </div>
		        <div>
		        	<input class="inputLabel" name="sno_univ" id="sno_univ" value="${student.sno_univ}">
		        </div>
		        
		        <div>
					<label>name(한글):</label>
				</div>
				<div>
					<input class="inputLabel" name="name_kor" id="name_kor" value="${student.name_kor}">
				</div>
				
			</div>
			<div class="col-sm-3 sinfo" >
				<div>
					<label>학년:</label>
				</div>
				<div>
					<select name="grade" id="grade">
						<option value="1">1학년</option>
						<option value="2">2학년</option>
						<option value="3">3학년</option>
						<option value="4">4학년</option>
						<option value="5">5학년 이상</option>
						<option value="0">해당 없음</option>
					</select>
				</div>
				<div>
		        	<label>한교원 학번:</label> 
		        </div>
		        <div>
		        	<input class="inputLabel" name="sno_acad" id="sno_acad" value="${student.sno_acad}">
		        </div>
				<div>
					<label>name(영어):</label>
				</div>
				<div>
					<input class="inputLabel" name="name_eng" id="name_eng" value="${student.name_eng}">
				</div>
			</div>
			
			<div class="col-sm-3 sinfo" style="text-align: left;">
				<div>
					<label>[현재상태]</label>
				</div>
				<div>
					<label class="inputLabel">학적:</label>
					<select name="status" id="status">
						<option value="재학">재학</option>
						<option value="휴학">휴학</option>
						<option value="인턴십">인턴십</option>
					</select>
				</div>
				<div>
					<label class="inputLabel">장학:</label>
					<select name="awardStatus" id="awardStatus">
						<option value="유지">유지</option>
						<option value="중지">중지</option>
						<option value="박탈">박탈</option>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row sinfo">
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label>birth:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="birth" id="birth" value="${student.birth}">
				</div>
				<div class="col-sm-3">
					<label>sex:</label>
				</div>
				<div class="col-sm-3">
					<input type="radio" id="male" name="sex" value="male" checked="checked" >
					<label for="male">Male</label></td>
					<input type="radio" id="female" name="sex" value="female">
					<label for="female">Female</label><br>
				</div>
			</div>
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label >continent:</label>
				</div>
				
				<div class="col-sm-3">
						<select name="continent" id="continent">
							<option value="Asia">Asia</option>
							<option value="Africa">Africa</option>
							<option value="North America">North America</option>
							<option value="South America">South America</option>
							<option value="Antarctica">Antarctica</option>
							<option value="Europe">Europe</option>
							<option value="Australia">Australia</option>
						</select>
				</div>
				<div class="col-sm-3">
					<label >nationality:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="nationality" id="nationality" value="${student.nationality}"/>
				</div>
			</div>    
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label >email:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="email" id="email"  value="${student.email}">
				</div>
				<div class="col-sm-3">
					<label >phone:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="phone" id="phone" value="${student.phone}">
				</div>
			</div>    
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label >college:</label>
				</div>
				<div class="col-sm-3">
					<select name="college" id="college" onchange="itemChange()">
						<option value="인문사회대학">인문사회대학</option>
						<option value="글로벌비즈니스대학">글로벌비즈니스대학</option>
						<option value="신학순결대학">신학순결대학</option>
						<option value="건강보건대학">건강보건대학</option>
						<option value="공과대학">공과대학</option>
						<option value="SW융합대학">SW융합대학</option>
					</select>
				</div>
				<div class="col-sm-3">
					<label >dept:</label>
				</div>
				<div class="col-sm-3">
					<select name="dept" id="dept">
					</select>
				</div>
			</div>
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label >sns:</label>
				</div>
				<div class="col-sm-3">
					<select name="snsType" id="snsType">
				        <option value="kakotalk">kakotalk</option>
				        <option value="Line">Line</option>
					</select>
				</div>
				<div class="col-sm-3">
					<label >id:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="sns_id" id="sns_id" value="${student.sns_id}">
				</div>
			</div>
			<div class="row col-sm-12">
				<div class="col-sm-3">
					<label >address:</label>
				</div>
				<div class="col-sm-3">
					<input class="inputLabel" name="address" id="address" value="${student.address}">
				</div>
			</div>
		</div>
		
		</br>
				
		<div>
			<div class="row">
	        	<h3 class="page-header">1. 학적 및 성적 이력</h3>
	        </div>
	        <div class="row">
	        	<div class="table table-bordered table-hover dataTable" id="semesterInfo"></div>
	        </div>
	    </div>    
	    
	    <div>
			<div class="row">
	        	<h3 class="page-header">2. 신앙출석률 이력</h3>
	        </div>
	        <div class="row">
	        	<div class="table table-bordered table-hover dataTable" id="holyInfo"></div>
	        </div>
	    </div>  
	    
	    <div>
			<div class="row">
	        	<h3 class="page-header">3. 방중 프로그램 참석 이력</h3>
	        </div>
	        <div class="row">
	        	<div class="table table-bordered table-hover dataTable" id="activeInfo"></div>
	        </div>
	    </div>  
	    <div>
			<div class="row">
	        	<h3 class="page-header">4. 수상 이력</h3>
	        </div>
	        <div class="row">
	        	<div class="table table-bordered table-hover dataTable" id="awardsInfo"></div>
	        </div>
	    </div>  
	    <div>
			<div class="row">
	        	<h3 class="page-header">5. 상담 이력</h3>
	        </div>
	        <div class="row">
	        	<div class="table table-bordered table-hover dataTable" id="consultInfo"></div>
	        </div>
	    </div>  
</div>
</body>
</html>