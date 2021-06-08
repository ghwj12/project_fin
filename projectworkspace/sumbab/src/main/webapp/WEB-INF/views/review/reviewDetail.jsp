<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${Review.reviewNum}번 리뷰 상세보기</title>
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

<nav><br>
	<c:choose>
	<c:when test="${classify == 2}"><jsp:include page="../mypage/mypageMenu2.jsp" flush="false"/></c:when>
	<c:otherwise><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></c:otherwise>
	</c:choose>
</nav>

<section style="margin-left:250px">
<h2>리뷰 상세보기</h2>

<form>
<table class="type05">
	<tr>
		<th>가게명</th>
		<td>${Review.name}
		</td>
		<td rowspan="7">
			<c:choose>
			<c:when test="${Review.picture != null }">
				<img src="<c:url value="/upload_02review/${Review.picture}"/>" width="250px"/>
			</c:when>
			<c:otherwise>
				리뷰 작성 시, 등록한 사진이 없습니다.
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${Review.regdate}</td>
	</tr>
	<tr>
		<th>먹은 메뉴</th>
		<td>${Review.menu}</td>
	</tr>
	<tr>
		<th>별점</th>
		<td>${Review.star}</td>
	</tr>
	<tr>
		<th>리뷰 내용</th>
		<td>${Review.content}</td>
	</tr>
	<tr>
		<th>태그</th>
		<td>
		<c:forEach var="tags" items="${tags}">
		#${tags.tagName} 
		</c:forEach>
		</td>
	</tr>
	<tr>
		<th>재방문 유무</th>
		<td>${Review.revisit}</td>
	</tr>
</table>
<br>
	<input type="button" class="button" value="리뷰 수정" onclick="location.href='/sumbab/review/editReview/${Review.reviewNum}'">
	<input type="button" class="button" value="리뷰 삭제" onclick="popupForDelReview(${Review.reviewNum});">
</form>
</section>
<script src="<%=request.getContextPath()%>/resources/js/popup.js"> </script>
</body>
</html>