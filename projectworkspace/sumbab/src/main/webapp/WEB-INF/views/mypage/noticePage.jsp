<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 사항</title>
<link rel="stylesheet" href="/sumbab/resources/css/button.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<br>
<c:if test="${classify == 3}">
	<nav><jsp:include page="mypageMenu3.jsp" flush="false"/></nav>
</c:if>

<section style="margin-left:250px">
	<h2><strong>공지 사항</strong></h2>
	<table class="table table-dark">
		<tr>
			<th class="No">No.</th>
			<th>공지 제목</th>
			<th class="date">날짜</th>
		</tr>
		<c:forEach var="notice" items="${noticeList}" varStatus="loop">
			<tr>
				<td>${loop.count}</td>
				<td><a href="<c:url value="/mypage/noticeDetail/${notice.noticeNum}" />">${notice.title}</a></td>
				<td><fmt:formatDate value="${notice.regdate}" pattern="yyyy.MM.dd" /></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${classify == 3}">
		<input type="button" class="button" value="공지 올리기" onclick="location.href='writeNotice'"/>
	</c:if>
</section>
</body>
</html>