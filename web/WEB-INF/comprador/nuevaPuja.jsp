<%-- 
    Document   : nuevaPuja
    Created on : 15-may-2022, 2:43:06
    Author     : Carlos Ortega Chirito
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="taw.entities.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva puja</title>
    </head>
    <body> <jsp:include page="/WEB-INF/comprador/cabeceraComprador.jsp" />
        <% Producto prod = (Producto)request.getAttribute("producto"); 
           java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy 'a las' HH:mm:ss z");%>
        <h1>Puja por el producto</h1>
        <table border="1"> 
            <tr>
                <th>Vendedor</th>
                <th>Título</th>                
                <th>Imagen</th>
                <th>Marca</th>
                <th>Categoría</th>
                <th>Fecha de publicación</th>
            </tr>
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
        </table> <br/>
        
        <% Double preciopujanterior = (Double)request.getAttribute("preciopujanterior");
           Double minimoapujar; 
           if(preciopujanterior == null || preciopujanterior < prod.getPreciosalida()){
               minimoapujar = prod.getPreciosalida() + 0.1;
           }else{
               minimoapujar = preciopujanterior + 0.1;
           }
               
        %>
           El mínimo a pujar es de <%= minimoapujar %> euros.
           <form action="PujaNuevaServlet" method="POST">
               <input type="hidden" name="productoid" value="<%= request.getParameter("productoid") %>"/>
               <input type="hidden" name="desdefavoritos" value="<%= request.getAttribute("desdefavoritos") %>"/>
               <input type="text" name="precio" value="<%= minimoapujar %>" size="6" />
               <input type="submit" value="Confirmar" />
           </form>
        
    </body>
</html>
