<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
	
        <script language="javascript">
           function adminDel(userId)
           {
               if(confirm('您确定删除吗？'))
               {	
              
                   window.location.href="/UserDel?userId="+userId;
               }
           }
           
           function adminAdd()
           {
                 var url="/jsp/adminAdd.jsp";
				 window.location.href=url;
           }
           	function check1(){
				if( document.getElementById("userName").value==""){
					alert("请输入用户名");
					return false;
				}
					document.text.submit();
			}
           
       </script>
		<style type="text/css">

	</style>
        
	</head>

	<body leftmargin="2" topmargin="2" >

			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="6" align='center'>&nbsp;${student.name}同学信息&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="18%">ID</td>
					<td width="18%">用户名</td>
					<td width="18%">密码</td> 
					<td width="18%">性别</td>
					<td width="18%">邮箱</td>
					<td width="10%">操作</td>
		        </tr>	

				<tr align='center' bgcolor="#FFFFFF" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${student.id}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${student.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${student.password}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${student.sex}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${student.email}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="${pageContext.request.contextPath}/student/studentUpdate.jsp">修改</a>
					</td>
				</tr>
			</table>
		    
	</body>
</html>
