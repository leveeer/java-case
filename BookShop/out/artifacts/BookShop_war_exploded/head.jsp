<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header" class="wrap">
	<div id="logo"> 
	   成人教育网上书城
	</div>
    <div style="width:380px; height:50px; float:right; position:absolute; left:60%; top:2px; line-height:50px">
	    <img src="images/cart.gif" style="margin-bottom:-4px" width="26" height="23">
		  欢迎您：${user.username}
                  <a href="myAccount.jsp">我的帐户</a>
				| <a href="productCategory.jsp">商品分类</a> 
				| <a href="register.jsp">新用户注册</a>  
		</div>
	<div id="navbar">
		<div class="userMenu">
			<ul>
				<li class="current"><a href="index?pageIndex=1">User首页</a></li>
				<li><a href="orderlist.do">我的订单</a></li>
				<li><a href="shopping.jsp">购物车</a></li>
				<li><a href="logout.action" onclick="javascript:return confirm_logout()">注销</a></li>
			</ul>		
		</div>

		<form method="post" name="search" action="simpleSearch.do">
			搜索：<input class="input-text" type="text" value="请输入书名" name="keywords"  onblur="if(this.value==''){this.value='请输入书名'}" 
			onfocus="if(this.value=='请输入书名'){this.value=''}"/>
			<input class="input-btn" type="submit" name="submit" value="" />
		</form>

	</div>
</div>
	<script type="text/javascript">
function confirm_logout(){
	if(confirm("确定要注销吗？")){
		return true;
	}else{
		return false;
	}
}
</script>
			