<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>menu bar</title>
	 <!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />
</head>
<body>
<script>
function Mypage_check(){ 
    var id = '<%=session.getAttribute("id")%>';
    var classify = <%=session.getAttribute("classify")%>;
    var pwd = '<%=session.getAttribute("pwd")%>';
    console.log("id");
    console.log(classify);
    console.log("pwd");

     if(id=="null"){ 
    	 alert("로그인이 필요한 서비스입니다. 로그인 후 이용해 주세요!"); 
     }
     else if (classify == '3') {
    	 location.replace("/sumbab/mypage/noticePage");
     }
     else {
         location.replace("/sumbab/mypage/memberMyPage");
      }
}
function Warning_check(){ 
	  var id = '<%=session.getAttribute("id")%>';
    if(id == "null"){ 
    	 alert("로그인이 필요한 서비스입니다. 로그인 후 이용해 주세요!"); 
    }
    else{
       location.replace("/sumbab/mypage/noticePage");
    }
}  
</script>
	  <!-- Navigation-->
	  <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="/sumbab/">SUMBAB </a><a class="navbar-brand" href="/sumbab/sumbabStart"><i class="fas fa-search"></i></a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a class="nav-link" href="/sumbab/aboutSumbab">About 숨밥</a></li>
                        <li class="nav-item"><a class="nav-link" href="javascript:Warning_check();">공지</a></li>
                        <c:choose>
                        	<c:when test="${sessionScope.classify == 2}">
                       			<li class="nav-item"><a class="nav-link" href="/sumbab/store/registerStep1">가게 등록하기</a></li>
                        	</c:when>
                        	<c:when test="${sessionScope.classify != 3}">
                       			<li class="nav-item"><a class="nav-link" href="/sumbab/pick/storage">보관함</a></li>
                        	</c:when>
                        </c:choose>
                       <li class="nav-item"><a class="nav-link" href="javascript:Mypage_check();">마이페이지</a></li>
                         <c:choose>
  					<c:when test="${sessionScope.id == null}">
                        <li class="nav-item"><a class="nav-link" href="/sumbab/login/login">로그인</a></li>
  					</c:when>
    				<c:otherwise>
                        <li class="nav-item" style="padding: 0.5rem 1rem">${sessionScope.id}님</li>
                        <li class="nav-item"><a class="nav-link" href="/sumbab/login/logout">로그아웃</a></li>
  				</c:otherwise>
				</c:choose>
                     </ul>
                </div>
            </div>
        </nav>
        </header>
<!-- Bootstrap core JS-->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script>
	jQuery.noConflict();
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
	<script src="/sumbab/resources/js/scripts.js"></script>
</body>
</html>