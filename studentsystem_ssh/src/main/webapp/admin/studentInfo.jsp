 <%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
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

	<!-- 	<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />	 -->
	
        <script language="javascript">
           function studentDel(id)
           {
               if(confirm('您确定删除吗？'))
               {	
              
                   window.location.href="${pageContext.request.contextPath}/admin.do?command=studentDel&id="+id;
               }
           }
           
           function studentAdd()
           {
                 var url="${pageContext.request.contextPath}/admin/studentAdd.jsp";
				 window.location.href=url;
           }
           	function check1(){
				if( document.getElementById("name").value==""){
					alert("请输入用户名");
					return false;
				}
					document.formUpdate.submit();
			}
           
       </script>
		<style type="text/css">
		body {
			 background:url(/images/bg.gif);
			}
	</style>
        
	</head>

	<body leftmargin="2" topmargin="2" >
			<table>
			<form action="<%=path %>/studentInfo" name="formUpdate" method="post">
				<tr >
				用户查询
				</tr>
				<tr>
					 <td width="25%" bgcolor="#FFFFFF" align="right">  请输入用户名：</td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input type="text" name="userName" size="20"/>
				        <input type="submit" value="提交" onClick="check1()"/>&nbsp;
				    </td>
				</tr>
				</form>
				</table>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="6" align='center'>&nbsp;管理员维护&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="18%">ID</td>
					<td width="18%">用户名</td>
					<td width="18%">密码</td>
					<td width="18%">性别</td>
					<td width="18%">邮箱</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${student}" var="s">
				<tr align='center' bgcolor="#FFFFFF" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${s.id}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${s.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${s.password}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${s.sex}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${s.email}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="studentDel(${s.id})" >删除</a>
						<a href="<%=path %>/admin.do?command=findStudentById&id=${s.id}">修改</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<table width='98%'  border='0'>
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="studentAdd()" />
			    </td>
			  </tr>
		    </table> 
		    
	</body>
</html>
