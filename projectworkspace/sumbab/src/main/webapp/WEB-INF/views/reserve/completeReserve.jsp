<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 완료</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

예약이 완료되었습니다.<br>
예약 내역은 마이페이지-예약 내역에서 확인하실 수 있습니다.<br><br>

<!-- 마이페이지 controller에 requestMapping 할 것 -->
<input type="button" value="예약내역 보기" class="button" onclick="location.href='/sumbab/reserve/reserveList'"> 
<input type="button" value="가게 페이지로 " class="button" onclick="location.href='/sumbab/store/StoreView/${storeNum}'">

</body>
</html>