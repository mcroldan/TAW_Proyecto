/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import taw.dao.FavoritosFacade;
import taw.dao.ProductoFacade;
import taw.dto.FavoritosDTO;
import taw.dto.UsuarioDTO;
import taw.entities.Producto;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import taw.services.FavoritosService;

/**
 *
 * @author Carlos Ortega Chirito
 */
@WebServlet(name = "AlternarFavoritoServlet", urlPatterns = {"/AlternarFavoritoServlet"})
public class AlternarFavoritoServlet extends HttpServlet {
    @EJB FavoritosService favoritosService;
    
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
        int productoid = Integer.parseInt((String)request.getParameter("productoid"));
        UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
        FavoritosDTO f = favoritosService.findByProductoAndUsuario(productoid, (int)usuario.getId());
        String ocurrido; 
        if(f == null){
            favoritosService.crearNuevoFavorito(usuario.getId(), productoid);
        }else{
            favoritosService.borrarFavorito(f.getId());
        }
        if(request.getParameter("desdefavoritos")==null){
            response.sendRedirect(request.getContextPath()+"/ListadoProductosDisponiblesServlet");
        }else{
            response.sendRedirect(request.getContextPath()+"/UsuarioProductosCompradosYFavoritosServlet");
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
