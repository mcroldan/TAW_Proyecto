<%-- 
    Document   : CrudProductos
    Created on : 13-may-2022, 23:37:20
    Author     : Alfonso
--%>


<%@page import="taw.entities.Producto"%>
<%@page import="taw.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, th, td {
              border:1px solid black;
            }
        </style>
    </head>
    <body>
        <form method="post" action="AdministradorProductoServlet">
            <input type="text" placeholder="Introduzca filtro por categoria de producto" name="filtro">
            <button>Buscar</button>
        </form>
        
    <table style="width:100%">
        <tr>
            <th>PRODUCTO</th>
            <th>DESCRIPCION</th>
            <th>PRECIO</th>
            <th>MARCA</th>
            <th>CATEGORIA</th>
            
        </tr>
        <%
            List<Producto > productos = (List)request.getAttribute("productos");
            for (Producto prod : productos){
        %>
        <tr>
            <td><%= prod.getTitulo()%></td>
            <td><%= prod.getDescripcion()%></td>
            <td><%= prod.getPreciosalida()%></td>
            <td><%= prod.getMarca()%></td>
            <td><%= prod.getCategoria()%></td>
            <td><a href="ProductoBorrarServlet?id=<%= prod.getId()%>">Borrar</a></td> 
            <td><a href="ProductoCrearEditarServlet?id=<%= prod.getId() %>">Editar</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="ProductoCrearEditarServlet">Crear nuevo Producto ... </a>
    </body>
</html>
