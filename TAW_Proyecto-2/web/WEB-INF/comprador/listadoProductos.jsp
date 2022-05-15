<%-- 
    Document   : listadoProductos
    Created on : 30-abr-2022, 22:14:48
    Author     : Carlos
--%>

<%@page import="taw.entities.Usuario"%>
<%@page import="taw.entities.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
    </head>
    <body>
        <h1>Listado de productos</h1>
            <%
                List<Producto> productos = (List)request.getAttribute("productos");
                Usuario user =  (Usuario)session.getAttribute("usuario");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(productos.isEmpty()){
            %>
                    No hay productos disponibles.
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
                        <td><a href="ProductoAlternarFavoritoServlet?id=<%= prod.getId() %>+userid=<%= user.getId() %>">Alternar favorito</a></td>
                        <td><a href="PujaNuevaServlet?id=<%= prod.getId() %>">Hacer puja</a></td>
                        
                       </tr>
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>