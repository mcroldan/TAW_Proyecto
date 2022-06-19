<%-- 
    Document   : cabecera
    Created on : 25-abr-2022, 14:18:56
    Author     : Carlos Ortega Chirito
--%>
<%@ page import="es.taw.taw_proyecto.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>
<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getUsername() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <td><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>
