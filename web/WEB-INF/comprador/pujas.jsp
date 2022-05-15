<%-- 
    Document   : pujas
    Created on : 24-abr-2022, 22:24:50
    Author     : Carlos
--%>

<%@page import="taw.entities.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="taw.entities.Puja"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis pujas</title>
    </head>
    <body> <jsp:include page="/WEB-INF/comprador/cabeceraComprador.jsp" />
        <h1>Mis pujas</h1>
            <%
                List<Puja> pujas = (List)request.getAttribute("pujas");
                java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");
                if(pujas.isEmpty()){
            %>
                    No tienes pujas abiertas en este momento.
            <% }else { %>
                    <table border="1"> 
                        <tr>
                            <th>Fecha de inicio</th>
                            <th>Precio</th>                
                            <th>Cancelar puja</th>
                        </tr>
            <%      for (Puja puja: pujas) { %>
                        <tr>
                        <td>
                            <%= df.format(puja.getFecha()) %>
                        </td>
                        <td><%= puja.getPrecio() %></td>
                       <td><a href="PujaBorrarServlet?pujaid=<%= puja.getId() %>">Borrar</a></td>
                       </tr>
                       <tr>
                            <th>Vendedor</th>
                            <th>Título</th>                
                            <th>Imagen</th>
                            <th>Marca</th>
                            <th>Categoría</th>
                            <th>Fecha de publicación</th>
                        </tr>
                        <% Producto prod = puja.getProducto(); %>
                        <tr>
                        <td> <%= prod.getVendedor().getUsername()%> </td>
                        <td> <%= prod.getTitulo() %> </td>
                        <td> <img src=<%= prod.getUrlFoto()%> width="" height="" alt="Foto del producto"/>
                        </td>
                        <td> <%= prod.getMarca() %> </td>
                        <td> <%= prod.getCategoria().getNombre() %>
                        <td> <%= df.format(prod.getFechainicio()) %> </td>
                        </td>
                       </tr>
                       
           <%
                    } %>
                    </table>
            <% }
            %>
            
        
    </body>
</html>