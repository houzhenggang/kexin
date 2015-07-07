<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
${errorMessage}
<form method="post" action="register">
    用户名:<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    确认密码：<input type="password" name="re_password"><br>
    邮箱：<input type="text" name="email"><br>
    电话号码：<input type="text" name="phoneNumber"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
