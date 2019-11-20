<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改问卷信息</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改问卷信息</h3>
    <form action="${pageContext.request.contextPath}/updateQuestionnaireServlet" method="post">
        <!-- 隐藏域提交id -->
            <input type="hidden" name="id" value="${questionnaire.id}">
            <div class="form-group">
            <label for="title">调查课题：</label>
            <input type="text" class="form-control" id="title" name="title" value="${questionnaire.title}"  placeholder="请输入调查课题" />
        </div>
        <div class="form-group">
            <label for="createtime">创建时间：</label>
            <input type="text" class="form-control" id="createtime"  name="createtime" value="${questionnaire.createtime}" readonly="readonly" placeholder="" />
        </div>

        <div class="form-group">
            <label for="isuse">是否启用：</label>
            <input type="text" class="form-control" id="isuse" name="isuse" value="${questionnaire.isuse}" placeholder="请输入是或否"/>
        </div>

        <div class="form-group">
            <label for="endtime">截止时间：</label>
            <input type="date" class="form-control" id="endtime" name="endtime" value="${questionnaire.endtime}" placeholder=""/>
        </div>
        <div class="form-group">
            <label for="editor">创建人：</label>
            <input type="text" class="form-control" id="editor" name="editor" value="${questionnaire.editor}" readonly="readonly" placeholder=""/>
        </div>

        <div class="form-group">
            <label for="introduce">课题简介：</label>
            <textarea rows="5" class="form-control" id="introduce" name="introduce" value="${questionnaire.introduce}" placeholder="请输入课题简介"></textarea>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/questionnaireListServlet'"/>
        </div>
    </form>
</div>
</body>
</html>
