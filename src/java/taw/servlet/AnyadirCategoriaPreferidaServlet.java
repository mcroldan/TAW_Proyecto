/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.dao.CategoriaFacade;
import taw.dao.CategoriasPreferidasFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Categoria;
import taw.entities.Usuario;

/**
 *
 * @author Carlos Ortega Chirito
 */
@WebServlet(name = "AnyadirCategoriaPreferidaServlet", urlPatterns = {"/AnyadirCategoriaPreferidaServlet"})
public class AnyadirCategoriaPreferidaServlet extends HttpServlet {
    @EJB UsuarioFacade usuarioFacade;
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
        HttpSession session = request.getSession();
        String categoriaid = request.getParameter("categoriaid");
        Usuario user = (Usuario)session.getAttribute("usuario");
        if(categoriaid == null){
            List<Categoria> categoriasDisponibles = categoriaFacade.findCategoriasDisponibles((int)user.getId());
            if(categoriasDisponibles.isEmpty()){
                String strerror = "No hay categorías disponibles para añadir";
                request.setAttribute("error", strerror);
            }else{
                request.setAttribute("categorias", categoriasDisponibles);
            }
            request.getRequestDispatcher("/WEB-INF/comprador/anyadirCategoriaUsuario.jsp").forward(request, response);
        }else{
            Categoria cat = categoriaFacade.find(Integer.parseInt(categoriaid));
            categoriasPreferidasFacade.crearRelacion(user, cat);
            response.sendRedirect(request.getContextPath()+"/CategoriasPreferidasServlet");
        }
        
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
