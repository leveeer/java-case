<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索结果</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<style type="text/css">
.booklist{
	text-align: center;  
    width: 100%; 
	height:360px;
    margin: 0px;
    padding: 10px 0 10px 40px; 
}
#bookul{ 
   list-style:none;
   float:left
}
#bookul li{
	float:left;
	width: 161px;
    height: 185px;
	display:inline; margin:auto 10px;
}
.divbookpic { 
    width: 131px;
    height: 135px;
    border: 1px solid #CCCCCC;
    background-color: #FFFFFF;
    margin-bottom: 8px;
    overflow: hidden;
    display:inline;
    position: relative;
    text-align: center;
    vertical-align: middle; 
}
.divlisttitle {
    line-height: 120%;
    text-align: left; 
	display:inline;
	float:left;
	margin-left:12px;
	margin-top:3px;
}
#supperSearch{ 
width:100%; 
height:30px; 
margin:5px; 
float:left; 
padding:5px;
}
.search-btn{
	background-image:url(images/serchbutton.gif); background-repeat:round;
	width:60px; 
	height:22px; 
	border:0px;
}
</style>
</head>
<body>
<%@include file="head.jsp" %>
<div id="content" class="wrap"> 
<div>
<div id="supperSearch">
        <form method="post" name="search" action="supperSearch.do">
        <table border="0" cellspacing="0">
           <tr>
			<td >书名：</td>
			<td><input class="input-text" type="text" name="bookName" onblur="if(this.value==null){this.value=''}"/>
			<span id="bktip"> </span></td>
			<td >&nbsp;&nbsp; 价格：</td>
			<td><input class="input-text" type="text" name="price1" value="0" size="4" maxlength="4"  />
			<span> </span></td>
			<td width="30px">元&nbsp;至&nbsp;</td>
			<td><input class="input-text" type="text" name="price2" value="0" size="4" maxlength="4"  />
			<span> </span></td> 
            <td >元 &nbsp;&nbsp;类别：</td>
			<td> 
             <select name="category" value="请选择">
               <option value="全部">--全部--</option>
               <option value="计算机" name="计算机">计算机</option>
               <option value="文学"  name=" 文学" >文学</option>             
               <option value="小说" name="小说">小说</option>
               <option value="生活" name="生活">生活</option>
             </select>
            <span> </span>
            </td>
			<td>&nbsp;&nbsp;</td>
			 <td class="button">
              <input class="search-btn" type="submit" name="submit" value=" " />
             </td>
            <td>
		</tr> 
        </table>
        </form>
        </div>    
              	 
        <h1>搜索结果</h1>       
        <div class="list bookList">
        <form method="post" name="shoping" action="${pageContext.request.contextPath}/addCart.do " onsubmit="return check()">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th>类别</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach items="${products0}" var="product" varStatus="status">
				<tr>
               <td><input type="checkbox" name="bookId" value="${product.pid}" /></td>
					<td class="title">${product.pname}</td>
					<td>${product.category}</td>
					<td>${product.price}</td>
					<td>${product.pnum}</td>
					<td class="thumb"><img src="images/${product.imgurl}" /></td>
            </tr>
				</c:forEach>
			</table>
        <div class="button"><input class="input-btn" type="submit" name="submit" value="" /></div>
		</form>
	</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有 
</div>
</body>
</html>
