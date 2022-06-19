<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.taw_proyecto.dto.EstudioDTO" %><%--
  Created by IntelliJ IDEA.
  User: xdmrg
  Date: 14/06/2022
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Estudio</title>
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
<form method="post" action="/analista/create">
    <select onchange="this.form.submit()" name="tabla">
        <option disabled selected value> </option>
        <option value="Usuario">Usuario</option>
        <option value="Producto">Producto</option>
    </select>
</form>

<% if(e != null) { %>
<%--@elvariable id="estudio" type="es.proyecto.tawspringproyecto.dto.EstudioDTO"--%>
<form:form method="post" action="/analista/save" modelAttribute="estudio">
    <br/><b>Nombre del estudio</b><br/><br/>
    <form:input path="nombre"></form:input><br/><br/>

    <p style="color:red"><%= str %></p><br/>

    <br/><b>Ordenar según:</b><br/>
    <form:select path="ordenar">
        <form:options items="${params}"/>
    </form:select>

    <b><br/><br/>Agrupar según:</b><br/>
    <form:select path="agrupar">
        <form:option value="-" label="No agrupar"></form:option>
        <form:options items="${params}"></form:options>
    </form:select>

    <b><br/><br/>Operaciones sobre agrupaciones:</b><br/>
    <form:select path="operacion">
        <form:option value="-">Ninguna</form:option>
        <form:option value="Cantidad">Cantidad de elementos (en número)</form:option>
        <form:option value="Porcentaje">Cantidad de elementos (en porcentaje)</form:option>
    </form:select>

    <b><br/><br/>Tipo de orden:</b><br/>
    <form:radiobutton path="tipoOrden" value="desc"></form:radiobutton> Desc.
    <form:radiobutton path="tipoOrden" value="asc"></form:radiobutton> Asc.

    <b><br/><br/>Numero de elementos:</b><br/>
    <form:input maxlength="2" path="numElementos"></form:input><br/><br/>
    <form:hidden path="tabla"></form:hidden>
    <form:hidden path="id"></form:hidden>

    <form:button>Enviar</form:button>
</form:form>
<% } %>
</body>
</html>
