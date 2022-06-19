<%-- 
    Document   : listadoProductos
    Created on : 30-abr-2022, 22:14:48
    Author     : Carlos Ortega Chirito
--%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.taw_proyecto.dto.ProductoDTO" %>
<%@ page import="es.taw.taw_proyecto.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
    </head>
    <body> <jsp:include page="/WEB-INF/view/comprador/cabeceraComprador.jsp" />
        <h1>Listado de productos</h1>
        <form method="post" action="ListadoProductosDisponiblesServlet">
            Título: <input type="text" 
                           name="filtroTitulo"
                           <% String filtroTitulo = (String)request.getAttribute("filtroTitulo"); 
                           %>
                           value="<%= (filtroTitulo==null)?"":filtroTitulo %>" />
            <input type="submit" value="Filtrar" />
        </form>
        <form method="post" action="/productos/mostrar/">
            Marca: <input type="text" 
                       name="filtroMarca"
                       <% String filtroMarca = (String)request.getAttribute("filtroMarca"); 
                       %>
                       value="<%= (filtroMarca==null)?"":filtroMarca %>" />
        <input type="submit" value="Filtrar" />                
        </form>
            <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                UsuarioDTO user =  (UsuarioDTO)session.getAttribute("usuario");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(productos == null){
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
                        </td>
                       </tr>
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>
