<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${input} 검색결과</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
	<%--keyword검색, hashtag 검색 모두 여기로 출력된다. --%>
	<c:choose>
		<c:when test="${input != null}">
		<h1>"${input}" 검색 결과:</h1>
		</c:when>
		<c:when test="${tagName != null}">
		<h1>"${tagName}" 태그가 포함된 가게는?</h1>
		</c:when>
	</c:choose>
	
	<c:set var="i" value="0" />
	<c:set var="j" value="4" />
	<table class="table table-dark" style="margin:auto; text-align:center;">

		<c:forEach var="storeVO" items="${storeList}" varStatus="loop">
		<c:if test="${i%j == 0 }">
		<tr>
			</c:if>
			<td>
				<c:choose>
				<c:when test="${storeVO.picture !=null }">
					<img src="<c:url value='/upload_01store/${storeVO.picture}'/>" width="200px"/><br>
				</c:when>
				<c:otherwise>
				</c:otherwise>
				</c:choose>
			<a href="<c:url value="/store/StoreView/${storeVO.storeNum}"/>">
			<h3>${storeVO.name}</h3></a>
			${storeVO.citycode} ${storeVO.districtcode}<br>
			</td>
			<c:if test="${i%j==j-1}">
		</tr>
		</c:if>
		<c:set var="i" value="${i+1}"/>
		</c:forEach>
	</table>
</body>
</html>