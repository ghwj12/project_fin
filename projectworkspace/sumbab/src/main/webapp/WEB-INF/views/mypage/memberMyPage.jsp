<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="/sumbab/resources/css/button.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<nav><br>
	<c:choose>
	<c:when test="${classify == 2}"><jsp:include page="mypageMenu2.jsp" flush="false"/></c:when>
	<c:otherwise><jsp:include page="mypageMenu1.jsp" flush="false"/></c:otherwise>
	</c:choose>
</nav>

<section style="margin-left:250px">
	<h2><strong>회원 정보</strong></h2><br>
	<table class = "table table-dark" >
		<tr>
			<td>아이디 : ${member.id}</td>
		</tr>
		<tr>
			<td>이메일 : ${member.email}</td>
		</tr>
		<tr>
			<td>가입날짜 : ${member.regdate}</td>
		</tr>
		<tr>
			<td>
			<c:if test="${member.id.indexOf('kakao') == -1}">
			<input type="button" class="button" value="회원정보 수정" onclick="location.href='/sumbab/login/modify'">
			</c:if>
			<input type="button" class="button" value="회원 탈퇴" onclick="location.href='/sumbab/member/memberDeleteView'">
			</td>
		</tr>
	</table>
</section>
</body>
</html>