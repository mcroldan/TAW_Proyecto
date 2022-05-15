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
import javax.servlet.http.HttpSession;
import taw.dao.ProductoFacade;
import taw.dao.PujaFacade;
import taw.entities.Producto;
import taw.entities.Puja;
import taw.entities.Usuario;

/**
 *
 * @author PC
 */
@WebServlet(name = "PujaNuevaServlet", urlPatterns = {"/PujaNuevaServlet"})
public class PujaNuevaServlet extends HttpServlet {
    @EJB ProductoFacade productoFacade;
    @EJB PujaFacade pujaFacade;
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
        Producto producto = productoFacade.find(Integer.parseInt(productoid));
        String desdefavoritos = (String)request.getParameter("desdefavoritos");
        if(precio == null){
            Double preciomayorPuja = productoFacade.maxPuja(producto.getId());
            request.setAttribute("producto", producto);
            request.setAttribute("preciopujanterior", preciomayorPuja);
            request.setAttribute("desdefavoritos", desdefavoritos);
            request.getRequestDispatcher("/WEB-INF/comprador/nuevaPuja.jsp").forward(request, response);
        }else{
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            pujaFacade.nuevaPuja(precio, usuario, producto);
        }
        if(request.getAttribute("desdefavoritos")==null){
            request.getRequestDispatcher("ListadoProductosServlet").forward(request, response);
        }else{
            request.getRequestDispatcher("ListadoCompradosYFavoritosServlet").forward(request, response);
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
