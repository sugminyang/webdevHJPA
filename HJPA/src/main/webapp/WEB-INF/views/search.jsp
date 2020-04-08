<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Search</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/bootstrap.css">
  <link rel="stylesheet" href="http://bike-bee.snu.ac.kr/css/usebootstrap.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 

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
    </style>
</head>
<body>

<div class="container" >
	<div class="col-lg-12">
        <h3 class="page-header">Student Search</h3>
    </div>
    <div class="col-lg-12" id="searchContent">
    	<!-- <p>search drug name: <b>${drugname}</b></p> -->
    </div>
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
    <script src="/resources/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
<script>
        $(document).ready( function () {
        	/* searchctx = ""
        	
        	if("${drugname}".length != 0)	{
        		//$("#searchContent").innerHTML("<p>search drug name: <b>${drugname}</b></p>");
        		searchctx = "<p>search drug name: <b>${drugname}</b></p>";
        	}
        	
        	if("${diseasename}".length != 0)	{
        		searchctx += "<p>search disease name: <b>${diseasename}</b></p>";
        	}
        	
        	$("#searchContent").text(searchctx); */
        	
        	if(${data} != null)	{
        		//alert(${data})
        		$('#result').empty()
        		var table = $('<table id="MydataTable" class="table table-bordered table-hover"></table>')
        		var tr = $("<tr></tr>")
        		//var vars = ['disease','gene','interaction_types','drug_name','drug_summary','interaction_claim_source']	//old
        		var vars = ['pid','sno','name_kor','grade','college','dept','nationality','name_eng','email','phone']
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
        				tr.append('<td>' + b[v] + '</td>')
        			})
        			tbody.append(tr)
        		})
        		$(table).append(tbody)

        		$('#result').append(table)
        		table.DataTable({
        			dom: 'Bfrtip',
                    buttons: ['copy', 'excel', 'pdf', 'print'],
                'paging': true,
                'lengthChange': false,
                'searching': true,
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
//        		console.log(${data})
        		$('#result').empty()
        		error = JSON.stringify(${data})
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
        		})
        	}
        	
        	
        	 
        	$('#MydataTable tbody').on( 'click', 'tr', function () {
        		var table = $('#MydataTable').DataTable();
//        	    alert( 'Row index: '+table.row( this ).index() );
//        	    console.log(table.row( this ).data()[0]);
//        	    console.log(table.row( this ).data()[1].indexOf("\">"));
				var diseasename = table.row( this ).data()[0];
        	    var gene = table.row( this ).data()[1];
        	    var st = table.row( this ).data()[1].indexOf("\">") + 2;
        	    var ed = table.row( this ).data()[1].indexOf("</a>");
//        	    console.log(gene.substring(st,ed))
        	    gene = gene.substring(st,ed)
        	    
        	    
        	    url = "rowinfo?genesymbol=" + gene + "&diseasename=" + diseasename
	    		url += "&output=json"
	    
        	    $.ajax({
	            'type': "GET",
	            'url': url,
	            'dataType': "json",
	            'error': rowinfo_fail,
	            'success': rowinfo_success
	        	})
        	} );
            
			function rowinfo_success(data)	{
//				console.log(data)
				$("#pmidList").text(data)
				
				$('#pmidList').empty()
				var table = $('<table id="pmidTable" class="table table-bordered table-hover"></table>')
				var tr = $("<tr></tr>")
				var vars = ['idx','title','author','journal','paperVisualization']
				$(vars).each(function(k, v) {
					tr.append('<th>' + v + '</th>')
				})
				var thead = $("<thead></thead>")
				thead.append(tr)
				$(table).append(thead)
				var cnt = 1;
				var tbody = $("<tbody></tbody>")
				var bindings = data
				
				$(bindings).each(function(k, b) {
					tr = $("<tr></tr>")
					$(vars).each(function(k2, v) {
						if(v == 'paperVisualization')	{
							tr.append('<td>'+'<button> <a target="_blank" href="' + document.location.origin + '' + '/${mode}/paperviz?pmid='+ b["pmid"]+'">' + b["pmid"] + '</a></button>' + '</td>')
//						console.log('<td>'+'<button> <a target="_blank" href="' + document.location.origin + '' + '/${mode}/paperviz?pmid='+ b+'">' + b + '</a></button>' + '</td>')
						}
						else if(v == 'idx')	{
							tr.append('<td><p style="text-align: center;">' + cnt + '</p></td>')
						}
						else if(v == 'author')	{
							tr.append('<td><p style="font-size: 10px;">' + b[v] + '</p></td>')
						}
						else	{
        					tr.append('<td>' + b[v] + '</td>')
        				}
					})
					cnt++;
					tbody.append(tr)	
				})
				
				$(table).append(tbody)
		
				$('#pmidList').append(table)
        		table.DataTable({
                'paging': true,
                'lengthChange': false,
                'searching': true,
                'ordering': true,
                'info': true,
                "bFilter": true,
                "bSort": true,
                scrollCollapse: true,
                "retrieve": true
        		})
			}
        	    
			function rowinfo_fail(error) {
        		console.log("no information")
			}
        });
</script>        
</body>
</html>