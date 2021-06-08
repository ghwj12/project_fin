<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html> 
<html>
<head> 
<title>SUMBAB 회원가입</title> 
</head> 
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
	
 <article class="container"> 
<h2>카카오 연동 회원가입</h2>
카카오로 가입하시고, 클릭 한번으로 간편하게 로그인 하세요!
 <form:form action="/sumbab/member/memberjoin_kakao" method="POST" commandName="memberDTO"> 
 <table border="1">
 	<tbody>
			<tr>
				<th>회원분류</th>
				<td>
				<select name="classify" required> 
				<option value="">분류</option>
				<option value="1">일반회원</option>
				<option value="2">가게관리회원</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td>
				${email}
				<input type="hidden" name="email" id="email" value="${email}">
				<input type="hidden" name="id" id="id" value="">
				<input type="hidden" name="pwd" id="pwd" value="">
				<input type="hidden" name="kakao_id" id="kakao_id" value="${kakao_id}">
				</td>
			</tr>
			</tbody>
		</table>
		<input type="submit" class="button" value="가입하기">
		</form:form>
	</article> 
</body>
</html>
