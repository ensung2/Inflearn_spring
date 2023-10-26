<%--
  Created by IntelliJ IDEA.
  User: 은또
  Date: 2023-10-25
  Time: 오후 8:23
  To change this template use File | Settings | File Templates.
--%>
<%-- jsp 문서라는 선언 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 실행시 .jsp까지 적어줘야 한다. --%>
<form action="/jsp/members/save.jsp" method="post">
    username: <input name="username" type="text"/>
    age: <input name="age" type="text"/>
    <button type="submit">전송</button>
</form>
</body>
</html>
