<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		#hjIcon {
        	border: none;
    		background: none;
		}
	</style>
	
<script>
    $(document).ready(function(){
        $("#notice_regi").on("click",function(){
            location.href="/noticeRegi"
        });
    });
    function fn_paging(curPage){
        location.href="/noticeList?curPage="+curPage;
    }
    function notice_push(notice_id){
        alert(notice_id);
    }
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
      </div>
    </div>
  </nav>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header" align="center">공지사항</div>

					<input type="button" id="notice_regi" value="등록">
					</br>
					<div class="table table-bordered table-hover dataTable" id="result"></div>
				</div>
			</div>
		</div>
	</div>



<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
    
	<script type="text/javascript">

        $(document).ready( function () {
        	
        	/* console.log(${data}) */
        	data = ${noticeList}
        	if(data != null)	{
        		//alert(${data})
        		$('#result').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
        		var vars = ['notice_id','notice_title','mod_date']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		var bindings = data
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				/* if(v == 'totalWarnings')	{
        					//tr.append('<td> 성적:' + b['totalGradeWarning'] + ', 신앙: ' + b['totalHolyWarning']+ '</td>')
        				}
        				else if(v == 'totalGradeWarning')	{
        					tr.append('<td>성적경고:' + b['totalGradeWarning'] + '</td>')
        				}
        				else if(v == 'totalHolyWarning')	{
        					tr.append('<td>신앙경고:' + b['totalHolyWarning'] + '</td>')
        				}
        				else	{
        					tr.append('<td>' + b[v] + '</td>')
        				} */
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#result').append(table)
        		table.DataTable({
                'paging': true,
                "scrollX": false,
                'lengthChange': false,
                'searching': false,
                'ordering': true,
                'info': true,
                "bFilter": true,
                "bSort": true,
                "order": [[ 0, "asc" ]],
                'scrollCollapse': true,
                "retrieve": true
        		});
        		
        	}
        	else {
//        		console.log(${data})
        		$('#result').empty()
        		error = JSON.stringify(data)
        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
        		$('#result').append(table)
        		table.DataTable({
        			bPaginate : false,		    
        	        'paging': true
//                'paging': true,
//                'lengthChange': false,
//                'searching': false,
//                'ordering': true,
//                'info': true,
//                'autoWidth': true
        		});
        	}
        	
        	$('#MydataTable tbody').on( 'click', 'tr', function () {
        		var table = $('#MydataTable').DataTable();
    			var pid = table.row( this ).data()[0];
    			$.ajax({
    				'type': "POST", 
    				'url': 'getStudentInfo',
    				'data':{pid:pid},
    				success : function(data) { 
					}, 
					error : function(data) { 
					} 
    			   });
    			
    			window.location.href = "/studentInfo";
        	});
        });
        
</script>             
</body>
</html>