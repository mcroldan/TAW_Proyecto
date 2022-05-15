<%-- 
    Document   : jsp-crearestudio
    Created on : 5 may 2022, 19:04:25
    Author     : Manuel Cristóbal Roldán Gómez
--%>

<%@page import="taw.dto.EstudioDTO"%>
<%@page import="taw.entities.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String tabla = (String)request.getAttribute("tabla");
            String str = (String)request.getAttribute("str");
            EstudioDTO e = (EstudioDTO)request.getAttribute("estudio");
            if(tabla == null) tabla = "";
            if(str == null) str = "";
            
        %>
        
        <b>Tabla a analizar</b><br/><br/>
        <form method="post" action="AnalistaUpdateGUIServlet">
            <select onchange="this.form.submit()" name="tabla">
                <option disabled selected value> </option>
                <option value="Usuario">Usuario</option>
                <option value="Producto">Producto</option>
            </select>
        </form>
        
  
        <form method="post" action="AnalistaCreateEditServlet">
         <br/><b>Nombre del estudio</b><br/><br/>
        <input type="text" name="name" placeholder="Introduzca nombre del estudio" value="<%= e != null ? e.getNombre() : "" %>"><br/><br/>
        
        <b><%= tabla.toUpperCase() %></b><br/><br/>
        <p style="color:red"><%= str %></p><br/>
        <br/><b>Ordenar según:</b><br/>
        <select name="param">
        <% if(tabla.equalsIgnoreCase("usuario")){ %>
                <option <%= e == null || (e != null && e.getOrdenar().equalsIgnoreCase("id")) ? "selected" : "" %> name="idUs1" value="id">Id</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Nombre") ? "selected" : "" %> name="noUs1" value="nombre">Nombre</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Apellidos") ? "selected" : "" %> name="apUs1" value="apellidos">Apellidos</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("CP") ? "selected" : "" %> name="cpUs1" value="cp">Codigo postal</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Direccion") ? "selected" : "" %> name="dirUs1" value="direccion">Direccion</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Ciudad") ? "selected" : "" %> name="ciUs1" value="ciudad">Ciudad</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Pais") ? "selected" : "" %> name="paUs1" value="pais">Pais</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Telefono") ? "selected" : "" %> name="telUs1" value="telefono">Telefono</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Email") ? "selected" : "" %> name="mailUs1" value="email">Email</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Edad") ? "selected" : "" %> name="edUs1" value="edad">Edad</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Sexo") ? "selected" : "" %> name="seUs1" value="sexo">Sexo</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Username") ? "selected" : "" %> name="usUs1" value="username">Usuario</option>
        <%} else if(tabla.equalsIgnoreCase("producto")){ %>
            
                <option <%= e == null || (e != null && e.getOrdenar().equalsIgnoreCase("id")) ? "selected" : "" %>  name="idPr1" value="id">Id</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Vendedor") ? "selected" : "" %> name="venPr1" value="vendedor">Vendedor</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Titulo") ? "selected" : "" %> name="tiPr1" value="titulo">Titulo</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Descripcion") ? "selected" : "" %> name="desPr1" value="descripcion">Descripcion</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Preciosalida") ? "selected" : "" %> name="prePr1" value="preciosalida">Precio de Salida</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("URL_Foto") ? "selected" : "" %> name="foPr1" value="urlFoto">Foto</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Marca") ? "selected" : "" %> name="maPr1" value="marca">Marca</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Categoria") ? "selected" : "" %> name="catPr1" value="categoria">Categoría</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("Fechainicio") ? "selected" : "" %> name="fePr1" value="fechainicio">Fecha de Inicio</option>
                <option <%= e != null && e.getOrdenar().equalsIgnoreCase("PrecioActual") ? "selected" : "" %> name="preAPrl" value="PrecioActual">Precio actual</option>
        <%  } %>
        </select>
        <b><br/><br/>Agrupar según:</b><br/>
        <select name="group">
        <% if(tabla.equalsIgnoreCase("usuario")){ %>
            
                <option <%= e == null || (e != null && e.getAgrupar().equalsIgnoreCase("-")) ? "selected" : "" %> name="blank" value="-">No agrupar</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Id") ? "selected" : "" %> name="idUs1" value="id">Id</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Nombre") ? "selected" : "" %> name="noUs1" value="nombre">Nombre</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Apellidos") ? "selected" : "" %> name="apUs1" value="apellidos">Apellidos</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("CP") ? "selected" : "" %> name="cpUs1" value="cp">Codigo postal</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Ciudad") ? "selected" : "" %> name="ciUs1" value="ciudad">Ciudad</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Pais") ? "selected" : "" %> name="paUs1" value="pais">Pais</option
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Edad") ? "selected" : "" %> name="edUs1" value="edad">Edad</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Sexo") ? "selected" : "" %> name="seUs1" value="sexo">Sexo</option>
        <%} else if(tabla.equalsIgnoreCase("producto")){ %>
                <option <%= e == null || (e != null && e.getAgrupar().equalsIgnoreCase("-")) ? "selected" : "" %> name="blank" value="-">No agrupar</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Id") ? "selected" : "" %> name="idPr1" value="id">Id</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Vendedor") ? "selected" : "" %> name="venPr1" value="vendedor">Vendedor</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Titulo") ? "selected" : "" %> name="tiPr1" value="titulo">Titulo</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Descripcion") ? "selected" : "" %> name="desPr1" value="descripcion">Descripcion</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Preciosalida") ? "selected" : "" %> name="prePr1" value="preciosalida">Precio de Salida</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("URL_Foto") ? "selected" : "" %> name="foPr1" value="urlFoto">Foto</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Marca") ? "selected" : "" %> name="maPr1" value="marca">Marca</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Categoria") ? "selected" : "" %> name="catPr1" value="categoria">Categoría</option>
                <option <%= e != null && e.getAgrupar().equalsIgnoreCase("Fechainicio") ? "selected" : "" %> name="fePr1" value="fechainicio">Fecha de Inicio</option>
        <%  } %>
        </select>
        
        <b><br/><br/>Operaciones sobre agrupaciones:</b><br/>
        <select name="operations">
            <option <%= e == null || (e != null && e.getOperacion().equalsIgnoreCase("-")) ? "selected" : "" %> value="-">Ninguna</option>
            <option <%= e != null && e.getOperacion().equalsIgnoreCase("Cantidad") ? "selected" : "" %> value="Cantidad">Cantidad de elementos (en número)</option>
            <option <%= e != null && e.getOperacion().equalsIgnoreCase("Porcentaje") ? "selected" : "" %> value="Porcentaje">Cantidad de elementos (en porcentaje)</option>
        </select>
        
        <b><br/><br/>Tipo de orden:</b><br/>
        <input <%= e == null || (e != null && e.getTipoOrden().equalsIgnoreCase("Desc")) ? "checked" : "" %>  type="radio" name="order" value="desc"> Desc.
        <input <%= e != null && e.getTipoOrden().equalsIgnoreCase("Asc") ? "checked" : "" %> type="radio" name="order" value="asc"> Asc.
        
        <b><br/><br/>Numero de elementos:</b><br/>
        <input type="number" max="100" name="numElementos" placeholder="Introduzca número de elementos" value="<%= e != null ? e.getNumElementos() : 10 %>">
        <input type="hidden" name="tabla" value= <%= tabla %>>
        <% if(e != null) { %>
        <input type="hidden" name="id_estudio" value= <%= e.getId() %>>
        <% } %>
        
        <br/><br/><button><%= e == null ? "Crear estudio" : "Editar estudio" %></button>
        </form>
        
        <form method="post" action="AnalistaBackServlet">
            <button>Volver</button>
        </form><br/>
    </body>
</html>
