<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숨밥 회원 탈퇴</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
<script>
function Mypage_check(){ 
    var id = '<%=session.getAttribute("id")%>';
    var classify = '<%=session.getAttribute("classify")%>';
    var pwd = '<%=session.getAttribute("pwd")%>';
    console.log("id");
    console.log("classify");
    console.log("pwd");

     if(id=="null"){ 
        alert("로그인이 필요한 서비스입니다. 회원이 아니시라면 회원가입후 저희 숨밥서비스를 이용부탁드립니다^^"); 
     }
     else if (classify == 1){
        location.replace("/sumbab/mypage/Lv1memberMyPage");
     }
     else if (classify == 2) {
    	 location.replace("/sumbab/mypage/Lv2memberMyPage");
     }
     else if (classify == 3) {
    	 location.replace("/sumbab/mypage/noticePage");
     }
}   
</script>
<br>
<h2>숨밥 서비스 탈퇴</h2>
<form action="/sumbab/deleteKakaoMember" method="post">
숨밥을 이용하며 작성한 글, 리뷰 등 모든 정보가 일괄 삭제됩니다. <br>
카카오 계정과 동일한 email을 입력하시면 탈퇴가 완료됩니다.<br><br>
<input type="text" style="width:500px;" name="email" id="email" placeholder="이메일을 입력해 주세요."><b>${msg}</b><br><br>
<input type="submit" value="탈퇴하기" class="button"> <input type="button" value="취소" class="btn btn-danger" onclick="Mypage_check();">
</form>
</body>
</html>