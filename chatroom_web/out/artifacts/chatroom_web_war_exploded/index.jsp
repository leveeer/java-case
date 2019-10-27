<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/1
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>聊天页面</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/emoji_jQuery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }
        body{
            /*background: url("${pageContext.request.contextPath}/img/bg.jpg") no-repeat center;*/
            padding-top: 50px;
        }

        .rg_layout{
            width: 900px;
            height: 500px;
            border: 2px solid black;
            background-color: whitesmoke;
            /*让div水平居中*/
            margin: auto;
        }

        .rg_online{
            margin-top: 80px;
            /*border: 1px solid red;*/
            float: left;
            width: 200px;
            height: 50px;
            text-align: center;
        }


        .rg_top{
           /* float: right;*/
            border: 1px solid black;
            width: 898px;
            height: 80px;
            font-family: "FZShuTi";
            font-size: 36px;
            color: blue;
            background: url("${pageContext.request.contextPath}/img/top.jpg") no-repeat center;
        }

        .rg_voice{
            margin-top: 15px;
            float: right;
            border: 1px solid whitesmoke;
            width: 670px;
            height: 30px;
        }

        .rg_center{
            float: right;
            border: 1px solid black;
            width: 670px;
            height: 260px;
            /*text-align: center;*/
            /*background-color: aliceblue;*/
            overflow-y: scroll;
            background: url("${pageContext.request.contextPath}/img/bg.jpg") no-repeat center;
        }

        .rg_function{
            float: right;
            border: 1px solid black;
            width: 670px;
            height: 40px;

        }

        .rg_down{
            float: right;
            border: 1px solid black;
            width: 670px;
            height: 60px;
        }

        .rg_left{
            border: 1px solid black;
            float: left;
            width: 200px;
            height: 300px;
            text-align: center;
            overflow-y: scroll;
            margin-top: -290px;
            background: url("${pageContext.request.contextPath}/img/left.jpg");
        }

        .rg_exit{
            border: 1px solid whitesmoke;
            float: left;
            width: 200px;
            height: 50px;
            text-align: center;
        }

        #select,#message,#button,#exit,#color,#font,#fontsize{
            /*设置边框圆角*/
            border-radius: 5px;
            height: 30px;
        }
        /*设置滚动条不显示，好像仅谷歌有效*/
        ::-webkit-scrollbar{
            display: none;
            width: 0;
            height: 0;
        }
    </style>

    <script type="text/javascript">
        $(function () {

            $("#center").scrollTop($("#center")[0].scrollHeight);   //当页面加载时滚动条滚到最下面

           /* //给发送按钮绑定提交事件
            $("#button").submit(function () {
                //$.post("${pageContext.request.contextPath}/chatServlet/send",$("#form").serialize(),function (data) {
                    alert(data);
                    //获取显示div
                    $("#center").html(data);
                });
            });*/


            $.Lemoji({
                emojiInput: '#input',
                emojiBtn: '#btn',
                position: 'LEFTBOTTOM',
                length: 8,
                emojis: {
                    qq: {path: 'static/images/qq/', code: ':', name: 'QQ表情'},
                    tieba: {path: 'static/images/tieba', code: ';', name: "贴吧表情"},
                    emoji: {path: 'static/images/emoji', code: ',', name: 'Emoji表情'}
                }
            });


            $('#send').click(function () {
                var content = $('#input').val();
                content = $.emojiParse({
                    content: content,
                    emojis: [{type: 'qq', path: 'static/images/qq/', code: ':'}, {
                        path: 'static/images/tieba/',
                        code: ';',
                        type: 'tieba'
                    }, {path: 'static/images/emoji/', code: ',', type: 'emoji'}]
                });
                $('#test').html(content);
            });






            //下拉列表中不显示自己
            var element = document.getElementById("select");
            //获取所有的option标签
            var optionElements = element.getElementsByTagName("option");
            //遍历option
            for(var i = 0; i < optionElements.length; i++) {
                //alert(optionElements[i]);
                //alert(optionElements[i].value)
                if (optionElements[i].value == '${userLogin.username}'){
                    optionElements[i].style.display = "none";
                }
            }

            /*//定时刷新消息文本
            setInterval(function () {
                $("#center").load(location.href + " #center");
            },1000);*/
        });

        //退出前确认
        function exit(username) {
            if(confirm("您确定要退出吗？")){
                location.href = "${pageContext.request.contextPath}/chatServlet/exit?username=" + username;
            }
        }

        //定时刷新页面
        function fresh(){
            window.location.reload();
        }
        setTimeout('fresh()',10000);


    </script>

</head>
<body>

<div class="rg_layout">
    <div class="rg_online">
        <p>在线人员列表</p>
        <p>当前在线[<span style="color: red">${userMap.size()}</span>]人</p>
    </div>

    <div class="rg_top">
        <span style="margin-left: 100px; font-size: 50px">z l s g 聊 天 室</span>
    </div>

    <div class="rg_voice">
        <marquee behavior="scroll" direction="right" loop="3">
            <span id="voice" style="color: red">${message}</span>
        </marquee>
        <%--<%
            application.removeAttribute("message");
        %>--%>
    </div>

    <div class="rg_center" id="center">
        ${publicMessage}
        <c:forEach items="${toUserMsgMap}" var="toUserMsgMap">
            <c:if test="${userLogin.username == toUserMsgMap.key}">
                ${toUserMsgMap.value}
            </c:if>
        </c:forEach>
        <c:forEach items="${fromUserMsgMap}" var="fromUserMsgMap">
            <c:if test="${userLogin.username == fromUserMsgMap.key}">
                ${fromUserMsgMap.value}
            </c:if>
        </c:forEach>


            <div id="test"></div>
            <div id="div-emoji">
                <p>
                    <input id="input" type="text">
                </p>
                <p>
                    <button id="send">发送</button>
                    <button id="btn">emojiBtn</button>
                </p>
            </div>


    </div>
    <form id="form" action="${pageContext.request.contextPath}/chatServlet/send" method="post">
        <div class="rg_function">
            字体颜色：<select id="color" name="color" style="width: 100px; margin-top: 4px">
            <option selected style="color: blue" value="blue">默认颜色</option>
            <option style="color:#FF0000" value="FF0000">红色热情</option>
            <option style="color:black" value="black">黑色深沉</option>
            <option style="color:#ff00ff" value="ff00ff">桃色浪漫</option>
            <option style="color:#009900" value="009900">绿色青春</option>
            <option style="color:#009999" value="009999">青色清爽</option>
            <option style="color:#990099" value="990099">紫色拘谨</option>
            <option style="color:#990000" value="990000">暗夜兴奋</option>
            <option style="color:#000099" value="000099">深蓝忧郁</option>
            <option style="color:#999900" value="999900">卡其制服</option>
            <option style="color:#ff9900" value="ff9900">镏金岁月</option>
            <option style="color:#0099ff" value="0099ff">湖波荡漾</option>
            <option style="color:#9900ff" value="9900ff">发亮蓝紫</option>
            <option style="color:#ff0099" value="ff0099">爱的暗示</option>
            <option style="color:#006600" value="006600">墨绿深沉</option>
            <option style="color:#999999" value="999999">烟雨蒙蒙</option>
        </select>

            字体：<select id="font" name="font" style="width: 100px; margin-top: 4px">
            <option selected style="font-family: Microsoft YaHei" value="Microsoft YaHei">微软雅黑</option>
            <option style="font-family: SimHei" value="SimHei">黑体</option>
            <option style="font-family: NSimSun" value="NSimSun">新宋体</option>
            <option style="font-family: FangSong" value="FangSong">仿宋</option>
            <option style="font-family: KaiTi" value="KaiTi">楷体</option>
            <option style="font-family: Microsoft JhengHei" value="Microsoft JhengHei">微软正黑</option>
            <option style="font-family: LiSu" value="LiSu">隶书</option>
            <option style="font-family: YouYuan" value="YouYuan">幼圆</option>
            <option style="font-family: FZShuTi" value="FZShuTi">方正舒体</option>
            <option style="font-family: FZYaoti" value="FZYaoti">方正姚体</option>
            <option style="font-family: STSong" value="STSong">华文宋体</option>
            <option style="font-family: STZhongsong" value="STZhongsong">华文中宋</option>
            <option style="font-family: STCaiyun" value="STCaiyun">华文彩云</option>
            <option style="font-family: STHupo" value="STHupo">华文琥珀</option>
            <option style="font-family: STLiti" value="STLiti">华文隶书</option>
            <option style="font-family: STXingkai" value="STXingkai">华文行楷</option>
            <option style="font-family: STXinwei" value="STXinwei">华文新魏</option>
        </select>

            字体大小：<select id="fontsize" name="fontsize" style="width: 100px; margin-top: 4px">
            <option selected style="font-size: 16px" value="16px">16</option>
            <option style="font-size: 10px" value="10px">10</option>
            <option style="font-size: 12px" value="12px">12</option>
            <option style="font-size: 14px" value="14px">14</option>
            <option style="font-size: 18px" value="18px">18</option>
            <option style="font-size: 20px" value="20px">20</option>
            <option style="font-size: 22px" value="22px">22</option>
            <option style="font-size: 24px" value="24px">24</option>
            <option style="font-size: 26px" value="26px">26</option>
            <option style="font-size: 28px" value="28px">28</option>
        </select>



        </div>

        <div class="rg_down">
                <br>&nbsp;&nbsp;聊天对象：<select name="to" id="select" style="width: 100px">
                <option value="">所有人</option>
                <c:forEach items="${userMap}" var="userMap">
                    <option id="option" value="${userMap.key}">${userMap.key}</option>
                </c:forEach>
            </select>
                <input id="message" style="width: 400px;" type="text" name="message"/>
                <input id="button" class="btn-primary" style="width: 60px;" type="submit" value="发送" />
        </div>
    </form>
    <div class="rg_left">
        <table style="text-align: center" width="180px" border="1" class="table table-bordered table-hover">
            <c:forEach var="userMap" items="${userMap}">
                <tr>
                    <td>${userMap.key}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="rg_exit">
        <input style="margin-top: 10px; width: 100px" id="exit" class="btn-primary" type="button" value="退出聊天室" onclick="exit('${userLogin.username}')">
    </div>

</div>

</body>

</html>
