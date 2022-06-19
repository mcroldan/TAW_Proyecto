<%-- 
    Document   : categoriasPreferidasMostrarProductos
    Created on : 22-may-2022, 21:23:24
    Author     : Carlos Ortega Chirito
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.taw_proyecto.dto.ProductoDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos de la categoría</title>
    </head>
    <body> <jsp:include page="/WEB-INF/view/comprador/cabeceraComprador.jsp" />
        <h1>Productos de la categoría</h1>
            <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(productos == null){
            %>
                    Esta categoría no tiene productos en este momento.
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
            <%      for (ProductoDTO prod: productos) { %>
                        <tr>
                        <td> <%= prod.getVendedor().getUsername()%> </td>
                        <td> <%= prod.getTitulo() %> </td>
                        <td> <img src=<%= prod.getUrlFoto()%> width="" height="" alt="Foto del producto"/>
                        </td>
                        <td> <%= prod.getMarca() %> </td>
                        <td> <%= prod.getCategoria().getNombre() %>
                        <td> <%= df.format(prod.getFechaInicio()) %> </td>
                        <td><form action="/alternarFavorito" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="submit" value="Alternar favorito" />
                            </form>
                        </td>
                        <td>
                        <form action="/pujas/nueva" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="submit" value="Hacer puja" />
                        </form>
                        
                       </tr>
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>
