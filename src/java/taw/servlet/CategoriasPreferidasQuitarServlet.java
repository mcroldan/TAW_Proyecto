/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import taw.dao.CategoriaFacade;
import taw.dao.CategoriasPreferidasFacade;
import taw.dto.UsuarioDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import taw.services.CategoriasPreferidasService;

/**
 *
 * @author Carlos Ortega Chirito
 */
@WebServlet(name = "CategoriasPreferidasQuitarServlet", urlPatterns = {"/CategoriasPreferidasQuitarServlet"})
public class CategoriasPreferidasQuitarServlet extends HttpServlet {
    @EJB CategoriasPreferidasService categoriasPreferidasService;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer categoriaid = Integer.valueOf(request.getParameter("categoriaid"));
        UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
        categoriasPreferidasService.borrarCategoriaPreferida(categoriaid, usuario.getId());
        response.sendRedirect("UsuarioCategoriasPreferidasServlet");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
