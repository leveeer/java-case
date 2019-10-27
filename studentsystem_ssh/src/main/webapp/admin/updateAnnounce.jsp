<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/20
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
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
    <style type="text/css">
        .a{
            background:url(/images/bg.gif);
        }
    </style>


    <script type="text/javascript">
        function closeOpen()
        {
            window.returnValue=false;
            window.close();
        }

        window.onload = function(){

            //1.给表单绑定onsubmit事件
            document.getElementById("form").onsubmit = function(){
                //调用校验方法
                return checkHead() && checkText();
            };

            //绑定离焦事件
            document.getElementById("head").onblur = checkHead;
            document.getElementById("text").onblur = checkText;
        };

        function checkHead() {
            var head = document.getElementById("head").value;
            var head_tip = document.getElementById("head_tip");
            if (head == ""){
                head_tip.innerHTML="请输入标题";
                return false;
            }else {
                head_tip.innerHTML="";
                return true;
            }
        }

        function checkText() {
            var text = document.getElementById("text").value;
            var text_tip = document.getElementById("text_tip");
            if (text == ""){
                text_tip.innerHTML="请输入正文";
                return false;
            }else {
                text_tip.innerHTML="";
                return true;
            }
        }
    </script>
</head>

<body >
<div class="a">
    <form id="form" action="<%=path %>/admin.do?command=updateAnnounce" name="formAdd" method="post">
        <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
            <tr bgcolor="#EEF4EA">
                <td colspan="3" background="<%=path %>/images/wbg.gif" class='title' align='center'><span>通知修改</span></td>
            </tr>
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td width="25%" bgcolor="#FFFFFF" align="right">
                    id：
                </td>
                <td width="75%" bgcolor="#FFFFFF" align="left">
                    <input id="id" type="text" name="id" size="30" value="${announceUpdate.id}" readonly/>
                </td>
            </tr>
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td width="25%" bgcolor="#FFFFFF" align="right">
                    标题名：
                </td>
                <td width="75%" bgcolor="#FFFFFF" align="left">
                    <input id="head" type="text" name="head" value="${announceUpdate.head}" size="30"/><span style="color: red" id="head_tip"></span>
                </td>
            </tr>
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td width="25%" bgcolor="#FFFFFF" align="right">
                    正文内容：
                </td>
                <td width="75%" bgcolor="#FFFFFF" align="left">
                    <textarea id="text" rows="12" cols="80" name="text">${announceUpdate.text}</textarea><span style="color: red" id="text_tip"></span>
                </td>
            </tr>

            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td width="25%" bgcolor="#FFFFFF" align="right">
                    &nbsp;
                </td>
                <td width="75%" bgcolor="#FFFFFF" align="left">
                    <input type="submit" value="提交"/>&nbsp;
                    <input type="reset" value="重置"/>&nbsp;
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

