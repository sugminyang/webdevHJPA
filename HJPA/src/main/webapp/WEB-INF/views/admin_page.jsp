<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@pagepageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="dean">

  <title>HJPA Demo Page</title> 

  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Custom fonts for this template -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"  ></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
		<style>
		#hjIcon {
        	border: none;
    		background: none;
		}
	</style>
	
	<script type="text/javascript">
			$(document).ready(function() { 
				data = ${adminUserInfo}
	        	if(data != null)	{
	        		$('#adminUserInfo').empty()
	        		var table = $('<table id="adminUserInfoTable" class="table table-bordered table-hover"></table>')
	        		var tr = $("<tr></tr>")
	        		//var vars = ['학기','이수 학점','성적','경고']
	        		var vars = ['pid','id','password','action']
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
	        				if(v == 'action')	{
	        			        tr.append('<td><i class="fa fa-pencil-square" aria-hidden="true"></i><i class="fa fa-remove" aria-hidden="true"></i></td>')
	        				}
	        				else	{
	        					tr.append('<td>' + b[v] + '</td>')
	        				}
	        				
	        			})
	        			tbody.append(tr)
	        		})
	        		$(table).append(tbody)

	        		$('#adminUserInfo').append(table)
	        		table.DataTable({
	        			select: true, 
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
	        		});
	        		
	        		var table = $('#adminUserInfoTable').DataTable();
	        		table.column(0).visible(false);
	        	}
	        	else {
	        		$('#adminUserInfo').empty()
	        		error = JSON.stringify(data)
	        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
	        		$('#adminUserInfo').append(table)
	        		table.DataTable({
	        			bPaginate : false,		    
	        	        'paging': true
	        		});
	        	} 
	        	
	        	if(${sessionScope.auth} == 0)	{ // student
	        		$("#addRow_adminUserInfo").css("display","none");
	        		$("#adminUserInfoTable .fa").css("display","none")
	        	}
	        	
	        	
	            var table;

	            $('#addRow_adminUserInfo').on( 'click', function () {
	        		var table = $('#adminUserInfoTable').DataTable();        		
	        		var row = [0,"<input type='text' value=''\>",
	        			"<input type='text' value=''\>",
	        			'<i class="fa fa-save" aria-hidden="true"></i><i class="fa fa-remove" aria-hidden="true"></i>']
	        		
	                table.row.add(row).draw( false );
	                table.order([1, 'asc']).draw();

	            } );
	            
	            $("#adminUserInfo").on('mousedown.edit', "i.fa.fa-pencil-square", function(e) {

	              $(this).removeClass().addClass("fa fa-save");
	              var $row = $(this).closest("tr").off("mousedown");
	              var $tds = $row.find("td").not('first').not(':last');

	              $.each($tds, function(i, el) {
	                var txt = $(this).text();
	                $(this).html("").append("<input type='text' value=\""+txt+"\">");
	              });

	            });
	            
	            $("#adminUserInfo").on('mousedown',"i.fa.fa-remove", function(e) {
	            	if(confirm("삭제 하시겠습니까?"))
					 {
	           			$(this).removeClass().addClass("fa fa-pencil-square");
	                   var $row = $(this).closest("tr");
	                   
	     			  var table = $('#adminUserInfoTable').DataTable();
	     			  table.column(0).visible(true);
	     			  var pid = $row.find("td:first").text();
	     			  table.column(0).visible(false);
	     			  
	     			  var $tds = $row.find("td").not('first').not(':last');
	     			  
	     			  var editData = pid
	     			  
	     			  
	     			  //console.log(editData);
	     			  
	     			  $.ajax({ 
	     					data :  editData,
	     					type : "POST", 
	     					contentType:"application/json;charset=UTF-8",
	     					url : "/adminUserRemove", 
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
	     			  table.columns.adjust().draw();
	               	var table = $('#adminUserInfoTable').DataTable();
	               	table
	                   .row( $(this).parents('tr') )
	                   .remove()
	                   .draw();
					 }
					 else
					 {
					 }
	            });

	            $("#adminUserInfo").on('mousedown', "input", function(e) {
	              e.stopPropagation();
	            });

	            $("#adminUserInfo").on('mousedown.save', "i.fa.fa-save", function(e) {
	              $(this).removeClass().addClass("fa fa-pencil-square");
	              var $row = $(this).closest("tr");
	              
				  var table = $('#adminUserInfoTable').DataTable();
				  table.column(0).visible(true);
				  var pid = $row.find("td:first").text();
				  table.column(0).visible(false);
				  
				  var $tds = $row.find("td").not('first').not(':last');
				  
				  var delimiter = "!@#";
				  var editData = ""
				  
	              $.each($tds, function(i, el) {
	                var txt = $(this).find("input").val()
	                $(this).html(txt);
	                editData = editData + txt + delimiter
	              });
				  
				  editData = editData + pid
				  
				  //console.log(editData);
				  
				  $.ajax({ 
						data :  editData,
						type : "POST", 
						contentType:"application/json;charset=UTF-8",
						url : "/adminUserEdit", 
						success : function(data) { 
							if(data != 0)	{
								alert("수정 사항이 올바르게 변경되었습니다.");
								table.column(0).visible(true);
								$row.find("td:first").text(data);
								table.column(0).visible(false);
							}
							else	{
								alert("변경 사항이 올바르지 않습니다.");
							}
						}, 
						error : function(data) { 
							alert("데이터 변경을 실패하였습니다."); 
						} 
					});
				  table.columns.adjust().draw();
	            });
	            
	            
	            $("#execBtn").click(function() {
	            	if($("#bulkFile").val() != "")	{
				      // disable button
				      $(this).prop("disabled", true);
				      // add spinner to button
				      $(this).html(
				        '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Processing...'
				      );
				      
				      $('#submitBtn').trigger("click");
	            	}
				});
	            
	            
	            $("#pwClear").click(function() {
	            	var editData = "@!id:"+$("#id").val();
					  
					  //console.log(editData);
					  
					  $.ajax({ 
							data :  editData,
							type : "POST", 
							contentType:"application/json;charset=UTF-8",
							url : "/changepassword", 
							success : function(data) { 
								if(data != 0)	{
									alert("비밀번호가 초기화되었습니다.");
								}
								else	{
									alert("비밀번호가 초기화를 실패하였습니다.");
								}
							}, 
							error : function(data) { 
								alert("error: 비밀번호가 초기화를 실패하였습니다."); 
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
      				</c:when>
      				<c:when test="${sessionScope.auth == 1}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
      				</c:when>
      				<c:when test="${sessionScope.auth == 2}">
      					<a class="btn btn-primary" href="/search">학생 정보 검색</a>
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

  <div class="container" >
		<div class="col-lg-12" align="center">
	        <h3 class="page-header">system admin page</h3>
	    </div>
	  	</br>
	  	
	  	<div class="row">
	  		<b>관리자 계정 생성</b>
	  	</div>
	  	<div class="row">
			<button id="addRow_adminUserInfo">Add new row</button>
			<div class="table table-bordered table-hover dataTable" id="adminUserInfo"></div>
	    </div>
	    </br>
	    
	    <div class="row">
	  		<b>학생 이력 정보 일괄 삽입</b>
	  	</div>
	  	<div class="row">
	  		<form action="/uploadBulkFile" enctype="multipart/form-data" method="POST">
				<input id="bulkFile" type="file" name="file" accept=".txt">
				<input id="submitBtn" type="submit" hidden="true"> 
				<button type="button" id="execBtn" class="btn btn-primary mb-2">입력 실행</button>
			</form>
	  	</div>
	  	</br>
	  	
     	<div class="row">
	  		<b>학생 계정 비밀번호 변경</b>
	  	</div>
		<div class="row">
			<label>아이디: </label> 
			<input class="col-sd-3" id="id" name="id" name="file">
			<button type="button" id="pwClear" class="btn btn-primary mb-2">비밀번호 초기화</button>
		</div>
		</br>
     	  	
  </div>

  <!-- Footer -->
  <footer class="footer bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 h-100 text-center text-lg-left my-auto">
          <ul class="list-inline mb-2">
            <li class="list-inline-item">
              <a href="/">About</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="/">Contact</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="/">Terms of Use</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="/">Privacy Policy</a>
            </li>
          </ul>
          <p class="text-muted small mb-4 mb-lg-0">&copy; Your Website 2020. All Rights Reserved.</p>
        </div>
      </div>
    </div>
  </footer>

</body>

</html>
