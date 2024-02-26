<%--
  Created by IntelliJ IDEA.
  User: FALL
  Date: 2024/2/11
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>workersLogin</title>
</head>
<body>
<h1>员工登录</h1>
<form action="/ademob/workers/selectWorker" method="post">
    <label for="name">用户名：</label>
    <input type="text" id="name" name="name">
    <br>
    <br>
    <label for="password">密码：</label>
    <input type="password" id="password" name="password">
    <br>
    <br>
    <input type="submit" id="submit" value="登录">
    <input type="reset" id="reset" value="重置"> <a href="managersLogin.jsp">管理员登录</a>
    <br>
    <div id="err_msg" style="color: red">${login_msg}</div>
</form>

</body>
</html>