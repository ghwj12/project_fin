<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 삭제</title>
<link rel="stylesheet" href="/sumbab/resources/css/button.css">
</head>
<body>
	<form:form method="POST">
	해당 신고를 삭제 하시겠습니까?<br><br>
	<input type="submit" class="button" value="삭제">
	<input type="button" class="button" value="취소" onclick="javascript:self.close()">
	</form:form>
</body>
</html>