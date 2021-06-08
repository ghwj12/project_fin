<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원탈퇴</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		// 취소
		$("#cancel").on("click", function() {
			location.href = "/sumbab/mypage/memberMyPage";
		})

		$("#submit").on("click", function() {
			if ($("#pwd").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#pwd").focus();
				location.href = "/sumbab/member/memberDeleteView";
				return false;
			}
			$.ajax({
				url : "/sumbab/memberDelete2",
				type : "POST",
				dataType : "json",
				data : $("#delForm").serializeArray(),
				success : function(data) {

					if (data == 0) {
						alert("패스워드가 틀렸습니다.");
						location.href = "/sumbab/member/memberDeleteView";
					}
					if (data == 1) {
						confirm("회원탈퇴하시겠습니까?");
						$("#delForm").submit();
					}
				}
			})
		});
	})
</script>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true" />
<nav><br>
	<c:choose>
	<c:when test="${classify == 2}"><jsp:include page="../mypage/mypageMenu2.jsp" flush="false"/></c:when>
	<c:otherwise><jsp:include page="../mypage/mypageMenu1.jsp" flush="false"/></c:otherwise>
	</c:choose>
</nav>
<section style="margin-left:250px">
	<h2>숨밥 회원 탈퇴</h2>
숨밥 회원 탈퇴를 하시면 그동안 이용하셨던 모든 정보가 삭제됩니다.<br>
탈퇴 진행을 위하여 아이디와 비밀번호를 정확하게 입력해 주세요.<br><br>

	<section id="container">
		<form action="/sumbab/memberDelete" method="post" id="delForm">
			<div class="form-group has-feedback">
				<label class="control-label" for="id">아이디</label>
				<input class="form-control" type="text" id="id" name="id" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="pwd">패스워드</label>
				<input class="form-control" type="password" id="pwd" name="pwd" />
			</div>
			
			<div class="form-group has-feedback">
				<button class="button" type="submit" id="submit">회원탈퇴</button>
				<button class="button" id="cancel" type="button">취소</button>
			</div>
		</form>
		<div>
			<c:if test="${msg == false}">
					비밀번호가 맞지 않습니다.
				</c:if>
		</div>
	</section>
</section>
</body>

</html>
