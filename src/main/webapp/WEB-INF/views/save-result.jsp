<%--
  Created by IntelliJ IDEA.
  User: 은또
  Date: 2023-10-26
  Time: 오전 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    jsp용 표현식 => ${} --%>
    <li>id = ${member.id}</li>
    <li>username = ${member.username}</li>
    <li>age = ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
