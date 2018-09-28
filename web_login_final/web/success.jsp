<%@ page import="com.hello.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Y-Rambler
  Date: 2018/9/11
  Time: 下午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录成功</h1>
<%
    User user = new User();
    if(request.getSession().getAttribute("existUser")!=null){
          user = (User) request.getSession().getAttribute("existUser");
    }
%>
<h2>您好，<%= user.getNickName() %></h2>
<h4><a href="/LogoutServlet" >退出</a></h4>
</body>
</html>
