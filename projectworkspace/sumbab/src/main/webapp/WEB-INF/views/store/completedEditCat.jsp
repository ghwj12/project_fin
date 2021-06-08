<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 수정 완료</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<nav><br><jsp:include page="/WEB-INF/views/mypage/mypageMenu1.jsp" flush="false"/></nav>
<section style="margin-left:250px">

등록한 가게의 카테고리가 변경되었습니다.<br><br>

<input type="button" class="button" value="가게 리스트로 돌아가기" onclick="location.href='/sumbab/store/myStoreList'">
<input type="button" class="button" value="마이 페이지로" onclick="Mypage_check();">
</section>
</body>
</html>