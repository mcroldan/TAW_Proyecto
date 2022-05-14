<%-- 
    Document   : anyadirCategoriaUsuario
    Created on : 14-may-2022, 20:12:59
    Author     : PC
--%>

<%@page import="taw.entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva categoría preferida</title>
    </head>
    <body>
        <h1>Nueva categoría preferida</h1>
        <form action="AnyadirCategoriaPreferidaServlet" method="POST">
        <% String error = (String)request.getAttribute("error");
            if(error != null){ %>
            <%= error %>
        <%            
        }else{
        %>
            Elegir categoría:
            <select name="categoriaid">
            <% 
                List<Categoria> categorias = (List)request.getAttribute("categorias");
                for(Categoria c : categorias){
            %>
                <option value="<%= c.getId() %>"><%= c.getNombre() %></option>
            <% }%>
            </select>
            <input type="submit" value="Confirmar" />
        <% }%> 
        </form>
    </body>
</html>
