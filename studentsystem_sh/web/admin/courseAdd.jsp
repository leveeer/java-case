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
                    return checkClass_Name() && checkTeacher() && checkScore();
                };

                document.getElementById("class_name").onblur = checkClass_Name;
                document.getElementById("teacher").onblur = checkTeacher;
                document.getElementById("score").onblur = checkScore;
            };

            function checkClass_Name() {
                var class_name = document.getElementById("class_name").value;
                var class_name_tip = document.getElementById("class_name_tip");
                if (class_name == ""){
                    class_name_tip.innerHTML="课程名不能为空";
                    return false;
                } else {
                    class_name_tip.innerHTML="";
                    return true;
                }
            }

            function checkTeacher() {
                var teacher = document.getElementById("teacher").value;
                var teacher_tip = document.getElementById("teacher_tip");
                if (teacher == "") {
                    teacher_tip.innerHTML = "任课老师不能为空";
                    return false;
                } else {
                    teacher_tip.innerHTML = "";
                    return true;
                }
            }

            function checkScore() {
                var score = document.getElementById("score").value;
                var score_tip = document.getElementById("score_tip");
                if (score == ""){
                    score_tip.innerHTML="成绩不能为空";
                    return false;
                }
                if(score > 10){
                    score_tip.innerHTML="学分不能大于10";
                    return false;
                }
                if (score <= 0){
                    score_tip.innerHTML="学分不能小于等于0";
                    return false;
                }

                var regex = /^-?\d+(\.\d+)?$/;
                var flag = regex.test(score);
                if (!flag) {
                    score_tip.innerHTML = "学分输入格式有误";
                } else {
                    score_tip.innerHTML = "";
                }
                return flag;
            }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'> 
			<form id="form" action="<%=path %>/admin.do?command=addCourse" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title' align='center'><span>课程信息添加</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         课程名：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input id="class_name" type="text" name="class_name" size="22"/><span id="class_name_tip" style="color: red"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        任课老师：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="teacher" id="teacher" size="22"/><span style="color: red" id="teacher_tip"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						       课程学分：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="score" id="score" size="22"/><span id="score_tip" style="color: red"></span>
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
