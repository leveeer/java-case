<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购物车</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<%@include file="head.jsp" %>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="createorder.do">
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
				<c:set var="totalPrice" value="0"/>
				<c:forEach items="${cart.cartItems}" var="cartitem">
				<tr>
					<td class="thumb">
					<img src="images/${cartitem.product.imgurl }" width="90" height="90" />
					</td>
					<td class="title">${cartitem.product.pname }</td>
					<td>
					<input class="input-text" type="text" readonly="readonly" id="pnum" name="nums" value="${cartitem.count}"  />
					</td>
					<td>￥<span>${cartitem.product.price }</span></td>
				</tr>
			   </c:forEach>
			   </c:if>
			</table>
			<div class="button" >
				<h4 style="float:right;">总价：￥<span>${cart.total}</span>元</h4>
				<input type="hidden" name="money" value="${cart.total}">
			 </div>
			 <div>
			 收货地址：<input id="receiverAddress" name="receiverAddress" type="text" value="${user.address }" 
			 style="width:350px" onkeyup="checkReceiverAddress();" />
			 &nbsp;&nbsp;&nbsp;&nbsp;
			 <span id="receiverAddressMsg"></span> <br/><br/>
			  收货人：&nbsp;<input id="receiverName" name="receiverName" type="text" value="${user.name}" 
			  style="width:150px" onkeyup="checkReceiverName();" />
			  <span id="receiverNameMsg"></span>  &nbsp;&nbsp;&nbsp;&nbsp;<br/> <br/> 
			  联系方式：<input type="text" id="receiverPhone" name="receiverPhone" value="${user.phone}" 
			  style="width:150px" onkeyup="checkReceiverPhone();" />
			<span id="receiverPhoneMsg"></span> &nbsp;&nbsp;&nbsp;&nbsp;
			 </p>
			 <hr />
			 <p style="text-align:right">
			<!--  <img src="images/gif53_0291.gif" width="128" height="32" border="0" 
			    onclick="checkOnSubmit();"/> -->
			    <input type="submit" value="提交订单"/>
			 </div>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有
</div>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
  var craFalg=false;
  var crnFalg=false;
  var crpFalg=false;
  //验证收货地址
  function checkReceiverAddress(){
	  //判断是否为空  判断形式是否正确
	  if($("#receiverAddress").val()==""){
		  alert("收货地址不能为空!");
		  return false;
	  }
	  //TODO 其他验证方式
	  craFalg = true;
  }
  //验证用户名
  function checkReceiverName(){
	  //使用正则表达式验证
	  //判断是否为空  判断形式是否正确
	  if($("#receiverName").val()==""){
		  alert("用户名不能为空!");
		  return false;
	  }
	  //TODO 其他验证方式
	  crnFalg = true;
  }
  //验证电话
  function checkReceiverPhone(){
	  if($("#receiverPhone").val()==""){
		  alert("电话不能为空!");
		  return false;
	  }
	  //TODO 其他验证方式
	  crpFalg = true;
  }
  //表单提交前的总的验证
  function checkOnSubmit(){
	  //所有验证都通过 才能提交表单
	  if(crpFalg && crnFalg && craFalg){
		  $("form").submit();
	  }/* else{
		  alert("请先完成各项信息填写!");
	  } */
  }
</script>
</body>
</html>