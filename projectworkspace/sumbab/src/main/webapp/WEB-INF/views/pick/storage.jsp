<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 보관함</title>
<style>
body {
	padding: 0px;
	margin: 0px;
}

.button {
  background-color: white; 
  color: black; 
  border: 2px solid #ff9478;
  padding: 4px 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 15px;
}

.button:hover {
  background-color: #ff9478;
  color: white;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<c:if test="${!empty id}">
	<nav><br><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></nav>
</c:if>

<section style="margin-left:250px">
<h2><strong>내 보관함</strong></h2>
	<c:if test="${empty msg}">
	<br>
	<h4>식당</h4>
	<c:choose>
		<c:when test="${empty pickRest}">보관함에 담은 식당이 없습니다.</c:when>
		<c:otherwise>
			<table border="1">
			<tr>
				<th>No.</th>
				<th>가게명</th>
				<th>위치</th>
				<th>메뉴</th>
				<th>선택</th>
			</tr>
			<c:forEach var="pick" items="${pickRest}" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td><a href="<c:url value="/store/StoreView/${pick.storeNum}" />">${pick.name}</a></td>
					<td>${pick.citycode} ${pick.districtcode} ${pick.address}</td>
					<td>${pick.menu}</td>
					<td><input type="button" class="button" value="삭제" onclick="location.href='/sumbab/pick/storage/${pick.storeNum}'"></td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
	<br>
	<h4>카페</h4>
	<c:choose>
		<c:when test="${empty pickCafe}">보관함에 담은 카페가 없습니다.</c:when>
		<c:otherwise>
			<table border="1">
			<tr>
				<th>No.</th>
				<th>가게명</th>
				<th>위치</th>
				<th>메뉴</th>
				<th>선택</th>
			</tr>
			<c:forEach var="pick" items="${pickCafe}" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td><a href="<c:url value="/store/StoreView/${pick.storeNum}" />">${pick.name}</a></td>
					<td>${pick.citycode} ${pick.districtcode} ${pick.address}</td>
					<td>${pick.menu}</td>
					<td><input type="button" class="button" value="삭제" onclick="location.href='/sumbab/pick/storage/${pick.storeNum}'"></td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</c:if>

<c:if test="${!empty msg}">${msg}</c:if>
</section>
</body>
</html>