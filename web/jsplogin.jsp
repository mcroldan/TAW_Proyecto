<%-- 
    Document   : jsplogin
    Created on : 2 abr 2022, 21:44:26
    Author     : xdmrg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String error = (String)request.getAttribute("error");
            if(error == null) error = "";
        %>
            <%= error %>
        <form method="post" action="LoginServlet" name="login" accept-charset="UTF-8">
            Username
            <input type="text" name="loginInput"/>
            <br/>
            Password
            <input type="password" name="passInput"/>
            <br/>
            <button>Enviar</button>
        </form>
        
        <button type="button" onclick="window.location.href='newUser.jsp'">Registrarse</button>
    </body>
</html>
