<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>회원 정보 수정</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>

<nav><br>
	<c:choose>
	<c:when test="${classify == 2}"><jsp:include page="../mypage/mypageMenu2.jsp" flush="false"/></c:when>
	<c:otherwise><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></c:otherwise>
	</c:choose>
</nav>

<section style="margin-left:250px">
   <h2>회원 정보 수정</h2>
	<form:form commandName="memberVo" method="POST" id="submitMod">

		아이디 : ${sessionScope.id}<br>
		<form:hidden path="id" value="${sessionScope.id}" />
	
		이메일 : ${sessionScope.email}<input type="button" class="button" id="changeEmail" value="이메일 변경" onclick="able('modEmail')"><br>
		<fieldset id="modEmail" style="margin-left:50px" disabled><form:input path='email' placeholder='변경할 이메일 입력' /></fieldset>
	
		비밀번호 : <input type="password" id="pwd" name="pwd" placeholder = "현재 비밀번호 입력" required/>
		<input type="button" class="button" value="비밀번호 변경" onclick="able('modPwd')">
		<fieldset id="modPwd" style="margin-left:76px" disabled><form:password path='newPwd' placeholder='새 비밀번호 입력' /></fieldset><br>
	
		<input type="button" class="button" id="modify" value="정보 수정" />

	</form:form>
</section>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>	<!-- src 사용하면 script 태그 따로 사용해야 됨 -->
<script>
	function able(id){
		document.getElementById(id).disabled = false;
	}
	
	$("#modify").on('click', function(){
		if($('#pwd').val() == ''){
			alert("현재 비밀번호를 입력해 주세요.");
		} else if(document.getElementById('modEmail').disabled == false && $('#email').val() == ''){
			alert("변경할 이메일을 입력해 주세요.");
		} else if(document.getElementById('modPwd').disabled == false && $('#newPwd').val() == ''){
			alert("새 비밀번호를 입력해 주세요.");
		} else if($('#pwd').val() != '${sessionScope.pwd}'){
			alert("현재 비밀번호가 일치하지 않습니다.");
		}else{
			alert("회원 정보가 수정되었습니다.");
			document.getElementById('submitMod').submit();
		}
	})
</script>

</body>
</html>