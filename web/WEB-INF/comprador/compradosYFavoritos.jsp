<%-- 
    Document   : compradosYFavoritos
    Created on : 01-may-2022, 15:35:19
    Author     : Carlos Ortega Chirito
--%>

<%@page import="taw.dto.ProductoDTO"%>
<%@page import="taw.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis productos</title>
    </head>
    <body> <jsp:include page="/WEB-INF/comprador/cabeceraComprador.jsp" />
        <h1>Productos comprados y favoritos</h1>
            <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                UsuarioDTO user =  (UsuarioDTO)session.getAttribute("usuario");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(productos == null){
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
            <%      for (ProductoDTO prod: productos) { %>
                        <tr>
                        <td> <%= prod.getVendedor().getUsername()%> </td>
                        <td> <%= prod.getTitulo() %> </td>
                        <td> <img src=<%= prod.getUrlFoto()%> width="" height="" alt="Foto del producto"/>
                        </td>
                        <td> <%= prod.getMarca() %> </td>
                        <td> <%= prod.getCategoria().getNombre() %>
                        <td> <%= df.format(prod.getFechaInicio()) %> </td>
                        <td><form action="AlternarFavoritoServlet" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="hidden" name="desdefavoritos" value="1"/>
                            <input type="submit" value="Alternar favorito" />
                            </form>
                        </td>
                        <td>
                        <form action="PujaNuevaServlet" method="POST">
                            <input type="hidden" name="productoid" value="<%= prod.getId() %>"/>
                            <input type="hidden" name="desdefavoritos" value="1"/>
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
