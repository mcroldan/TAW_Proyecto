<%-- 
    Document   : CrudUsuarios
    Created on : 13-may-2022, 23:26:59
    Author     : Alfonso García Gálvez
--%>


<%@page import="taw.entities.Rol"%>
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
        <%
            List<Rol>roles= (List<Rol>)request.getAttribute("roles");
            %>
        <form method="post" action="AdministradorCrearUserServlet">
            <select name="rol">
                <% for(Rol rol : roles) {%>
                <option value=<%= rol.getId() %>><%= rol.getNombre()%></option>
                <% } %>
            </select>
            <button>Crear Usuario</button>
        </form>
        <form method="post" action="AdministradorServlet">
            
            <input type="text" placeholder="Introduzca filtro por nombre o apellido" name="filtro">
            <button>Buscar</button>
        </form>
        
    <table style="width:100%">
        <tr>
            <th>USUARIO</th>
            <th>CONTRASEÑA</th>
            <th>EDAD</th>
            <th>ID</th>
        </tr>
        <%
            List<Usuario> usuarios = (List)request.getAttribute("usuarios");
            for (Usuario user : usuarios){
        %>
        <tr>
            <td><%= user.getUsername()%></td>
            <td><%= user.getPassword()%></td>
            <td><%= user.getEdad() %></td>
            <td><%= user.getId()%></td>
            <td><a href="UsuarioBorrarServlet?id=<%= user.getId() %>">Borrar</a></td> 
        </tr>
        <%
            }
        %>
        
        <form method="post" action="LogoutServlet">
            <button>Cerrar sesión</button>
        </form>
    </table>
    </body>
</html>
