<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 작성한 리뷰</title>
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
<h2><strong>내가 작성한 리뷰</strong></h2>
컨텐츠를 클릭하시면 수정/삭제 하실 수 있습니다.<br>
<c:choose>
	<c:when test="${fn:length(reviewList) == 0}">
			아직 작성한 리뷰가 없습니다.
	</c:when>
	<c:otherwise>
		<table class="type05">
			<tr>
				<th class="No">No.</th><th>가게 이름</th><th>별점</th><th>컨텐츠</th><th class="date">작성일</th>
			</tr>
			<c:forEach var="reviewList" items="${reviewList}" varStatus="index">
			<tr>
				<td>${reviewList.reviewNum}</td>
				<td>${reviewList.name}</td>
				<td>${reviewList.star}</td>
				<td>
				<c:choose>
					<c:when test="${reviewList.content.length()>20}">
						<a href="/sumbab/review/reviewDetail/${reviewList.reviewNum}" onclick="getStoreNum();">
						${reviewList.content.substring(0,20)}...</a>
					</c:when>
					<c:when test="${reviewList.content.length()<20}">
					<a href="/sumbab/review/reviewDetail/${reviewList.reviewNum}">
						${reviewList.content}</a>
					</c:when>
				</c:choose>
				</td>
				<td>${reviewList.regdate.substring(0,10)}</td>
			</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</section>
</body>
</html>