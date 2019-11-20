<%@ page language="java" pageEncoding="UTF-8"%>
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
        <style type="text/css">
			body {
			 background:url(/images/bg.gif);
			}
		</style>
        <script type="text/javascript">
            function closeOpen()
		    {
		       window.returnValue=false;
		       window.close();
		    }
		    
			window.onload = function () {
                document.getElementById("form").onsubmit = function () {
                    return checkName() && checkPassword() && checkEmail();
                };

                document.getElementById("name").onblur = checkName;
                document.getElementById("password").onblur = checkPassword;
                document.getElementById("email").onblur = checkEmail;
            };

            function checkName() {
                var name = document.getElementById("name").value;
                var name_tip = document.getElementById("name_tip");
                if (name == ""){
                    name_tip.innerHTML="姓名不能为空";
                    return false;
                } else {
                    name_tip.innerHTML="";
                    return true;
                }
            }

            //验证密码是否合理
            function checkPassword() {
                var password = document.getElementById("password").value;
                var password_tip = document.getElementById("password_tip");
                if (password.length < 6 || password.length > 16) {
                    password_tip.innerHTML = "密码长度不符合要求";
                    return false;
                } else {
                    password_tip.innerHTML = "";
                    return true;
                }
            }

            //验证邮箱 @.
            function checkEmail() {
                var email = document.getElementById("email").value;
                var email_tip = document.getElementById("email_tip");
                if (email.indexOf("@") == -1) {
                    email_tip.innerHTML = "邮箱必须包含@";
                    return false;
                } else if (email.indexOf(".") <= email.indexOf("@")) {
                    email_tip.innerHTML = "邮箱必须包含@.";
                    return false;
                } else {
                    email_tip.innerHTML = "";
                    return true;
                }

            }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'> 
			<form id="form" action="<%=path %>/adminServlet/addStudent" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title' align='center'><span>学生信息添加</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         用户名：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input id="name" type="text" name="name" size="22"/><span id="name_tip" style="color: red"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        密码：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="password" name="password" id="password" size="22"/><span style="color: red" id="password_tip"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        性别：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
                                <input name="sex" type="radio" value="男" checked/>男
                                <input name="sex" type="radio" value="女"/>女
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						       电子邮箱：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="email" id="email" size="22"/><span id="email_tip" style="color: red"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="submit" value="提交"/>&nbsp;
						       <input type="reset" value="重置"/>&nbsp;
						       <input type="button" value="取消" onclick="closeOpen()"/>
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
