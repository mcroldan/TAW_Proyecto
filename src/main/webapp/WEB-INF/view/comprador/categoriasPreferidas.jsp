<%-- 
    Document   : categoriasPreferidas
    Created on : 24-abr-2022, 22:23:36
    Author     : Carlos Ortega Chirito
--%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.taw_proyecto.dto.CategoriaDTO" %>
<%@ page import="es.taw.taw_proyecto.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis categorías preferidas</title>
    </head>
    <body> <jsp:include page="/WEB-INF/view/comprador/cabeceraComprador.jsp" />
        <h1>Mis categorías</h1>
        <table border="1">
            <% 
                List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
                UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
                if(categorias == null){
            %>
                No tienes categorías preferidas.
            <%
                }else{
            %>
            <tr>
                <th>Nombre</th>
                <th>Quitar categoría</th>
                <th>Ver productos</th>                
            </tr>
            <%      for (CategoriaDTO categoria: categorias) {
            %>
            <tr>
            <td>
                <%= categoria.getNombre() %>
            </td>
            <td><a href="/categoriasPreferidas/<%= categoria.getId() %>/borrar">Quitar</a></td>
            <td><a href="/categoriasPreferidas/<%= categoria.getId() %>/mostrarProductos">Mostrar</a></td>
           </tr>
           <%
                    }
                }
            %>
        </table>
        <a href="/categoriasPreferidas/elegir">Añadir categoría</a>
        </form>
    </body>
</html>
