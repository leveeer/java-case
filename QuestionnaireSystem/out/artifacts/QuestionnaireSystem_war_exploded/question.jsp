<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>问卷详情</h3></center>
    <form action="${pageContext.request.contextPath}/addAnswerServlet" method="post">
        <label>作答用户：${user.username}</label>
    <c:forEach items="${questions}" var="question" varStatus="s">
        <!-- 隐藏域提交id -->
        <input type="hidden" name="projectid" value="${question.projectid}">
        <!-- 隐藏域提交id -->
        <input type="hidden" name="editor" value="${user.username}">
        <div class="form-group">
            <label>问题${s.count}：${question.question}</label>
        </div>
        <div class="form-group">
            <label><input type="text" class="form-control" id="answer" name="answer"  placeholder="请输入您的答案"></label>
        </div>
    </c:forEach>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" onclick="" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/findQuestionnaireByIsuseServlet'"/>
        </div>
    </form>
</div>

</body>
</html>
