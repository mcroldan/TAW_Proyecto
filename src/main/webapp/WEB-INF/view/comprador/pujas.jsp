<%-- 
    Document   : pujas
    Created on : 24-abr-2022, 22:24:50
    Author     : Carlos Ortega Chirito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.taw_proyecto.dto.PujaDTO" %>
<%@ page import="es.taw.taw_proyecto.dto.ProductoDTO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis pujas</title>
    </head>
    <body> <jsp:include page="/WEB-INF/view/comprador/cabeceraComprador.jsp" />
        <h1>Mis pujas</h1>
            <%
                List<PujaDTO> pujas = (List)request.getAttribute("pujas");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(pujas == null){
            %>
                    No tienes pujas abiertas en este momento.
            <% }else { %>
                    <table border="1"> 
                        <tr>
                            <th>Fecha de inicio</th>
                            <th>Precio</th>                
                            <th>Cancelar puja</th>
                        </tr>
            <%      for (PujaDTO puja: pujas) { %>
                        <tr>
                        <td>
                            <%= df.format(puja.getFecha()) %>
                        </td>
                        <td><%= puja.getPrecio() %></td>
                       <td><a href="/pujas/<%= puja.getId() %>/borrar">Borrar</a></td>
                       </tr>
                       <tr>
                            <th>Vendedor</th>
                            <th>Título</th>                
                            <th>Imagen</th>
                            <th>Marca</th>
                            <th>Categoría</th>
                            <th>Fecha de publicación</th>
                        </tr>
                        <% ProductoDTO prod = puja.getProducto(); %>
                        <tr>
                        <td> <%= prod.getVendedor().getUsername()%> </td>
                        <td> <%= prod.getTitulo() %> </td>
                        <td> <img src=<%= prod.getUrlFoto()%> width="" height="" alt="Foto del producto"/>
                        </td>
                        <td> <%= prod.getMarca() %> </td>
                        <td> <%= prod.getCategoria().getNombre() %>
                        <td> <%= df.format(prod.getFechaInicio()) %> </td>
                        </td>
                       </tr>
                       
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>
