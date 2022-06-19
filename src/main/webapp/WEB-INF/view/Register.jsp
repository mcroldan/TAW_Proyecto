<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: xdmrg
  Date: 14/06/2022
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Nuevo usuario</title>
</head>

<body>

<%
  Integer rol = (Integer)request.getAttribute("rol");
  if(rol == null) rol = 1;
  request.setAttribute("rol", rol);
%>
Los campos marcados con * son obligatorios.
<br/><br/>
<%--@elvariable id="usuario" type="es.proyecto.tawspringproyecto.entity.Usuario"--%>
<form:form modelAttribute="usuario" method="post" action="/save">
  <form:hidden path="id"></form:hidden>
  <form:hidden path="rolByRol"></form:hidden>
  *Nombre: <form:input path="nombre"></form:input><br/><br/>
  *Apellidos: <form:input path="apellidos"></form:input><br/><br/><br/>
  *Direccion: <form:input path="direccion"></form:input><br/><br/>
  *Código Postal: <form:input path="cp" maxlength="5"></form:input><br/><br/>
  *Ciudad: <form:input path="ciudad"></form:input><br/><br/>
  *Pais: <form:select path="pais">
    <form:options items="${paises}"></form:options>
  </form:select><br/><br/>
  *Teléfono: <form:input path="telefono" maxlength="12"></form:input><br/><br/>
  *Email: <form:input path="email"></form:input><br/><br/>
  *Sexo: <form:select path="sexo">
    <form:option value="H">Hombre</form:option>
    <form:option value="M">Mujer</form:option>
  </form:select><br/><br/>
  *Edad: <form:input path="edad"></form:input><br/><br/>
  *Usuario: <form:input path="username"></form:input><br/><br/>
  *Contraseña: <form:input path="password"></form:input><br/><br/>
  <form:button>Enviar</form:button><br/><br/>
</form:form>
<!--<form method="post" action="RegisterServlet">
  *Nombre: <input type="text" name="nombre"/><br/><br/>
  *Apellidos: <input type="text" name="apellido"/><br/><br/><br/>
  *Dirección:<input type="text" name="direccion"/><br/><br/>
  *Código Postal: <input type="number" max="99999" name="cp"/><br/><br/>
  *Ciudad: <input type="text" name="ciudad"/><br/><br/>
  *Pais: <select name="pais">
</select><br/><br/>
  *Teléfono: <input type="text" maxlength="12" name="tel"/><br/><br/>

  *Email: <input type="email" name="email"/><br/><br/>
  *Sexo:
  <select name="sexo"><option value="H"> Hombre</option>
    <option value="M"> Mujer</option> </select> <br/><br/>
  *Edad: <input type="number" name="edad"><br/><br><br/>
  *Usuario: <input type="text" name="usuario"><br/><br/>
  *Contraseña: <input type="password" name="password"><br/><br/><br/>
  <input type="hidden" name="rol" value="<%=rol%>"/>
  <button>Enviar</button>
</form> </-->
</body>
</html>
