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
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>

<style>
	td {
		height: 30;
	  	vertical-align: middle;
	}
	table{
		margin-bottom: 15px;
	}
	button{
		margin-left: 15px;
		margin-right: 15px;
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

	$(document).ready(function() {
		// 취소
		$(".cancel").on("click", function() {
			location.href = "/";
		})

		//첫 데이터는 대학교
		var isUniv = $('input[name="registerGroup"]:checked').val();
		if (isUniv == "대학교학생") {
			document.getElementById("sno_acad").disabled = true
			document.getElementById("sno_univ").disabled = false
		} else {
			document.getElementById("sno_acad").disabled = false
			document.getElementById("sno_univ").disabled = true
		}

		//초기 dept는 인문사회대학교
		itemChange()
		
		$("#register").on("click", function() {
			if ($("#id").val() == "") {
				alert("아이디를 입력해주세요.");
				$("#id").focus();
				return false;
			}
			if ($("#password").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return false;
			}
			if ($("#name_kor").val() == "") {
				alert("한글 이름을 입력해주세요.");
				$("#name_kor").focus();
				return false;
			}
			if ($("#name_eng").val() == "") {
				alert("영어 이름을 입력해주세요.");
				$("#name_eng").focus();
				return false;
			}
			if ($("#email").val() == "") {
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false;
			}
			if ($("#address").val() == "") {
				alert("주소를 입력해주세요.");
				$("#address").focus();
				return false;
			}
			if ($("#birth").val() == "") {
				alert("생년월일을 입력해주세요.");
				$("#birth").focus();
				return false;
			}
			if ($("#nationality").val() == "") {
				alert("국적을 입력해주세요.");
				$("#nationality").focus();
				return false;
			}
			if ($("#phone").val() == "") {
				alert("휴대전화 번호를 입력해주세요.");
				$("#phone").focus();
				return false;
			}
			if ($("#sns_id").val() == "") {
				alert("sns id를 입력해주세요.");
				$("#sns_id").focus();
				return false;
			}
			if ($("#college").val() == "") {
				alert("단과대를 입력해주세요.");
				$("#college").focus();
				return false;
			}

			var isUniv = $('input[name="registerGroup"]:checked').val();
			if (isUniv == "대학교학생") {
				if ($("#sno_univ").val() == "") {
					alert("학부 학번을 입력해주세요.");
					$("#sno_univ").focus();
					return false;
				}
			} else {
				if ($("#sno_acad").val() == "") {
					alert("학교원 학번을 입력해주세요.");
					$("#sno_acad").focus();
					return false;
				}
			}

			var idChkVal = $("#idChkBox").val();
			if (idChkVal == "Y") {
				$("#sno_univ").prop('disabled', false);
				$("#sno_acad").prop('disabled', false);
				$("#id").prop('disabled', false);
				$("#regForm").submit();
				return true;
			} else {
				alert("중복확인 버튼을 눌러주세요.");
				$("#id").focus();
				return false;
			}
		});
	})

	function fn_idChk() {
		$.ajax({
			url : "/idchk",
			type : "post",
			dataType : "json",
			data : {
				"id" : $("#id").val()
			},
			success : function(data) {
				/* console.log(data) */
				var data = JSON.stringify(data);

				if (data == 1) {
					alert("중복된 아이디입니다.");
					$("#idChkBox").attr("value", "N");
					$("#id").prop('disabled', false);
					$("#id").focus();
				} else if (data == 0) {
					$("#idChkBox").attr("value", "Y");
					$("#id").prop('disabled', true);
					alert("사용가능한 아이디입니다.");
				}
			}
		})
	}

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
		<div class="float-right">
			<a class="btn btn-primary" href="register">Register</a> 
			<a class="btn btn-primary" id="loginBtn" href="login">Sign In</a>
		</div>
	</div>
	</nav>
	
	<div> 
		<table align="center">
			<tr>
				<td align="center"><label >Register classification</label></td>
				<td align="center">
					<input type="radio" name="registerGroup" id="btnFstudent" value="대학교학생" checked="checked"/>
					<label for="btnFstudent">대학교학생</label>
				<td align="center">
					<input type="radio" name="registerGroup" id="btnKorEdu" value="한교원학생"/>
					<label for="btnKorEdu">한교원학생</label>
				</td>
			</tr>
			<tr>
				<td><label>id</label></td>
				<td><input name="id" id="id" /></td>
				<td><input type="button" value="중복 확인" id="idChk" onclick="fn_idChk();"/></td>
				<td><input type="hidden" id="idChkBox"value="N"/></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input name="password" type="password" id="password" /></td>
			</tr>
			<tr>
				<td><label>대학교 학번</label></td>
				<td><input name="sno_univ" id="sno_univ" /></td>
			</tr>	
			<tr>
				<td><label>한교원 학번</label></td>
				<td><input name="sno_acad" id="sno_acad" /></td>
			</tr>						
			<tr>
				<td><label>name_kor</label></td>
				<td><input name="name_kor" id="name_kor" /></td>
				<td style="text-align: center;">
					<label style="font-style: italic; color: red;">외국인 등록증 기준</label>
				</td>
				
			</tr>
			<tr>
				<td><label >name_eng</label></td>
				<td><input name="name_eng" id="name_eng" /></td>
				<td style="text-align: center;">
					<label style="font-style: italic; color: red;">외국인 등록증 기준</label>
				</td>
			</tr>			
			<tr>
				<td><label>Email</label></td>
				<td><input name="email" id="email" /></td>
			</tr>
			<tr>
				<td><label>Address</label></td>
				<td><input name="address" id="address" /></td>
				<td style="text-align: center;">
					<label style="font-style: italic; color: red;">parent’s address</label>
				</td>
				
			</tr>
			<tr>
				<td><label>birthday</label></td>
				<td><input name="birth" id="birth" placeholder="901124"/></td>
			</tr>
			<tr>
				<td><label >nationality</label></td>
				<td style="text-align: center;">
				    <select name="continent" id="continent">
				        <option value="Asia">Asia</option>
				        <option value="Africa">Africa</option>
				        <option value="North America">North America</option>
				        <option value="South America">South America</option>
				        <option value="Antarctica">Antarctica</option>
				        <option value="Europe">Europe</option>
				        <option value="Australia">Australia</option>
					</select>
				</td>   
				<td style="text-align: center;">
					<input name="nationality" id="nationality" />
				</td>
			</tr>	
			<tr>
				<td><label >phone</label></td>
				<td><input name="phone" id="phone" placeholder="01011112222"/></td>
				<td style="text-align: center;">
					<label style="font-style: italic; color: red;">휴대폰이 없을 시 '휴대폰 없음'입력</label>
				</td>
				
			</tr>				
			<tr>
				<td align="left">
					<label>sns</label>
				</td>
				<td style="text-align: center;">
				    <select name="snsType" id="snsType">
				        <option value="kakotalk">kakotalk</option>
				        <option value="Line">Line</option>
					</select>
				</td>    
				<td align="center">   
				    <input name="sns_id" id="sns_id">
			    </td>
			    <td style="text-align: center;">
			    	<label style="font-style: italic; color: red;">'kakaotalk'권장</label>
			    </td>
			</tr>	
			<tr>
				<td><label >gender</label></td>
				<td align="center">
					<input type="radio" id="male" name="sex" value="male" checked="checked" >
					<label for="male">Male</label></td>
				<td align="center">
					<input type="radio" id="female" name="sex" value="female">
					<label for="female">Female</label><br>
				</td>
			</tr>
			<tr>
				<td align="left">
					<label>department</label>
				</td>
				<td style="text-align: center;">
				    <select name="college" id="college" onchange="itemChange()">
				        <option value="인문사회대학">인문사회대학</option>
				        <option value="글로벌비즈니스대학">글로벌비즈니스대학</option>
				        <option value="신학순결대학">신학순결대학</option>
				        <option value="건강보건대학">건강보건대학</option>
				        <option value="공과대학">공과대학</option>
				        <option value="SW융합대학">SW융합대학</option>
				        
					</select>
				</td> 
				<td style="text-align: center;">
					<select name="dept" id="dept">
					</select>
				</td>
			<tr>
			</tr>	    
		</table>
	</div>
	<div align="center" class="container">	
		<form id="regForm" action="registerProcess" method="post">		
			<button id="register" name="register">Register</button>
			<button id="cancel" name="cancel" value="Y">Cancel</button>
		</form>
	</div>
  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>