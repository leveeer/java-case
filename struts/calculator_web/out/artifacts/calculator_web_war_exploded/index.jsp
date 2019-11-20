<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/9
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="op" uri="http://www.jxau.com/calculate" %>
<html>
<head>
    <title>简易计算器</title>
</head>
<script>
    window.onload = function () {
        //1.给表单绑定onsubmit事件
        document.getElementById("form").onsubmit = function () {
            return checkPrecedingNum() && checkBackNum();
        };

        //给操作数和被操作数分别绑定离焦事件
        document.getElementById("precedingNum").onblur = checkPrecedingNum;
        document.getElementById("backNum").onblur = checkBackNum;
    };

    function checkPrecedingNum() {
        var precedingNum = document.getElementById("precedingNum").value;
        //alert(precedingNum);
        var regex = /^-?\d+(\.\d+)?$/;
        var flag = regex.test(precedingNum);
        var error = document.getElementById("error");
        if (!flag) {
            error.innerHTML = "输入格式有误";
        } else {
            error.innerHTML = "";
        }
        return flag;
    }

    function checkBackNum() {
        var backNum = document.getElementById("backNum").value;
        var operator = document.getElementById("operator").value;
        var error = document.getElementById("error");
        if (operator == "/" && backNum == 0.0) {
            error.innerHTML = "除数不能为0";
            return false;
        }
        var regex = /^-?\d+(\.\d+)?$/;
        var flag = regex.test(backNum);
        if (!flag) {
            error.innerHTML = "输入格式有误";
        } else {
            error.innerHTML = "";
        }
        return flag;
    }
</script>
<body>
<h3 style="text-align: center">简易计算器</h3>
<div style="text-align: center">
    <form id="form" action="${pageContext.request.contextPath}/result.do" method="post">
        <input style="width: 160px;height: 23px;" type="text" name="precedingNum" id="precedingNum"
               value="${numberForm.precedingNum}"><br>
        <select style="width: 160px;height: 23px; margin-top: 8px" name="operator" id="operator">
            <option value="+" <c:if test="${numberForm.operator eq '+'}">selected</c:if>>+</option>
            <option value="-" <c:if test="${numberForm.operator eq '-'}">selected</c:if>>-</option>
            <option value="*" <c:if test="${numberForm.operator eq '*'}">selected</c:if>>*</option>
            <option value="/" <c:if test="${numberForm.operator eq '/'}">selected</c:if>>/</option>
        </select><br>
        <input style="width: 160px;height: 23px;margin-top: 8px" type="text" name="backNum" id="backNum"
               value="${numberForm.backNum}"><br>
        <input style="width: 160px;height: 23px;margin-top: 8px;" type="submit" value="=">
    </form>
</div>
<div style="text-align: center">
    <label>
        <input style="width: 160px;height: 23px;" type="text"
                <c:if test="${empty numberForm}">
                    value = ""
                </c:if>
                <c:if test="${numberForm.operator eq '+'}">
                    value = "${op:add(numberForm.precedingNum, numberForm.backNum)}"
                </c:if>
                <c:if test="${numberForm.operator eq '-'}">
                    value = "${op:subtract(numberForm.precedingNum, numberForm.backNum)}"
                </c:if>
                <c:if test="${numberForm.operator eq '*'}">
                    value = "${op:multiply(numberForm.precedingNum, numberForm.backNum)}"
                </c:if>
                <c:if test="${numberForm.operator eq '/'}">
                    value = "${op:divide(numberForm.precedingNum, numberForm.backNum)}"
                </c:if>
               readonly/>

    </label>

    <div>
        <span id="error" style="color: red"></span>
    </div>
</div>
</body>
</html>
