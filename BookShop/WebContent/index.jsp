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
		<form method="post" name="shoping" action="${pageContext.request.contextPath}/addCart.do " onsubmit="return check()">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach items="${products}" var="product" varStatus="status">
				<tr <c:if test="${status.index%2==1}">class="odd"</c:if>>
               <td><input type="checkbox" name="bookId" value="${product.pid }" /></td>
					<td class="title">${product.pname}</td>
					<td>${product.price}</td>
					<td>${product.pnum}</td>
					<td class="thumb"><img src="images/${product.imgurl}" /></td>
            </tr>
				</c:forEach>
			</table>
			<!-- <div class="page-spliter">
				<a href="#">&lt;</a>
				<a href="#">首页</a>
				<span class="current">1</span>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<span>...</span>
				<a href="#">尾页</a>
				<a href="#">&gt;</a>
			</div> -->
			<div  style="width:98%; text-align: center;padding:10px 0 0 0 ;">
            <%=request.getAttribute("bar")%>
           </div> 
			<div class="button"><input class="input-btn" type="submit" name="submit" value="" /></div>
		</form>
	</div>
</div>
<!-- 引入jquery -->
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
/* function check(){
	 /* if($("input[type='checkbox']").is(':checked')){
		   return true;
	   }else{
		   alert("没有被选中的商品,请选一个。");
		   return false;
	   } */
	   
function check(){
var pro=document.getElementsByName("bookId");
for(var i=0;i<pro.length;i++){
if(pro[i].checked==true){
return true;
}

}
alert("没有被选中的商品,请选一个。");
return false;
		
}
</script>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有

</div>
</body>
</html>
    