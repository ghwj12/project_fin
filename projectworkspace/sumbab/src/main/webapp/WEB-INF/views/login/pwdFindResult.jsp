<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />

<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
 	
</head>
<body>
<h3>비밀번호 찾기 결과:</h3>
<form name="pFr" action="/sumbab/login/login">
	<table class="table table-dark">
		<tr>
			<th>비밀번호 :</th>
			<c:set var="password" value="${pwd}"/>
			<td>${fn:substring(password,0,2)}********<br>
			</td>
		</tr>
	</table>
		<input type="button" value="로그인 하기" class="button" onclick="location.href='javascript:popupSubmit();self.close()'">
	</form>
	
	<script type="text/javascript">
	function popupSubmit() {
	    window.opener.name = "parentPage"; // 부모창의 이름 설정
	    document.pFr.target = "parentPage"; // 타켓을 부모창으로 설정
	    document.pFr.action = "/sumbab/login/login";  //부모창에 호출될 url 
	    document.pFr.submit();
		}
	 </script>
</body>
</html>