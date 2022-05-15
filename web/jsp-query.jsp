<%-- 
    Document   : jsp-query
    Created on : 14 may 2022, 18:05:02
    Author     : xdmrg
--%>

<%@page import="taw.dto.ProductoDTO"%>
<%@page import="taw.dto.UsuarioDTO"%>
<%@page import="taw.dto.EstudioDTO"%>
<%@page import="taw.dto.EstudioDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="taw.entities.Producto"%>
<%@page import="taw.entities.Estudio"%>
<%@page import="taw.entities.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            EstudioDTO e = (EstudioDTO)request.getAttribute("estudio");
        %>
        <h1><%= e.getNombre() %></h1>
        
        <form method="post" action="LogoutServlet">
            <button>Cerrar sesión</button>
        </form><br/>
        
        <form method="post" action="AnalistaBackServlet">
            <button>Volver</button>
        </form><br/>
        
    <table border>
        <tr>
            <% if(!e.getAgrupar().equalsIgnoreCase("-")){ %>
                <th><%= e.getAgrupar().toUpperCase() %> </th>
                <th>CANTIDAD</th>
            <% } else if(e.getTabla().equalsIgnoreCase("Usuario")) { %>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>APELLIDOS</th>
                <th>DIRECCION</th>
                <th>CIUDAD</th>
                <th>PAIS</th>
                <th>TELEFONO</th>
                <th>EMAIL</th>
                <th>EDAD</th>
                <th>SEXO</th>
                <th>NOMBRE USUARIO</th>
            <% } else if (e.getTabla().equalsIgnoreCase("Producto")) { %>
                <th>ID</th>
                <th>VENDEDOR</th>
                <th>TITULO</th>
                <th>DESCRIPCION</th>
                <th>PRECIO DE SALIDA</th>
                <th>FOTO</th>
                <th>MARCA</th>
                <th>CATEGORIA</th>
                <th>FECHA DE INICIO</th>
            <% } %>
            
        </tr>
        <% 
            if(request.getAttribute("resultado") != null){
                if(!e.getAgrupar().equalsIgnoreCase("-")){
                    List<Object[]> resultado = (List<Object[]>)request.getAttribute("resultado");
                    for (Object[] o : resultado){
        %>
                    <tr>
                        <td><%= o[0] %></td>
                        <td><%= o[1] %></td>
                    </tr>
        <%
                    }
                } else if(e.getTabla().equalsIgnoreCase("Usuario")) { 
                    List<UsuarioDTO> resultado = (List<UsuarioDTO>)request.getAttribute("resultado");
                    for (UsuarioDTO u : resultado){
        %>
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getNombre()%></td>
                        <td><%= u.getApellidos()%></td>
                        <td><%= u.getDireccion()%></td>
                        <td><%= u.getCiudad()%></td>
                        <td><%= u.getPais()%></td>
                        <td><%= u.getTelefono()%></td>
                        <td><%= u.getEmail()%></td>
                        <td><%= u.getEdad()%></td>
                        <td><%= u.getSexo()%></td>
                        <td><%= u.getUsername()%></td>
                    </tr>
        <%
                    }
                } else if (e.getTabla().equalsIgnoreCase("Producto")) { 
                    List<ProductoDTO> resultado = (List<ProductoDTO>)request.getAttribute("resultado");
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    for (ProductoDTO p : resultado){ %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getVendedor().getNombre() + " " + p.getVendedor().getApellidos() + " (" + p.getVendedor().getId() + ")" %></td>
                        <td><%= p.getTitulo()%></td>
                        <td><%= p.getDescripcion()%></td>
                        <td><%= p.getPreciosalida()%></td>
                        <td><img height="128" src=<%= p.getUrlFoto()%>></td>
                        <td><%= p.getMarca()%></td>
                        <td><%= p.getCategoria().getNombre() %></td>
                        <td><%= format.format(p.getFechaInicio()) %></td>
                    </tr>
              <%    }
            }
        }
        %>
    </table>
    </body>
</html>
