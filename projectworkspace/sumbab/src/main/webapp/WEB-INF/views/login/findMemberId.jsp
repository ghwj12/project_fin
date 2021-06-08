<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>아이디 찾기 폼</title>
</head>
<body>
	<form:form commandName="findId" action="/sumbab/login/idFindResult" method="post">
	<h2>숨밥 아이디 찾기</h2>
		<b>가입하실 때 등록한 이메일을 입력하세요.</b><br>
			<input type="text" name="email" id="email" required>
			<input type="submit" value="아이디 찾기" class="button">
	</form:form>
	
</body>
</html>