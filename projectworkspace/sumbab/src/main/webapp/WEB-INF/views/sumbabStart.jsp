<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숨밥 시작하기</title>
<link rel="stylesheet" href="/sumbab/resources/css/button.css">
<style>
body {
	padding: 0px;
	margin: 0px;
}
</style>


</head>

<body style="text-align:center;">
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

	<div id="searchBar">
		<h1>숨밥꼭질 시작하기</h1><br>
		<h2>음식점/카페 찾아보기</h2>
		가게이름, 메뉴, 지역, 해시태그 등을 검색해 보세요!
		<br> 해시태그 검색은 앞에 #를 붙여서 검색하세요.
		<br>
	<form:form commandName="inputForm" method="post" action="selectByInput">
		<input type="text" id="input" name="input" placeholder="키워드를 입력하세요." required>
		<input type="submit" class="button" value="검색">
	</form:form>
	</div>
<br><hr>
	<div id="divPosition" style="">
		<h2>위치 기반 추천</h2>
		주변에 있는 맛집을 추천 받으세요!<br><br>
		
		<a href="<c:url value = "Gps/GPSlocation"/>">
			<img src="/sumbab/resources/img/Mapimg.png" style="border:1px solid #5D5D5D; width:20%; text-align:center"/><br>
			현재 내 위치에서 근방 맛집 보기
		</a>
	</div>

<br><hr>
<div>
	<jsp:include page="recommendPage/mergeRecommend.jsp" flush="true"/>
</div>
<br><br><br><br><br>





</body>
</html>
