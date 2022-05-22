/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import taw.dao.ProductoFacade;
import taw.dao.PujaFacade;
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
import taw.dto.ProductoDTO;
import taw.services.ProductoService;
import taw.services.PujaService;

/**
 *
 * @author Carlos Ortega Chirito
 */
@WebServlet(name = "PujaNuevaServlet", urlPatterns = {"/PujaNuevaServlet"})
public class PujaNuevaServlet extends HttpServlet {
    @EJB PujaService pujaService;
    @EJB ProductoService productoService;
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
        String precio = (String)request.getParameter("precio");
        String productoid = (String)request.getParameter("productoid");
        ProductoDTO producto = productoService.findDTO(Integer.parseInt(productoid));
        String desdefavoritos = (String)request.getParameter("desdefavoritos");
        if(precio == null){
            Double preciomayorPuja = productoService.maxPuja(producto.getId());
            request.setAttribute("producto", producto);
            request.setAttribute("preciopujanterior", preciomayorPuja);
            request.setAttribute("desdefavoritos", desdefavoritos);
            request.getRequestDispatcher("/WEB-INF/comprador/nuevaPuja.jsp").forward(request, response);
        }else{
            HttpSession session = request.getSession();
            UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
            if(Double.parseDouble(precio) >= producto.getPreciosalida() + 0.1)
                pujaService.nuevaPuja(precio, usuario.getId(), producto.getId());
        }
        if(request.getAttribute("desdefavoritos")==null){
            request.getRequestDispatcher("ListadoProductosDisponiblesServlet").forward(request, response);
        }else{
            request.getRequestDispatcher("UsuarioProductosCompradosYFavoritosServlet").forward(request, response);
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
