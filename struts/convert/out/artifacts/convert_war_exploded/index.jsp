<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="ct" uri="http:www.jxau.com/convert" %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>自定义转换器测试</h3>
<form action="${pageContext.request.contextPath}/convert.do" method="post">
	int数：<input type="text" name="intValue" value="${ConvertForm.intValue}"><br>
	double数：<input type="text" name="doubleValue" value="${ConvertForm.doubleValue}"><br>
    sql日期：<input type="text" name="sqlDate" value="${ConvertForm.sqlDate}"><br>
	util日期：<input type="text" name="utilDate" value="${ct:convertUtilDate(ConvertForm.utilDate)}"><br>
	<input type="submit" value="提交">
</form>
</body>
</html>