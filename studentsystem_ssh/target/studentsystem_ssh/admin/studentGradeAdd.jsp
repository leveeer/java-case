<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
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
        
        <script type="text/javascript">
            window.onload = function(){

                //1.给表单绑定onsubmit事件
                document.getElementById("form").onsubmit = function(){
                    //调用校验方法
                    return checkGrade() && checkCourse() && checkName();
                };

                //绑定离焦事件
                document.getElementById("name").onblur = checkName;
                document.getElementById("grade").onblur = checkGrade;
                document.getElementById("course").onblur = checkCourse;
            };

            function checkName() {
                var name = document.getElementById("name").value;
                var name_tip = document.getElementById("name_tip");
                if (name == "") {
                    name_tip.innerHTML="学生名不能为空";
                    return false;
                }else {
                    name_tip.innerHTML="";
                    return true;
                }
            }
            function checkCourse() {
                var course = document.getElementById("course").value;
                var course_tip = document.getElementById("course_tip");
                if(course == 0){
                    course_tip.innerHTML="请选择课程";
                    return false;
                }else {
                    course_tip.innerHTML="";
                    return true;
                }
            }
            
            function checkGrade() {
                var grade = document.getElementById("grade").value;
                var grade_tip = document.getElementById("grade_tip");
                if (grade == ""){
                    grade_tip.innerHTML="成绩不能为空";
                    return false;
                }
                if(grade > 100){
                    grade_tip.innerHTML="成绩输入错误";
                    return false;
                }
                if (grade < 0){
                    grade_tip.innerHTML="成绩输入错误";
                    return false;
                }

                var regex = /^-?\d+(\.\d+)?$/;
                var flag = regex.test(grade);
                if (!flag) {
                    grade_tip.innerHTML = "成绩输入有误";
                } else {
                    grade_tip.innerHTML = "";
                }
                return flag;
            }
        </script>
        <style type="text/css">
	body {
		 background:url(/images/bg.gif);
		}
	</style>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'> 
			<form id="form" action="<%=path %>/admin.do?command=studentGradeAdd" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title' align='center'><span>学生信息添加</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         学生名：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input id="name" type="text" name="name" size="20"/><span id="name_tip" style="color: red"></span>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        课程：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <select id="course" name="class_id">
									<option value=0 selected>请选择</option>
										<c:forEach items="${course}" var="course">
											<option value=${course.class_id} >${course.class_name}</option>
										</c:forEach>
									
								</select><span id="course_tip" style="color: red"></span>
						    </td>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						     成绩：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="grade" id="grade" size="22"/><span id="grade_tip" style="color: red"></span>
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
