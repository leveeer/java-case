<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
    <script type="text/javascript">
        window.onload = function(){

            //1.给表单绑定onsubmit事件
            document.getElementById("form").onsubmit = function(){
                    //调用密码校验方法   checkNewPwd();
                if (confirm("您确定要修改密码吗？修改之后将需要重新登录")){
                    return checkNewPwd() && checkRenewPwd();
                }

            };

            //给密码框绑定离焦事件
            document.getElementById("newPwd").onblur = checkNewPwd;
            document.getElementById("renewPwd").onblur = checkRenewPwd;
        };


        //校验密码
        function checkNewPwd(){
            //1.获取密码的值
            var newPwd = document.getElementById("newPwd").value;
            var password = document.getElementById("password").value;
            //4.提示信息
            var n_tip = document.getElementById("n_tip");
            if (newPwd == "") {
                n_tip.innerHTML = "密码不能为空";
                return false;
            }else if (password == newPwd){
                n_tip.innerHTML = "新密码与原密码一致";
                return false;
            }
            //2.定义正则表达式
            var reg_password = /^\w{6,12}$/;
            //3.判断值是否符合正则的规则
            var flag = reg_password.test(newPwd);
            if(flag){
                n_tip.innerHTML = "";
            }else{
                //提示红色密码格式有误
                n_tip.innerHTML = "密码格式有误";
            }
            return flag;
        }

        function checkRenewPwd() {
            var newPwd = document.getElementById("newPwd").value;
            var renewPwd = document.getElementById("renewPwd").value;
            var r_tip = document.getElementById("r_tip");
            if (newPwd != renewPwd){
                r_tip.innerHTML = "两次输入的密码不一致";
                return false;
            }else if (newPwd == renewPwd){
                r_tip.innerHTML = "";
                return true;
            }
        }

    </script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/adminServlet/updatePassword" id="form">
    <table border="1">
        <tr>
            <th width="20%">原密码</th>
            <td width="60%"><input type="text" name="password" id="password" value="${admin.password}" readonly ></td><span style="color: red"></span>
        </tr>
        <tr>
            <th width="20%">请输入新密码</th>
            <td width="60%"><input type="password" name="newPwd" id="newPwd"><span id="n_tip" style="color: red"></span></td>
        </tr>
        <tr>
            <th>请再输入一遍</th>
            <td><input type="password" name="renewPwd" id="renewPwd"><span id="r_tip" style="color: red"></span></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${admin.id}"/>
    <input type="submit" name="submit" value="提交"/>
</form>
</body>
</html>