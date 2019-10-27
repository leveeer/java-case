<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <style>
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }
        body{
            background: url("img/regist.jpg") no-repeat center ;
            padding-top: 25px;
        }

        .rg_layout{
            width: 900px;
            height: 500px;
             /*border: 8px solid #EEEEEE;
             background-color: white;*/
            /*让div水平居中*/
            margin: auto;
        }

        .rg_left{
            /* border: 1px solid red;*/
            float: left;
            margin: 15px;
        }
        .rg_left > p:first-child{
            color:#FFD026;
            font-size: 30px;
        }

        .rg_left > p:last-child{
            color:#A6A6A6;
            font-size: 20px;
        }


        .rg_center{
            margin-top: 250px;
            margin-left: 50px;
            float: left;
           /* border: 1px solid red;*/

        }

        .rg_right{
             /*border: 1px solid red;*/
            float: right;
            /*margin: 80px;*/
            margin-top: 40px;
        }

        .rg_right > p:first-child{
            font-size: 15px;

        }
        .rg_right p a {
            color:#FFD026;
        }

        .td_left{
            width: 100px;
            text-align: right;
            height: 45px;
        }
        .td_right{
            padding-left: 50px ;
        }

        #username,#password{
            width: 251px;
            height: 32px;
            border: 1px solid #A6A6A6 ;
            /*设置边框圆角*/
            border-radius: 5px;
            padding-left: 10px;
        }


        #btn_sub{
            width: 150px;
            height: 40px;
            background-color: #FFD026;
            border: 1px solid #FFD026 ;
            margin-top: 20px;
        }
        .error{
            color:red;
        }
        #td_sub{
            padding-left: 200px;
        }

    </style>
    <script type="text/javascript">
        /*
            分析：
                1.给表单绑定onsubmit事件。监听器中判断每一个方法校验的结果。
                    如果都为true，则监听器方法返回true
                    如果有一个为false，则监听器方法返回false
                2.定义一些方法分别校验各个表单项。
                3.给各个表单项绑定onblur事件。
         */

        window.onload = function(){
            //1.给表单绑定onsubmit事件
            document.getElementById("form").onsubmit = function(){
                //调用密码校验方法   checkPassword();
                return checkUserName() && checkPassword();
            };

            //给密码框绑定离焦事件
            document.getElementById("username").onblur = checkUserName;
            document.getElementById("password").onblur = checkPassword;
        };

        function checkUserName() {
            var username = document.getElementById("username").value;
            var s_username = document.getElementById("s_username");
            if (username == ""){
                s_username.innerHTML = "用户名不能为空";
                return false;

            }else if(username.length < 2 || username.length > 16){
                s_username.innerHTML = "用户名格式错误";
                return false;
            }else{
                s_username.innerHTML = "";
                return true;
            }
        }

        //校验密码
        function checkPassword(){
            //1.获取密码的值
            var password = document.getElementById("password").value;
            //4.提示信息
            var s_password = document.getElementById("s_password");
            if (password == "") {
                s_password.innerHTML = "密码不能为空";
                return false;
            }
            //2.定义正则表达式
            var reg_password = /^\w{6,12}$/;
            //3.判断值是否符合正则的规则
            var flag = reg_password.test(password);

            if(flag){
                //提示绿色对勾
                s_password.innerHTML = "<img width='35' height='25' src='img/gou.png'/>";
            }else{
                //提示红色密码格式有误
                s_password.innerHTML = "密码格式有误";
            }
            return flag;
        }



    </script>
</head>
<body>

<div class="rg_layout">
    <div class="rg_left">
        <p>新用户注册</p>
        <p>USER REGISTER</p>

    </div>

    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 form-->
            <form action="${pageContext.request.contextPath}/userAction.do?command=register" id="form" method="post">
                <table>
                    <tr>
                        <td class="td_left"><label for="username">用户名</label></td>
                        <td class="td_right">
                            <input type="text" name="username" id="username" placeholder="请输入用户名">
                            <span id="s_username" class="error"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="password">密码</label></td>
                        <td class="td_right">
                            <input type="password" name="password" id="password" placeholder="请输入密码">
                            <span id="s_password" class="error"></span>
                        </td>
                    </tr>


                    <tr>
                        <td colspan="2" id="td_sub"><input type="submit" id="btn_sub" value="注册"></td>
                    </tr>
                </table>

            </form>


        </div>

    </div>

    <div class="rg_right">
        <p>已有账号?<a href="${pageContext.request.contextPath}/login.jsp">立即登录</a></p>
    </div>


</div>


</body>
</html>
