<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>成人教育网上书城</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@include file="head.jsp" %>
<div id="content" class="wrap">
	<div class="list bookList">
	
		<form method="post" name="shoping" action="index" onsubmit="return check()">
			<h1 align="center" style="color:red">您搜索的商品不存在！</h1>		
			<div  style="width:98%; text-align: center;padding:10px 0 0 0 ;">
           </div> 
			<div class="button"><input type="submit" name="submit" value="返回首页" /></div>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有
</body>
</html>
    