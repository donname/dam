<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2022/5/17
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--这里的提交的路径，需要找到项目的路径--%>
<%--{pageContext.request.contextPath}代表当前的项目--%>
<form action="${pageContext.request.contextPath}/Redirect" method="get">
    用户名：<input type="text" name="username">
    密码：<input type="password" name="password">
    <input type="submit">
</form>

</body>
</html>
