<%-- 
    Document   : jsplogged
    Created on : 2 abr 2022, 22:20:45
    Author     : xdmrg
--%>

<%@page import="taw.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Usuario u = (Usuario)session.getAttribute("usuario");
            String[] roles = {"Usuario", "Moderador", "Agua"};
            String rol = u.getRol().getNombre();
        %>
        
        ¡Bienvenido, <%= u.getUsername() %> , tu rol es <%= u.getRol().getNombre() %> y tu ID es <%= u.getId() %>!
        
        <%
          if(rol.equalsIgnoreCase("Analista")){  
        %>
        <form method="get" action="AnalistaServlet" name="goToCrud">
            <button>Estudios estadísticos</button>
        </form>
        
        <form method="post" action="LogoutServlet">
            <button>Cerrar sesión</button>
        </form>
        
        <%
            }
        %>
    </body>
</html>