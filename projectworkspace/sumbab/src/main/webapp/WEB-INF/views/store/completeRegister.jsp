<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 등록 완료</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<br>
<b>"${storeDTO.name}"</b>의 등록이 완료되었습니다!<br>
등록한 가게 정보는 마이페이지-등록한 가게 에서 보실 수 있습니다.
<br><br>
		<input type="button" class="button" value="다른 가게 추가하기" onclick="location.href='/sumbab/store/registerStep1'"/>&emsp;
		<input type="button" class="button" value="마이페이지로" onclick="Mypage_check();"/>
</body>
</html>