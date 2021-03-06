package taw.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import taw.dto.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Manuel Cristóbal Roldán Gómez
 */
@WebServlet(urlPatterns = {"/BaseTAWServlet"})
public abstract class BaseTAWServlet extends HttpServlet {

    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    @Override
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected boolean comprobarSesion(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO u = (UsuarioDTO)session.getAttribute("usuario");
        if(u == null){
            response.sendRedirect(request.getContextPath());
            return false;
        }        
        return true;
    }

}
