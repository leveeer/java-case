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
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>添加新课题</h3></center>
    <form action="${pageContext.request.contextPath}/addQuestionnaireServlet" method="post">
        <div class="form-group">
            <label for="title">调查课题：</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入调查课题">
        </div>

        <div class="form-group">
            <label for="createtime">创建时间：</label>
            <input type="date" class="form-control" id="createtime" name="createtime" placeholder="">
        </div>
        <div class="form-group">
            <label for="isuse">是否启用：</label>
            <input type="text" class="form-control" id="isuse" name="isuse"   placeholder="请输入是或否">
        </div>
        <div class="form-group">
            <label for="endtime">截止时间：</label>
            <input type="date" class="form-control" id="endtime" name="endtime" placeholder="">
        </div>
        <div class="form-group">
            <label for="editor">创建人：</label>
            <input type="text" class="form-control" id="editor" name="editor" value="${admin.username}" readonly="readonly" placeholder="">
        </div>
        <div class="form-group">
            <label for="introduction">调查简介</label>
            <textarea cols="10"  class="form-control" id="introduction" name="introduction" placeholder=""></textarea>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/index.jsp'"/>
        </div>
    </form>
</div>
</body>
</html>
