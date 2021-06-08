<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>리뷰 삭제 팝업</title>
</head>
<body>
<form:form method="POST">
작성한 리뷰를 삭제하시겠습니까?<br><br>
<input type="submit" value="리뷰 삭제" class="button">
<input type="button" value="아니오" class="button" onclick="javascript:self.close()">
</form:form>
</body>
</html>