<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록한 가게</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<nav><br><jsp:include page="../mypage/mypageMenu2.jsp" flush="false"/></nav>
<section style="margin-left:250px">
	<h2><strong>등록한 가게</strong></h2>
	가게명을 클릭하시면 수정/삭제 하실 수 있습니다.<br>
	
	<table class="type05">
		<tr>
			<th>No.</th><th>가게명</th><th>주소</th><th>조회수</th>
		</tr>
		<tr>
		<c:forEach var="myStore" items="${myStoreList}" varStatus="loop">
			<td>${myStore.storeNum}</td>
			<td><a href="/sumbab/store/storeDetail/${myStore.storeNum}">${myStore.name}</a></td>
			<td>${myStore.citycode} ${myStore.districtcode} ${myStore.address}</td>
			<td>${myStore.count}</td>
		<tr>
			</c:forEach>
	</table>
</section>
</body>
</html>