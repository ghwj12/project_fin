<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
</head>
<body style="text-align:center;">
	  <jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<br>
<h2>숨밥꼭질 로그인</h2>
	<form:form commandName="memberVo" method="POST" >
	<table class="table table-dark" style="max-width:500px; margin:auto;">
		<tr>
			<th><form:label path="id">아이디</form:label></th>
			<td><form:input path="id" required="required"/></td>
		</tr>
		<tr>
			<th><form:label path="pwd">비밀번호</form:label></th>
			<td><form:password path="pwd" required="required"/></td>
		</tr>
		<tr>
		<td colspan="2">
		<input type="submit" class="button login" value="숨밥 로그인" formaction="/sumbab/login/login" >
		</td>
		</tr>
	</table>
	</form:form>
	
	<a href="javascript:openFindId();">아이디 찾기</a> | <a href="javascript:openFindPwd();">비밀번호 찾기</a>
	<br><br>
	
	<h2>아직 숨밥 회원이 아니신가요?</h2>
	숨밥 회원 가입 하시고 다양한 혜택, 알림 서비스를 받아보세요.<br>
	카카오 계정이 있다면 더 쉽고 간편하게 회원가입/로그인 해보세요!<br> 
	<input type="button" class="button" value = "일반 회원가입" style="width:203px; padding:10px;"
	onclick="location.href='/sumbab/member/memberjoin'"/>
		<div>
		<!-- 카카오 이미지에 로그인/회원가입 문구를 넣고 싶었으나, api 디자인 가이드 정책으로 인하여 정해진 문구만 사용 가능합니다! -->
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=a3fbebb55cfa7bc59ad6f80ba997dfbe&scope=account_email&redirect_uri=http://localhost:8090/sumbab/kakaoLogin&response_type=code">
			<img src="/sumbab/resources/img/kakao_login_large_narrow.png"
			style="height:50px;width:auto"></a>
		</div>
	
	<script type="text/javascript">
		function openFindId(){
			var popWidth = 700;
			var popHeight = 500;
			var maxWidth = window.screen.width
			var maxHeight = window.screen.height
			var popX = (maxWidth - popWidth)/2;
			var popY = (maxHeight - popHeight)/2;
			var url="/sumbab/login/findMemberId";
			var openWin = window.open(url,'', 'status=no, height=' + popHeight  + ', width=' + popWidth  + ', left='+ popX + ', top='+ popY);	
		}
	</script>
	
	<script type="text/javascript">
		function openFindPwd(){
			var popWidth = 700;
			var popHeight = 500;
			var maxWidth = window.screen.width
			var maxHeight = window.screen.height
			var popX = (maxWidth - popWidth)/2;
			var popY = (maxHeight - popHeight)/2;
			var url="/sumbab/login/findMemberPwd";
			var openWin = window.open(url,'', 'status=no, height=' + popHeight  + ', width=' + popWidth  + ', left='+ popX + ', top='+ popY);	
		}
	</script>
	
</body>
</html>