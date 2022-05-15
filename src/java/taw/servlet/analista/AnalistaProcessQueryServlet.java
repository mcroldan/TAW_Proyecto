/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package taw.servlet.analista;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taw.dao.EstudioFacade;
import taw.dao.ProductoFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Estudio;
import taw.entities.Usuario;
import taw.servlet.BaseTAWServlet;

/**
 *
 * @author xdmrg
 */
@WebServlet(name = "AnalistaProcessQueryServlet", urlPatterns = {"/AnalistaProcessQueryServlet"})
public class AnalistaProcessQueryServlet extends BaseTAWServlet {
    
    @EJB EstudioFacade estudioFacade;
    @EJB UsuarioFacade usuarioFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        
        if(super.comprobarSesion(request, response)){
            Integer id = Integer.parseInt(request.getParameter("estudio"));
            Estudio e = this.estudioFacade.find(id);
            Integer count = 1;

            if(e.getTabla().equalsIgnoreCase("Usuario")){
                count = this.usuarioFacade.count();
            } else if(e.getTabla().equalsIgnoreCase("Producto")){
                count = this.productoFacade.count();
            }

            List l;
            if(e.getAgrupar().equalsIgnoreCase("-")){
                l = this.estudioFacade.findAnalistaQueryNoGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden());
            } else {
                if(e.getOperacion().equalsIgnoreCase("Cantidad")) {
                    l = this.estudioFacade.findAnalistaQueryGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar());
                } else {
                    l = this.estudioFacade.findAnalistaQueryGroupPercentage(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar(), count);
                }    
            }

            request.setAttribute("resultado", l);
            request.setAttribute("estudio", e);
            request.getRequestDispatcher("jsp-query.jsp").forward(request, response);
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