<%-- 
    Document   : inicio
    Created on : 25-abr-2022, 14:05:53
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de inicio</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/cabecera.jsp" />
        <h1>Página de inicio</h1> <br>
        <a href="CategoriasPreferidasServlet">Categorías preferidas</a> <br>
        <a href="ListadoCompradosYFavoritosServlet">Productos comprados y favoritos</a> <br>
        <a href="ListadoProductosServlet">Listado de productos</a> <br>
        <a href="PujaServlet">Mis pujas abiertas</a><br>
        <a href="CrudUsuarios.jsp">crear usuario</a> <br>
    </body>
</html>
