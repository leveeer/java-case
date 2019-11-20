<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@include file="head.jsp" %>
<div id="content" class="wrap">
	<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th>订单商品</th>
					<th class="userName">收货人</th>
					<th class="price">订单金额</th>
					<th class="createTime">下单时间</th>
					<th class="status">订单状态</th>
				</tr>
				<c:forEach items="${orders}" var="order">
				<c:forEach items="${order.orderItems }" var="orderItem">
					<tr>
					<td>${order.oid }</td>
					<td class="thumb"><img src="images/${orderItem.product.imgurl}" /></td>
					<td>${order.name}</td>
					<td>￥${orderItem.subTotal}</td>
					<td>${order.ordertime}</td>
					<td>已完成</td>
				</tr>
				</c:forEach>
				</c:forEach>
				<a href="">支付</a>	
			</table>
			<div class="page-spliter">
				<a href="#">&lt;</a>
				<a href="#">首页</a>
				<span class="current">1</span>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<span>...</span>
				<a href="#">尾页</a>
				<a href="#">&gt;</a>
			</div>
			<div class="button"><input class="input-gray" type="submit" name="submit" value="查看一个月前的订单" /><input class="input-gray" type="submit" name="submit" value="查看一个月前的订单" /></div>
	</div>
</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有

</div>
</body>
</html>
    