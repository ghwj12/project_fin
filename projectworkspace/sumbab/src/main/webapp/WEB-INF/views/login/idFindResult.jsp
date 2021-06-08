<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/sumbab/resources/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>

</head>
<body>
	<form name="iFr" action="/sumbab/login/login">
		<table class="table table-dark">
			<tr>
				<th>아이디 :</th>
				<td>${id}</td>
			</tr>
		</table>
		<input type="button" value="로그인" class="button" onclick="location.href='javascript:popupSubmit();self.close()'">
		<input type="button" value="비밀번호 찾기" class="button" onclick="location.href='/sumbab/login/findMemberPwd'">
	</form>
	<script type="text/javascript">
		function popupSubmit() {
			window.opener.name = "parentPage"; // 부모창의 이름 설정
			document.iFr.target = "parentPage"; // 타켓을 부모창으로 설정
			document.iFr.action = "/sumbab/login/login"; //부모창에 호출될 url 
			document.iFr.submit();
			}
	</script>
</body>
</html>