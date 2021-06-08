<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<form action="/sumbab/login/pwdFindResult" method="post">
	<h2>숨밥 비밀번호 찾기</h2>
		<b>가입하신 아이디를 입력하세요.</b><br>
			<input type="text" name="id" required >
			<input type="submit" value="비밀번호 찾기" class="button">
	</form>
	
</body>
</html>