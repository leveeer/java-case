<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
        
        <script language="javascript">
 
			function check1(){
				if( document.getElementById("name").value==""){
					alert("请输入用户名");
					return false;
				}
					document.formUpdate.action = "${pageContext.request.contextPath}/studentServlet/update"
			}
        </script>
        <style type="text/css">

	</style>
	</head>

	<body leftmargin="2" topmargin="9" >
			<form action="" name="formUpdate" method="get">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" class='title' align='center'><span>用户修改</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        请输入用户名：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
                                <input type="hidden" name="id" value="${student.id}"/>
                                <label for="name"></label><input type="text" id="name" name="name" size="20"/>
						        <input type="submit" value="提交" onClick="check1()"/>&nbsp;
						    </td>
					 </table>
			</form>
   </body>
</html>
