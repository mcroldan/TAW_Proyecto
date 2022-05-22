<%-- 
    Document   : cabeceraComprador
    Created on : 25-abr-2022, 14:18:56
    Author     : Carlos Ortega Chirito
--%>

<%@page import="taw.dto.UsuarioDTO"%>
<%@page import="taw.entities.Usuario"%>
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
        <td><a href="inicioComprador.jsp">Volver a la página principal</a></td>        
        <td><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>
