/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package taw.servlet.analista;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taw.dao.EstudioFacade;
import taw.entities.Estudio;

/**
 *
 * @author xdmrg
 */
@WebServlet(name = "AnalistaDuplicateServlet", urlPatterns = {"/AnalistaDuplicateServlet"})
public class AnalistaDuplicateServlet extends HttpServlet {
    @EJB EstudioFacade estudioFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        
        Integer id = Integer.parseInt(request.getParameter("estudio"));
        Estudio e = this.estudioFacade.find(id);
        Estudio e2 = new Estudio();
        e2.setId(this.estudioFacade.count() + 1);
        e2.setNombre(e.getNombre() + " - Copy");
        e2.setTabla(e.getTabla());
        e2.setOrdenar(e.getOrdenar());
        e2.setAgrupar(e.getAgrupar());
        e2.setOperacion(e.getOperacion());
        e2.setTipoOrden(e.getTipoOrden());
        e2.setNumElementos(e.getNumElementos());
        this.estudioFacade.create(e2);
        response.sendRedirect("AnalistaServlet");
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
