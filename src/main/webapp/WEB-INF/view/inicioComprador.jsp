<%-- 
    Document   : inicio
    Created on : 25-abr-2022, 14:05:53
    Author     : Carlos Ortega Chirito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de inicio</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/view/cabecera.jsp" />
        <h1>Página de inicio</h1> <br>
        <a href="/categoriasPreferidas/mostrar">Categorías preferidas</a> <br>
        <a href="productos/mostrarCompradosYFavoritos">Productos comprados y favoritos</a> <br>
        <a href="/productos/mostrar">Listado de productos</a> <br>
        <a href="/pujas/mostrar">Mis pujas abiertas</a>
    </body>
</html>
