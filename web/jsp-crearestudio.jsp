<%-- 
    Document   : jsp-crearestudio
    Created on : 5 may 2022, 19:04:25
    Author     : xdmrg
--%>

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
            Estudio e = (Estudio)request.getAttribute("estudio");
            if(tabla == null) tabla = "";
            if(str == null) str = "";
            
        %>
        
        <b>Tabla a analizar</b><br/><br/>
        <form method="post" action="AnalistaUpdateGUIServlet">
            <select onchange="this.form.submit()" name="tabla">
                <option disabled selected value> </option>
                <option value="usuario">Usuario</option>
                <option value="producto">Producto</option>
            </select>
        </form>
        
  
        <form method="post" action="AnalistaCreateEditServlet">
         <br/><b>Nombre del estudio</b><br/><br/>
        <input type="text" name="name" placeholder="Introduzca nombre del estudio" value="<%= e != null ? e.getNombre() : "" %>"><br/><br/>
        
        <b><%= tabla.toUpperCase() %></b><br/><br/>
        <%= str %>
        <br/><b>Ordenar según:</b><br/>
        <select name="param">
        <% if(tabla.equalsIgnoreCase("usuario")){ %>
                <option <%= e == null || (e != null && e.getOrdenar()!= 0) ? "" : "selected" %> name="idUs1" value="0">Id</option>
                <option <%= e != null && e.getOrdenar() == 1 ? "selected" : "" %> name="noUs1" value="1">Nombre</option>
                <option <%= e != null && e.getOrdenar() == 2 ? "selected" : "" %> name="apUs1" value="2">Apellidos</option>
                <option <%= e != null && e.getOrdenar() == 3 ? "selected" : "" %> name="cpUs1" value="3">Codigo postal</option>
                <option <%= e != null && e.getOrdenar() == 4 ? "selected" : "" %> name="dirUs1" value="4">Direccion</option>
                <option <%= e != null && e.getOrdenar() == 5 ? "selected" : "" %> name="ciUs1" value="5">Ciudad</option>
                <option <%= e != null && e.getOrdenar() == 6 ? "selected" : "" %> name="paUs1" value="6">Pais</option>
                <option <%= e != null && e.getOrdenar() == 7 ? "selected" : "" %> name="telUs1" value="7">Telefono</option>
                <option <%= e != null && e.getOrdenar() == 8 ? "selected" : "" %> name="mailUs1" value="8">Email</option>
                <option <%= e != null && e.getOrdenar() == 9 ? "selected" : "" %> name="edUs1" value="9">Edad</option>
                <option <%= e != null && e.getOrdenar() == 10 ? "selected" : "" %> name="seUs1" value="10">Sexo</option>
                <option <%= e != null && e.getOrdenar() == 11 ? "selected" : "" %> name="usUs1" value="11">Usuario</option>
        <%} else if(tabla.equalsIgnoreCase("producto")){ %>
            
                <option <%= e == null || (e != null && e.getOrdenar()!= 0) ? "" : "selected" %>  name="idPr1" value="0">Id</option>
                <option <%= e != null && e.getOrdenar() == 1 ? "selected" : "" %> name="venPr1" value="1">Vendedor</option>
                <option <%= e != null && e.getOrdenar() == 2 ? "selected" : "" %> name="tiPr1" value="2">Titulo</option>
                <option <%= e != null && e.getOrdenar() == 3 ? "selected" : "" %> name="desPr1" value="3">Descripcion</option>
                <option <%= e != null && e.getOrdenar() == 4 ? "selected" : "" %> name="prePr1" value="4">Precio de Salida</option>
                <option <%= e != null && e.getOrdenar() == 5 ? "selected" : "" %> name="foPr1" value="5">Foto</option>
                <option <%= e != null && e.getOrdenar() == 6 ? "selected" : "" %> name="maPr1" value="6">Marca</option>
                <option <%= e != null && e.getOrdenar() == 7 ? "selected" : "" %> name="catPr1" value="7">Categoría</option>
                <option <%= e != null && e.getOrdenar() == 8 ? "selected" : "" %> name="fePr1" value="8">Fecha de Inicio</option>
                <option <%= e != null && e.getOrdenar() == 9 ? "selected" : "" %> name="preAPrl" value="9">Precio actual</option>
        <%  } %>
        </select>
        <b><br/><br/>Agrupar según:</b><br/>
        <select name="group">
        <% if(tabla.equalsIgnoreCase("usuario")){ %>
            
                <option <%= e == null || (e != null && e.getAgrupar()!= 0) ? "" : "selected" %> name="blank" value="0">No agrupar</option>
                <option <%= e != null && e.getAgrupar() == 1 ? "selected" : "" %> name="idUs1" value="1">Id</option>
                <option <%= e != null && e.getAgrupar() == 2 ? "selected" : "" %> name="noUs1" value="2">Nombre</option>
                <option <%= e != null && e.getAgrupar() == 3 ? "selected" : "" %> name="apUs1" value="3">Apellidos</option>
                <option <%= e != null && e.getAgrupar() == 4 ? "selected" : "" %> name="cpUs1" value="4">Codigo postal</option>
                <option <%= e != null && e.getAgrupar() == 5 ? "selected" : "" %> name="ciUs1" value="5">Ciudad</option>
                <option <%= e != null && e.getAgrupar() == 6 ? "selected" : "" %> name="paUs1" value="6">Pais</option
                <option <%= e != null && e.getAgrupar() == 7 ? "selected" : "" %> name="edUs1" value="7">Edad</option>
                <option <%= e != null && e.getAgrupar() == 8 ? "selected" : "" %> name="seUs1" value="8">Sexo</option>
        <%} else if(tabla.equalsIgnoreCase("producto")){ %>
                <option <%= e == null || (e != null && e.getAgrupar()!= 0) ? "" : "selected" %> name="blank" value="0">No agrupar</option>
                <option <%= e != null && e.getAgrupar() == 1 ? "selected" : "" %> name="idPr1" value="1">Id</option>
                <option <%= e != null && e.getAgrupar() == 2 ? "selected" : "" %> name="venPr1" value="2">Vendedor</option>
                <option <%= e != null && e.getAgrupar() == 3 ? "selected" : "" %> name="tiPr1" value="3">Titulo</option>
                <option <%= e != null && e.getAgrupar() == 4 ? "selected" : "" %> name="desPr1" value="4">Descripcion</option>
                <option <%= e != null && e.getAgrupar() == 5 ? "selected" : "" %> name="prePr1" value="5">Precio de Salida</option>
                <option <%= e != null && e.getAgrupar() == 6 ? "selected" : "" %> name="foPr1" value="6">Foto</option>
                <option <%= e != null && e.getAgrupar() == 7 ? "selected" : "" %> name="maPr1" value="7">Marca</option>
                <option <%= e != null && e.getAgrupar() == 8 ? "selected" : "" %> name="catPr1" value="8">Categoría</option>
                <option <%= e != null && e.getAgrupar() == 9 ? "selected" : "" %> name="fePr1" value="9">Fecha de Inicio</option>
        <%  } %>
        </select>
        
        <b><br/><br/>Operaciones sobre agrupaciones:</b><br/>
        <select name="operations">
            <option selected value="0">Ninguna</option>
            <option <%= e == null || (e != null && e.getOperacion()!= 1) ? "" : "selected" %> value="1">Cantidad de elementos (en número)</option>
            <option <%= e != null && e.getOperacion()== 2 ? "selected" : "" %> value="2">Cantidad de elementos (en porcentaje)</option>
        </select>
        
        <b><br/><br/>Tipo de orden:</b><br/>
        <input <%= e == null || (e != null && e.getTipoOrden()!= 0) ? "" : "checked" %>  type="radio" name="order" value="0"> Desc.
        <input <%= e != null && e.getTipoOrden()== 1 ? "checked" : "" %> type="radio" name="order" value="1"> Asc.
        
        <b><br/><br/>Numero de elementos:</b><br/>
        <input type="number" max="100" name="numElementos" placeholder="Introduzca número de elementos" value="<%= e != null ? e.getNumElementos() : 10 %>">
        <input type="hidden" name="tabla" value= <%= tabla %>>
        <% if(e != null) { %>
        <input type="hidden" name="id_estudio" value= <%= e.getId() %>>
        <% } %>
        
        <br/><br/><button>Crear estudio</button>
        </form>
    </body>
</html>
