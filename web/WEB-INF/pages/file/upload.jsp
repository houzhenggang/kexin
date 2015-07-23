<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>文件上传</h1>

<form method="post" action="upload" enctype="multipart/form-data">
    文件名：<input type="text" name="name"/><br>
    <input type="file" name="file"/><br>
    <input type="submit"/>
</form>
</body>
</html>
