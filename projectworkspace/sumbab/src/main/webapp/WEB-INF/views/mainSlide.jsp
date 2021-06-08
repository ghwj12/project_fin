<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="margin:0;">
<section class="slide">
	<div class="carousel slide" id="carouselExampleIndicators" data-ride="carousel">
		<ol class="carousel-indicators">
			<li class="active" data-target="#carouselExampleIndicators" data-slide-to="0"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active" onclick="location.href='/sumbab/sumbabStart'">
				<img class="d-block w-100" src="https://ifh.cc/g/w0zvH2.jpg" alt="..." />
				<div class="carousel-caption d-none d-md-block">
					<h2>숨밥꼭질 시작하기</h2>
					<p>맛집 찾기에 지치셨다면, 숨밥을 시작하세요!</p>
				</div>
			</div>
			<div class="carousel-item" onclick="location.href='/sumbab/recommendPage/mergeRecommend'">
				<img class="d-block w-100" src="https://ifh.cc/g/qmhsV0.jpg" alt="..." />
				<div class="carousel-caption d-none d-md-block">
					<h2>카테고리 기반 추천</h2>
					<p>상견례, 데이트, 혼밥 등 맞춤 카테고리로 검색 해보세요</p>
				</div>
			</div>
			<div class="carousel-item" onclick="location.href='/sumbab/Gps/GPSlocation'">
				<img class="d-block w-100" src="https://ifh.cc/g/CY7Wn8.jpg" alt="..." />
				<div class="carousel-caption d-none d-md-block">
					<h2>여긴 어디, 나는 누구?</h2>
					<p>낯선 곳에서도 위치기반 추천으로 맛집 찾기 어렵지 않아요~</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		<span class="sr-only">Previous</span></a>
		<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
		</a>
	</div>
</section>

</body>
</html>