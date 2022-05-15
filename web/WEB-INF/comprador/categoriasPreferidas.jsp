<%-- 
    Document   : categoriasPreferidas
    Created on : 24-abr-2022, 22:23:36
    Author     : Carlos Ortega Chirito
--%>

<%@page import="taw.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="taw.entities.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis categorías preferidas</title>
    </head>
    <body> <jsp:include page="/WEB-INF/comprador/cabeceraComprador.jsp" />
        <h1>Mis categorías</h1>
        <table border="1">
            <% 
                List<Categoria> categorias = (List)request.getAttribute("categorias");
                Usuario user = (Usuario)session.getAttribute("usuario");
                if(categorias.isEmpty()){
            %>
                No tienes categorías preferidas.
            <%
                }else{
            %>
            <tr>
                <th>Nombre</th>
                <th>Quitar categoría</th>                
            </tr>
            <%      for (Categoria categoria: categorias) {
            %>
            <tr>
            <td>
                <%= categoria.getNombre() %>
            </td>
            <td><a href="CategoriaPreferidaQuitarServlet?categoriaid=<%= categoria.getId() %>">Quitar</a></td>
           </tr>
           <%
                    }
                }
            %>
        </table>
        <a href="AnyadirCategoriaPreferidaServlet">Añadir categoría</a>
        </form>
    </body>
</html>
