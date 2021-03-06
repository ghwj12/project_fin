<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>가게 등록하기-2단계 : ${storeDTO.name}</title>
</head>

<body>
<jsp:include page="/WEB-INF/views/header.jsp" flush="true"/>
	<h2>카테고리 설정</h2>
	<div>숨밥 카테고리 기반 추천에 가게를 추천하려면, 아래 항목을 선택해주세요.<br>
		선택하지 않을 경우, 카테고리 추천에 나타나지 않을 수 있습니다.<br><br>
	</div>
	
	<form:form action="completeRegisterC" method="post" commandName="cafeCatData">
	<!-- value는 1,0으로 모두 값이 들어감 -->
		<div>
			<h5>어떤 상황일 때 가면 좋은가요? (중복 선택 가능)</h5>
		</div>
		<input type="checkbox" id="situation1" name="situation1" >데이트 할 때<br>
		<input type="checkbox" id="situation2" name="situation2" >업무회의 할 때<br>
		<input type="checkbox" id="situation3" name="situation3" >공부할 때<br>
<br>
		<div>
			<h5>24시간 운영하는 곳인가요?(코로나 2단계 미만 정상 영업 시))</h5>
		</div>
		<input type="checkbox" id="timeslot" name="timeslot">24시간 운영
		<br>
<br>
		<div>
			<h5>어떤 분위기의 카페인가요? (중복 선택 가능)</h5>
		</div>
		<input type="checkbox" id="mood1" name="mood1" >아기자기<br>
		<input type="checkbox" id="mood2" name="mood2" >시크/모던<br>
		<input type="checkbox" id="mood3" name="mood3" >포토존<br>
		<br>
		<div>
			<h5>공간은 어떻게 구성되어 있나요? (중복 선택 가능)</h5>
		</div>
		<input type="checkbox" id="size1" name="size1" >단층<br>
		<input type="checkbox" id="size2" name="size2" >2층 이상<br>
		<input type="checkbox" id="size3" name="size3" >루프탑<br>
		<br>
		<input type="submit" class="button" value="등록하기">
	</form:form>
</body>
</html>