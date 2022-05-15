<%-- 
    Document   : jsplogged
    Created on : 2 abr 2022, 21:56:54
    Author     : xdmrg
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="taw.entities.Estudio"%>
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
        <h1>LISTA DE ESTUDIOS</h1>
        <form method="post" action="AnalistaServlet">
            <h3>Filtro por nombre</h3>
            <input type="text" placeholder="Dejar en blanco para mostrar todo" name="filtro">
            <button>Buscar</button><br/><br/>
        </form>
        
        <h3>Acciones</h3>
        <a href="./jsp-crearestudio.jsp">
            <button name="button" value="OK" type="button">Nuevo estudio</button>
        </a><br/><br/>
        
        <form method="post" action="LogoutServlet">
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
            List<Estudio> estudios = (List)request.getAttribute("estudios");
            if(estudios != null){
                for (Estudio estudio : estudios){
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
            <td><a href="AnalistaDeleteServlet?estudio=<%= estudio.getId()%>">Borrar</a></td> 
           <td><a href="AnalistaUpdateServlet?estudio=<%= estudio.getId() %>&tabla=<%= estudio.getTabla() %>" >Editar</a></td>  
           <td><a href="AnalistaDuplicateServlet?estudio=<%= estudio.getId() %>" >Duplicar</a></td>
           <td><a href="AnalistaProcessQueryServlet?estudio=<%= estudio.getId() %>" >Ver</a></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    </body>
</html>
