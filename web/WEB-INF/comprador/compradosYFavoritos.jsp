<%-- 
    Document   : compradosYFavoritos
    Created on : 01-may-2022, 15:35:19
    Author     : PC
--%>

<%@page import="taw.entities.Usuario"%>
<%@page import="taw.entities.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis productos</title>
    </head>
    <body>
        <h1>Productos comprados y favoritos</h1>
            <%
                List<Producto> productos = (List)request.getAttribute("productos");
                Usuario user =  (Usuario)session.getAttribute("usuario");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(productos.isEmpty()){
            %>
                    No tienes productos favoritos ni comprados.
            <% }else { %>
                    <table border="1"> 
                        <tr>
                            <th>Vendedor</th>
                            <th>Título</th>                
                            <th>Imagen</th>
                            <th>Marca</th>
                            <th>Categoría</th>
                            <th>Fecha de publicación</th>
                        </tr>
            <%      for (Producto prod: productos) { %>
                        <tr>
                        <td> <%= prod.getVendedor().getUsername()%> </td>
                        <td> <%= prod.getTitulo() %> </td>
                        <td> <img src=<%= prod.getUrlFoto()%> width="" height="" alt="Foto del producto"/>
                        </td>
                        <td> <%= prod.getMarca() %> </td>
                        <td> <%= prod.getCategoria().getNombre() %>
                        <td> <%= df.format(prod.getFechainicio()) %> </td>
                        <td><a href="ProductoAlternarFavoritoServlet?id=<%= prod.getId() %>+userid=<%= user.getId() %>">Borrar</a></td>
                        <td><a href="PujaNuevaServlet?id=<%= prod.getId() %>">Hacer puja</a></td>
                        
                       </tr>
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
    </body>
</html>
