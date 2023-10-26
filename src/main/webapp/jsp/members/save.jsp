<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: 은또
  Date: 2023-10-25
  Time: 오후 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 자바 코드를 넣을 수 있는곳--%>
<%
    // jsp에서는 request, response는 service 작성 없이 바로 가능(서비스 로직이 바로 호출됨)
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    // request.getParameter의 응답결과는 항상 string(문자)
    // age는 int타입이기 때문에 형변환을 해줘야 한다.
    int age = Integer.parseInt(request.getParameter("age"));

    // 맴버 객체 만들기
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
<ul>
<%--    <%= 자바 코드를 출력할 수 있는곳 %> --%>
    <li>id =<%=member.getId()%></li>
    <li>username =<%=member.getUsername()%></li>
    <li>age =<%=member.getAge()%></li>
</ul>
    <a href="/index.html">메인</a>
</body>
</html>
