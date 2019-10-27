<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@include file="head.jsp" %>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="createOrder.jsp">
			<table>
				<tr class="title">
					<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th class="price">价格</th>
				</tr>
				<c:if test="${empty cart}">
				<tr>
				  <td colspan="5"> 
				    <h3>您的购物车空空如也...</h3>
				    <h4>您可以先去 <a href="index.jsp">购物</a></h4>
				  </td>
				</tr>
				</c:if>
				<c:if test="${!empty cart}">
				<c:set var="totalPrice" value="0" />
				<c:forEach items="${cart.cartItems}" var="cartitem">
				<tr>
					<td class="thumb"> <img src="images/${cartitem.product.imgurl }" width="90" height="90" />
					</td> <td class="title">${cartitem.product.pname }</td>
					<td>
					<input type="button" value=" - " onclick="changNum('${cartitem.product.pid}',${map.key.pnum},${map.value-1})" />
					<input class="input-text" type="text" readonly="readonly" 
					     id="pnum" name="nums" value="${cartitem.count}" onblur="checkNum(${cartitem.product.pnum},this.value)" />
					<input type="button" value=" + " onclick="changNum('${cartitem.product.pid}',${cartitem.product.pnum},${map.value+1})" />
					</td>
					<td>￥<span>${cartitem.product.price }</span></td>
					<td> 
					<a href="removeCart.do?pid=${cartitem.product.pid}">删除</a> 
					</td>
				</tr>
				<c:set var="totalPrice" value="${totalPrice + map.value * map.key.price}" />
			   </c:forEach>
			   </c:if>
			   <a href="clearCart.do">清空购物车</a>
			</table>
			<div class="button">
				<h4>总价：￥<span>${cart.total }</span>元</h4>
				<input class="input-chart" type="submit" name="submit" value="" />
			</div>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有

</div>
</body>
</html>
