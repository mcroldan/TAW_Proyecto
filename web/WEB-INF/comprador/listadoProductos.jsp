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
    <body> <jsp:include page="/WEB-INF/comprador/cabeceraComprador.jsp" />
        <h1>Listado de productos</h1>
        <form method="post" action="ListadoProductosServlet">
            Título: <input type="text" 
                           name="filtroTitulo"
                           <% String filtroTitulo = (String)request.getAttribute("filtroTitulo"); 
                           %>
                           value="<%= (filtroTitulo==null)?"":filtroTitulo %>" />
            <input type="submit" value="Filtrar" />
        </form>
        <form method="post" action="ListadoProductosServlet">
            Marca: <input type="text" 
                       name="filtroMarca"
                       <% String filtroMarca = (String)request.getAttribute("filtroMarca"); 
                       %>
                       value="<%= (filtroMarca==null)?"":filtroMarca %>" />
        <input type="submit" value="Filtrar" />                
        </form>
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
                        <td><form action="AlternarFavoritoServlet" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="submit" value="Alternar favorito" />
                            </form>
                        </td>
                        <td>
                        <form action="PujaNuevaServlet" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="submit" value="Hacer puja" />
                        </form>
                        </td>
                       </tr>
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>
