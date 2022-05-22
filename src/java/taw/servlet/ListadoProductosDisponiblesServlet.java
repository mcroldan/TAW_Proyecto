/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import taw.dao.ProductoFacade;
import taw.dto.ProductoDTO;
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
@WebServlet(name = "ListadoProductosDisponiblesServlet", urlPatterns = {"/ListadoProductosDisponiblesServlet"})
public class ListadoProductosDisponiblesServlet extends HttpServlet {
    @EJB ProductoFacade productoFacade;
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
        List<ProductoDTO> productos;
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        int userid = user.getId();
        String filtroTitulo = (String)request.getParameter("filtroTitulo");
        String filtroMarca = (String)request.getParameter("filtroMarca");
        if(filtroTitulo == null && filtroMarca == null){
            productos = this.productoFacade.findAllSalvoMisProductosYLosAdjudicados(userid);
        }else if(filtroMarca != null){
            productos = this.productoFacade.findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(userid, filtroMarca);
            request.setAttribute("filtroMarca", filtroMarca);
        }else{
            productos = this.productoFacade.findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(userid, filtroTitulo);
            request.setAttribute("filtroTitulo", filtroTitulo);
        }
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/WEB-INF/comprador/listadoProductos.jsp").forward(request, response);
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
