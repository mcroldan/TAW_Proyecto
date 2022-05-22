/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import taw.dao.CategoriaFacade;
import taw.dao.CategoriasPreferidasFacade;
import taw.dao.UsuarioFacade;
import taw.dto.CategoriaDTO;
import taw.dto.UsuarioDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Carlos Ortega Chirito
 */
@WebServlet(name = "CategoriasPreferidasAnyadirServlet", urlPatterns = {"/CategoriasPreferidasAnyadirServlet"})
public class CategoriasPreferidasAnyadirServlet extends HttpServlet {
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
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        if(categoriaid == null){
            List<CategoriaDTO> categoriasDisponibles = categoriaFacade.findCategoriasDisponibles((int)user.getId());
            if(categoriasDisponibles == null){
                String strerror = "No hay categorías disponibles para añadir";
                request.setAttribute("error", strerror);
            }else{
                request.setAttribute("categorias", categoriasDisponibles);
            }
            request.getRequestDispatcher("/WEB-INF/comprador/anyadirCategoriaUsuario.jsp").forward(request, response);
        }else{
            categoriasPreferidasFacade.crearRelacion(user.getId(), Integer.valueOf(categoriaid));
            response.sendRedirect(request.getContextPath()+"/UsuarioCategoriasPreferidasServlet");
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
