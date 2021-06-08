<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="3; url=/sumbab/review/myReviewList">
<title>리뷰 수정 완료</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<nav><br>
	<c:choose>
	<c:when test="${classify == 2}"><jsp:include page="../mypage/mypageMenu2.jsp" flush="false"/></c:when>
	<c:otherwise><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></c:otherwise>
	</c:choose>
</nav>

<section style="margin-left:250px">
리뷰가 수정되었습니다!<br>
3초 후 자동으로 내가 작성한 리뷰로 이동합니다.
</section>
</body>
</html>