<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

#sidebar-wrapper {
    position: fixed;
    width: 200px;
    height: 85%;
    background: #ff9478;
    overflow-x: hidden;
    overflow-y: auto;
  }
  
.sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #fff;
  }
  
  .sidebar-nav li a:hover {
    color: #000;
    background: rgba(255, 255, 255, 0.2);
  }
  
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }
</style>
</head>
<body>
     <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <li class="sidebar-brand">마이페이지</li>
      <li><a href="/sumbab/mypage/memberMyPage">회원 정보</a></li>
      <li><a href="/sumbab/store/myStoreList">등록한 가게</a></li>
      <li><a href="/sumbab/review/myReviewList">내가 작성한 리뷰</a></li>
    </ul>
  </div>
</body>
</html>