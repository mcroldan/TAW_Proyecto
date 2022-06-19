<%@ page import="es.taw.taw_proyecto.dto.EstudioDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xdmrg
  Date: 14/06/2022
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CRUD Analista</title>
    <style>
        table, th, td {
            border:1px solid black;
        }
    </style>
</head>
<body>
<h1>LISTA DE ESTUDIOS</h1>
<form method="post" action="/analista/filter">
    <h3>Filtro por nombre</h3>
    <input type="text" placeholder="Dejar en blanco para mostrar todo" name="filter">
    <button>Buscar</button><br/><br/>
</form>

<h3>Acciones</h3>
<a href="./create">
    <button name="button" value="OK" type="button">Nuevo estudio</button>
</a><br/><br/>

<form method="post" action="/logout">
    <button>Cerrar sesión</button>
</form><br/>

<h3>Lista de estudios</h3>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>NOMBRE</th>
        <th>TABLA</th>
        <th>ORDEN</th>
        <th>TIPO DE ORDEN</th>
        <th>GRUPOS</th>
        <th>OPERACION</th>
        <th>NUMERO ELEMENTOS</th>
        <th>BORRAR</th>
        <th>EDITAR</th>
        <th>DUPLICAR</th>
        <th>VER</th>

    </tr>
    <%
        List<EstudioDTO> estudios = (List)request.getAttribute("estudios");
        if(estudios != null){
            for (EstudioDTO estudio : estudios){
    %>
    <tr>
        <td><%= estudio.getId()%></td>
        <td><%= estudio.getNombre()%></td>
        <td><%= estudio.getTabla() %></td>
        <td><%= estudio.getOrdenar() %></td>
        <td><%= estudio.getTipoOrden()%></td>
        <td><%= estudio.getAgrupar() %></td>
        <td><%= estudio.getOperacion() %></td>
        <td><%= estudio.getNumElementos()%></td>
        <td><a href="./remove/<%= estudio.getId()%>">Borrar</a></td>
        <td><a href="./edit/<%= estudio.getId() %>" >Editar</a></td>
        <td><a href="./duplicate/<%= estudio.getId() %>" >Duplicar</a></td>
        <td><a href="./view/<%= estudio.getId() %>" >Ver</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
