<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 추천</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<h2>카테고리 추천 : ${category.categoryText1} - ${category.categoryText2}</h2>
	<c:forEach var="recommend" items="${recommendList}" varStatus="loop">
	<div>
		<h3>${loop.count}. <a href="<c:url value="/store/StoreView/${recommend.storeNum}"/>">${recommend.name}</a></h3>
		${recommend.citycode} ${recommend.districtcode} ${recommend.address}<br><br>
		${recommend.time}<br>
		${recommend.menu}<br>
		조회수 : ${recommend.count}
	</div>
	</c:forEach>
	
</body>
</html>