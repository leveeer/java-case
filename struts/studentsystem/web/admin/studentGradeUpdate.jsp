<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/21
  Time: 08:54
  To change this template use File | Settings | File Templates.
--%>
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

    <script language="javascript">
        function closeOpen()
        {
            window.returnValue=false;
            window.close();
        }

        window.onload = function () {
            document.getElementById("form").onsubmit = function () {
                return checkGrade();
            };

            document.getElementById("grade").onblur = checkGrade;
        };

        function checkGrade(){
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
<form id="form" action="<%=path %>/adminServlet/updateStudentGrade?class_id=${updateGrade.class_id}" name="formAdd" method="post">
    <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
        <tr bgcolor="#EEF4EA">
            <td colspan="3" background="<%=path %>/images/wbg.gif" class='title' align='center'><span>学生成绩修改</span></td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                学生id：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="stu_id" size="20" value="${updateGrade.stu_id}"  readonly="true">
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                学生名：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="name" size="20" value="${updateGrade.name}"  readonly="true">
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                课程：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="class_name" size="20" value="${updateGrade.class_name}"  readonly="true">
            </td>
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                成绩：
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="text" name="grade" id="grade" size="20" value="${updateGrade.grade}"/><span id="grade_tip" style="color: red"></span>
            </td>
        </tr>
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
            <td width="25%" bgcolor="#FFFFFF" align="right">
                &nbsp;
            </td>
            <td width="75%" bgcolor="#FFFFFF" align="left">
                <input type="submit" value="提交">&nbsp;
                <input type="reset" value="重置"/>&nbsp;
                <input type="button" value="取消" onclick="closeOpen()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

