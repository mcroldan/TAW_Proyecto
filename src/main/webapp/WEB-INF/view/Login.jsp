<%--
  Created by IntelliJ IDEA.
  User: Manuel Cristóbal Roldán Gómez
  Date: 14/06/2022
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<%
    String error = (String)request.getAttribute("error");
    if(error == null) error = "";
%>
<%= error %>
<form method="post" action="/login" name="login" accept-charset="UTF-8">
    Username
    <input type="text" name="username"/>
    <br/>
    Password
    <input type="password" name="password"/>
    <br/>
    <button>Enviar</button>
</form>

<button type="button" onclick="window.location.href='/register'">Registrarse</button>
</body>
</html>