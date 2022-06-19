<%@ page import="es.taw.taw_proyecto.dto.UsuarioDTO" %>
Created by IntelliJ IDEA.
  User: xdmrg
  Date: 14/06/2022
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logged</title>
</head>
<body>
<%
    UsuarioDTO u = (UsuarioDTO) session.getAttribute("usuario");
    String rol = u.getRol().getNombre();
%>

¡Bienvenido, <%= u.getUsername() %> , tu rol es <%= rol %> y tu ID es <%= u.getId() %>!

<%
    if(rol.equalsIgnoreCase("Analista")){
%>
<form method="get" action="/analista/" name="goToCrud">
    <button>Estudios estadísticos</button>
</form>

<%
} else if(rol.equalsIgnoreCase("Administrador")){ %>
<form method="get" action="AdministradorServlet" name="goToCrud">
    <button>CRUD Usuarios</button>
</form>

<form method="post" action="AdministradorProductoServlet">
    <button>CRUD Productos</button>
</form>
<%  } %>

<form method="post" action="/logout">
    <button>Cerrar sesión</button>
</form>
</body>
</html>