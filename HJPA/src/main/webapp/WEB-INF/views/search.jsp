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
            #hjIcon {
        	border: none;
    		background: none;
		}
    </style>
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
  
<div class="container" >
	<div class="col-lg-12">
        <h3 class="page-header">Student Search</h3>
    </div>
    
    </br>
    
    <div class="row col-lg-12" id="searchContent">
    	<div class="col-lg-2">
        	<select class="btn-lg" name="searchCategory" id="searchCategory">
						<option value="nationality">국적</option>
						<option value="grade">학년</option>
						<option value="college">단과대</option>
						<option value="dept">학과</option>
						<option value="status">학적상태</option>
						<option value="awardStatus">장학상태</option>
						<option value="sex">성별</option>
						<option value="name_kor">이름(한글)</option>
			</select>
		</div>    	 
		<div class="col-lg-8">
       		<input id="searchText" type="text" class="form-control-lg" placeholder="Enter a search keyword.." style="width:100%">
		</div>
        <div class="col-lg-2">
        	<button id="searchBtn" type="button" class="btn btn-block btn-lg btn-primary">Search</button>
		</div>    	 
    </div>
    
    </br>
    </br>
    
	<div class="table table-bordered table-hover dataTable" id="result"></div>

    <!-- .modal -->
    <div class="modal fade" id="myCartModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              
              <h4 class="modal-title">Load stored results</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                                                                            </div>    
            </div>
            <div class="modal-footer">
                            
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
<!-- /.modal -->

<!-- .modal -->
    <div class="modal fade" id="loadModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Loading Now</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    Please Wait..
                        <i class="fa fa-refresh fa-spin"></i>
                </div>    
            </div>
            <div class="modal-footer">
            
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
<!-- /.modal -->     
    
</div>  
    
<footer class="main-footer">
<div class="pull-right hidden-xs">
    <b>Version</b> 1.0
</div>
</footer>
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
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
<script type="text/javascript">

        $(document).ready( function () {
        	$(document).keypress(function(e) {
				if (e.which == 13 && $('#searchText').val().length != 0) {
			    	e.preventDefault();
			   	    search();
			    }
		    });
		    
        	$("#searchBtn").on("click", function() {
        		search();
        	});
        	
        	/* console.log(${data}) */
        	if(${data} != null)	{
        		//alert(${data})
        		$('#result').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
        		var vars = ['pid','birth','nationality','name_kor','name_eng','email','phone','sns_id','sex','college','dept','sno_univ','sno_acad','snsType','continent','grade','status','awardStatus','totalCredit','totalGradeWarning', 'totalHolyWarning']
        		$(vars).each(function(k, v) {
        			tr.append('<th>' + v + '</th>')
        		})
        		var thead = $("<thead></thead>")
        		thead.append(tr)
        		$(table).append(thead)
        		
        		var tbody = $("<tbody></tbody>")
        		var bindings = ${data}
        		$(bindings).each(function(k, b) {
        			tr = $("<tr></tr>")
    					    					
        			$(vars).each(function(k2, v) {
        				if(v == 'totalWarnings')	{
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
        				}
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#result').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: ['copy', 'excel', 'pdf', 'print'],
                'paging': true,
                "scrollX": true,
                'lengthChange': false,
                'searching': true,
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
        
        function search() {
			var deliminator = "!@#";
			var txt = $('#searchText').val();
			if(txt.length == 0)	{
				txt = " ";
			}	
			
			var sendData = 
				$('#searchCategory').val() + deliminator
				+ txt;
			
			//console.log(sendData)
			$.ajax({ 
				data :  sendData,
				type : "POST", 
				contentType:"application/json;charset=UTF-8",
				dataType: "json",
				url : "/searchStudent", 
				success : function(data) { 
	        		$('#result').empty();
	        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
	        		var tr = $("<tr></tr>")
	        		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
	        		var vars = ['pid','birth','nationality','name_kor','name_eng','email','phone','sns_id','sex','college','dept','sno_univ','sno_acad','snsType','continent','grade','status','awardStatus','totalCredit','totalGradeWarning', 'totalHolyWarning']
	        		$(vars).each(function(k, v) {
	        			tr.append('<th>' + v + '</th>')
	        		})
	        		var thead = $("<thead></thead>")
	        		thead.append(tr)
	        		$(table).append(thead)
	        		
	        		var tbody = $("<tbody></tbody>")
	        		var bindings = data
	        		$(bindings).each(function(k, b) {
	        			tr = $("<tr></tr>");
	    					    					
	        			$(vars).each(function(k2, v) {
	        				if(v == 'totalWarnings')	{
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
	        				}
	        			})
	        			tbody.append(tr);
	        		})
	        		$(table).append(tbody)

	        		$('#result').append(table)
	        		table.DataTable({
	        			dom: 'Bfrtip',
	                    buttons: ['copy', 'excel', 'pdf', 'print'],
	                'paging': true,
	                "scrollX": true,
	                'lengthChange': false,
	                'searching': true,
	                'ordering': true,
	                'info': true,
	                "bFilter": true,
	                "bSort": true,
	                "order": [[ 0, "asc" ]],
	                'scrollCollapse': true,
	                "retrieve": true
	                
	        		});						
			        	
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
				}, 
				error : function(data) { 
					$('#result').empty()
	        		error = JSON.stringify(data)
	        		var table = $('<table class="table table-bordered table-hover"><tbody><tr><td>error: ' + error + '</td></tr></tbody></table>')
	        		$('#result').append(table)
	        		table.DataTable({
	        			bPaginate : false,		    
	        	        'paging': true
//	                'paging': true,
//	                'lengthChange': false,
//	                'searching': false,
//	                'ordering': true,
//	                'info': true,
//	                'autoWidth': true
	        		});
				} 
			}); 				
		};
        
        
</script>        
</body>
</html>