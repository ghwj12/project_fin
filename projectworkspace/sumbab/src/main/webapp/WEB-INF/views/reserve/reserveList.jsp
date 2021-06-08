<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약한 음식점</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<nav><br><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></nav>
<section style="margin-left:250px">
	<h2><strong>예약 내역 보기</strong></h2>
	<br>
	<h4>예약 내역</h4>
		<c:choose>
			<c:when test="${fn:length(listFuture) == 0}">
				예약 내역이 없습니다.<br>
			</c:when>
			<c:otherwise>
		<c:forEach var="reserveF" items="${listFuture}" varStatus="loop">
		<table class="type05">
			<tr>
				<th>가게명</th>
				<td style="min-width:300px;">
					<c:forEach var="store" items="${storeName}"	varStatus="status">
						<c:if test="${store.storeNum eq reserveF.storeNum}">
							<c:out value=" ${store.name}" />
						<input type="hidden" id="reserveSeq" name="reserveSeq" value="${reserveF.reserveSeq}">
						<input type="button" class="button" value="취소" onclick="popupForCancel(${reserveF.reserveSeq});">
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>예약자 이름</th>
				<td>${reserveF.reserveName}</td>
			</tr>
			<tr>
				<th>예약 날짜</th>
				<td>${reserveF.resDate}</td>
			</tr>
			<tr>
				<th>예약자 연락처</th>
				<td>${reserveF.phone}</td>
		</table>
		</c:forEach>
			</c:otherwise>
		</c:choose>
	
	<br>
	<h4>과거 예약 내역</h4>
		<c:choose>
			<c:when test="${fn:length(listPast) == 0}">
				지난 예약 내역이 없습니다.<br>
			</c:when>
			<c:otherwise>
	<c:forEach var="reserveP" items="${listPast}" varStatus="status">
		<table class="type05">
			<tr>
				<th>가게명</th>
				<td style="width:300px;">
					<c:forEach var="store" items="${storeName}"	varStatus="status">
						<c:if test="${store.storeNum eq reserveP.storeNum}">
							<c:out value=" ${store.name}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>예약자 이름</th>
				<td>${reserveP.reserveName}</td>
			</tr>
			<tr>
				<th>예약 날짜</th>
				<td>${reserveP.resDate}</td>
			</tr>
			<tr>
				<th>예약자 연락처</th>
				<td>${reserveP.phone}</td>
			</tr>
		</table>
	</c:forEach>
			</c:otherwise>
		</c:choose>
</section>
<script src="<%=request.getContextPath()%>/resources/js/popup.js"> </script>

</body>
</html>