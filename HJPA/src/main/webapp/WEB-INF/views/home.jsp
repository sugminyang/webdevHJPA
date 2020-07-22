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
<!-- 	      <a class="btn btn-primary" id="register" href="register">Sign In</a>
	      <a class="btn btn-primary" id="loginBtn" href="login">Login</a> -->
      </div>
    </div>
  </nav>

  <!-- Masthead -->
  <header class="masthead text-white text-center">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-xl-9 mx-auto">
          <h1 class="mb-5">Scholarship Management Service of Hyo Jeong World Peace Foundation</h1>
        </div>
        <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
          <form>
            <div class="form-row">
              <!-- <div class="col-12 col-md-9 mb-2 mb-md-0">
                <input type="number" class="form-control form-control-lg" placeholder="Enter a student number...">
              </div>
              <div class="col-12 col-md-3">
                <button type="submit" class="btn btn-block btn-lg btn-primary">Search</button>
              </div> -->
            </div>
          </form>
        </div>
      </div>
    </div>
  </header>

<div class="container">
		<div class="la_notice">
			<h2>공지 사항</h2>
			<style>
.text_two {
	zoom: 1;
	position: relative;
}

.text_two:after {
	visibility: hidden;
	content: "";
	display: block;
	clear: both
}

a.more_latest {
	position: absolute;
	right: 0;
	top: -50px;
	color: #444;
	font-size: 12px;
	letter-spacing: 1px;
}

.text_two a {
	text-decoration: none
}

.text_two .main {
	margin-top: 20px;
}

.text_two .main:after {
	clear: both;
	display: block;
	content: '';
}

.text_two .main>div {
	width: 100%;
	font-family: 'malgun gothic';
}

.text_two .main>div a {
	color: #000;
	font-size: 16px;
	font-weight: 600;
}

.text_two .main>div span {
	display: block;
	font-size: 12px;
	color: #9f9f9f;
	margin-top: 7px;
}

.text_two .main>div p {
	color: #666;
	font-size: 13px;
	margin-top: 2px;
	line-height: 1.2;
}

.text_two .sub {
	
}

.text_two .sub ul {
	padding: 0;
	margin: 0;
	list-style: none
}

.text_two .sub ul li {
	padding: 5px 0;
	zoom: 1;
	position: relative;
	font-family: 'malgun gothic';
	font-size: 14px;
}

.text_two .sub ul li a {
	color: #666;
}

.text_two .sub ul .fst {
	padding-top: 20px
}

.text_two .sub ul li span {
	position: absolute;
	right: 0;
	font-size: 12px;
	color: #9f9f9f;
}
</style>
			<div class="la_notice">
				<div class="text_two">
					<a href='/noticeList' class="more_latest">more</a>
					<div class="main">
						<div>
							<a href="./bbs/board.php?bo_table=41&wr_id=447">[효정평화장학] 2020
								효정평화장학생 최종 선정자 발…</a> <span class="time">2020-07-16</span>
							<p>효정평화장학생에 신청해주신 모든 분들께 깊은 감사의 말씀을 전합니다.&nbsp; 아래와 같이 최종
								선정자를 발표합니다.&nbsp; 문자로도 개별연락을 드렸…</p>
						</div>
					</div>

					<div class="sub">
						<ul>
							<li class="fst"><a
								href="./bbs/board.php?bo_table=41&wr_id=446"> [원모장학] 2020
									인재육성장학 해외부문 서류심사 … <span class="txt_sub">2020-07-14</span>
							</a></li>
							<li><a href="./bbs/board.php?bo_table=41&wr_id=445">
									[효정평화장학] 2020 효정평화장학생 면접대상자 공고 <span class="txt_sub">2020-07-08</span>
							</a></li>
							<li><a href="./bbs/board.php?bo_table=41&wr_id=444">
									[멘토링캠프] '2020 여름 온라인 티앤토멘토링학습캠… <span class="txt_sub">2020-07-02</span>
							</a></li>
						</ul>
					</div>
				</div>
				<!-- }  최신글 끝 -->
			</div>
	</div>
</div>

<%-- 
	<!-- Icons Grid -->
  <section class="features-icons bg-light text-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-screen-desktop m-auto text-primary"></i>
            </div>
            <h3>Student Search</h3>
            <p class="lead mb-0">This theme will look great on any device, no matter the size!</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-layers m-auto text-primary"></i>
            </div>
            <h3>Student Management</h3>
            <p class="lead mb-0">Featuring the latest build of the new Bootstrap 4 framework!</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="features-icons-item mx-auto mb-0 mb-lg-3">
            <div class="features-icons-icon d-flex">
              <i class="icon-check m-auto text-primary"></i>
            </div>
            <h3>Modify Student Information</h3>
            <p class="lead mb-0">Ready to use with your own content, or customize the source files!</p>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- Image Showcases -->
  <section class="showcase">
    <div class="container-fluid p-0">
      <div class="row no-gutters">

        <div class="col-lg-6 order-lg-2 text-white showcase-img" style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-showcase-1.jpg');"></div>
        <div class="col-lg-6 order-lg-1 my-auto showcase-text">
          <h2>Student Search</h2>
          <p class="lead mb-0">When you use a theme created by Start Bootstrap, you know that the theme will look great on any device, whether it's a phone, tablet, or desktop the page will behave responsively!</p>
        </div>
      </div>
      <div class="row no-gutters">
        <div class="col-lg-6 text-white showcase-img" style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-showcase-2.jpg');"></div>
        <div class="col-lg-6 my-auto showcase-text">
          <h2>Student Management</h2>
          <p class="lead mb-0">Newly improved, and full of great utility classes, Bootstrap 4 is leading the way in mobile responsive web development! All of the themes on Start Bootstrap are now using Bootstrap 4!</p>
        </div>
      </div>
      <div class="row no-gutters">
        <div class="col-lg-6 order-lg-2 text-white showcase-img" style="background-image: url('${pageContext.request.contextPath}/resources/img/bg-showcase-3.jpg');"></div>
        <div class="col-lg-6 order-lg-1 my-auto showcase-text">
          <h2>Modify Student Information</h2>
          <p class="lead mb-0">Landing Page is just HTML and CSS with a splash of SCSS for users who demand some deeper customization options. Out of the box, just add your content and images, and your new landing page will be ready to go!</p>
        </div>
      </div>
    </div>
  </section>

  <!-- Testimonials -->
  <section class="testimonials text-center bg-light">
    <div class="container">
      <h2 class="mb-5">What people are saying...</h2>
      <div class="row">
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="${pageContext.request.contextPath}/resources/img/testimonials-1.jpg" alt="">
            <h5>Margaret E.</h5>
            <p class="font-weight-light mb-0">"This is fantastic! Thanks so much guys!"</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="${pageContext.request.contextPath}/resources/img/testimonials-2.jpg" alt="">
            <h5>Fred S.</h5>
            <p class="font-weight-light mb-0">"Bootstrap is amazing. I've been using it to create lots of super nice landing pages."</p>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="testimonial-item mx-auto mb-5 mb-lg-0">
            <img class="img-fluid rounded-circle mb-3" src="${pageContext.request.contextPath}/resources/img/testimonials-3.jpg" alt="">
            <h5>Sarah W.</h5>
            <p class="font-weight-light mb-0">"Thanks so much for making these free resources available to us!"</p>
          </div>
        </div>
      </div>
    </div>
  </section>
 --%>
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
        <!-- <div class="col-lg-6 h-100 text-center text-lg-right my-auto">
          <ul class="list-inline mb-0">
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-facebook fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-twitter-square fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-instagram fa-2x fa-fw"></i>
              </a>
            </li>
          </ul>
        </div> -->
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
