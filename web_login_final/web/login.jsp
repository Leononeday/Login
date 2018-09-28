<%--
  Created by IntelliJ IDEA.
  User: Y-Rambler
  Date: 2018/9/11
  Time: 下午8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script>
    function changImg() {
        document.getElementById("img").src="/CheckImgServlet?time="+new Date().getTime();
    }
</script>
<body>
<h1>欢迎登录本网站！！！</h1>
<%
    String msg = "";
    if(request.getAttribute("msg")!=null){
         msg = (String) request.getAttribute("msg");
    }
%>
    <form action="/LoginServlet" method="post">
        <table>
            <h4><font color="red"><%= msg %></font></h4>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" value="${ cookie.remember.value }"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>验证码:</td>
                <td><input type="text" name="checkcode" /></td>
                <td><img src="/CheckImgServlet" name="checkcode" id="img"></td>
                <td><a href="" onclick="changImg()">看不清，换一张</a></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="remember"/></td>
                <td>记住用户名</td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
</body>
</html>
