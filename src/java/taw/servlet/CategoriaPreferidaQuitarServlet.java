/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taw.dao.CategoriaFacade;
import taw.dao.CategoriasPreferidasFacade;
import taw.entities.Categoria;
import taw.entities.Usuario;

/**
 *
 * @author PC
 */
@WebServlet(name = "CategoriaPreferidaQuitarServlet", urlPatterns = {"/CategoriaPreferidaQuitarServlet"})
public class CategoriaPreferidaQuitarServlet extends HttpServlet {
    @EJB CategoriasPreferidasFacade categoriasPreferidasFacade;
    @EJB CategoriaFacade categoriaFacade;
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
        Categoria catBorrar = categoriaFacade.find(categoriaid);
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        categoriasPreferidasFacade.borrarCategoriaPreferida(catBorrar, usuario);
        response.sendRedirect("CategoriasPreferidasServlet");
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
