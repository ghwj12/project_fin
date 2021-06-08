<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신고 내역</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<nav><br><jsp:include page="mypageMenu3.jsp" flush="false"/></nav>
<section style="margin-left:250px">
	<h2>신고 내역</h2>
	<table class="table table-dark">
		<tr>
			<th>No.</th>
			<th>신고 사유</th>
			<th>신고자</th>
		</tr>
		<c:forEach var="report" items="${reportList}" varStatus="loop">
			<tr>
				<td>${loop.count}</td>
				<td><a href="<c:url value="/mypage/reportDetail/${report.warningNum}" />">${report.reason}</a></td>
				<td>${report.reporter}</td>
			</tr>
		</c:forEach>
	</table>
</section>
</body>
</html>