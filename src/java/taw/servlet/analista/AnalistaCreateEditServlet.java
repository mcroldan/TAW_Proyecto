package taw.servlet.analista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
import taw.dao.EstudioFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Estudio;
import taw.entities.Usuario;
import taw.servlet.BaseTAWServlet;

/**
 *
 * @author xdmrg
 */
@WebServlet(urlPatterns = {"/AnalistaCreateEditServlet"})
public class AnalistaCreateEditServlet extends BaseTAWServlet {
    
    @EJB UsuarioFacade usuarioFacade;
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
        
                //CRUD (Only Read by now)      
        
        List<Usuario> usuarios;
        HttpSession session = request.getSession();
                
        if(super.comprobarSesion(request, response)){
            if(((Usuario)session.getAttribute("usuario")).getRol().getNombre().equalsIgnoreCase("analista")){
                
                        String id = request.getParameter("id_estudio");
                        String name = request.getParameter("name");
                        String tabla = request.getParameter("tabla");
                        Integer param = Integer.parseInt(request.getParameter("param"));
                        Integer group = Integer.parseInt(request.getParameter("group"));
                        Integer operations = Integer.parseInt(request.getParameter("operations"));
                        Integer order = Integer.parseInt(request.getParameter("order"));
                        Integer numElementos = Integer.parseInt(request.getParameter("numElementos"));

                        Estudio e = null;
                        if(id != null){
                            Integer idd = Integer.parseInt(id);
                            e = this.estudioFacade.find(idd);
                        }
                        
                        if(e == null) { // El estudio no existe, se crea
                            e = new Estudio();
                            e.setId(this.estudioFacade.count()+1);
                            e.setNombre(name);
                            e.setTabla(tabla);
                            e.setOrdenar(param);
                            e.setAgrupar(group);
                            e.setOperacion(operations);
                            e.setTipoOrden(order);
                            e.setNumElementos(numElementos);
                            this.estudioFacade.create(e);
                        } else { // Se edita el estudio
                            if(!e.getNombre().equalsIgnoreCase(name)) e.setNombre(name);
                            if(!e.getTabla().equalsIgnoreCase(tabla)) e.setTabla(tabla);
                            if(e.getOrdenar() != (param)) e.setOrdenar(param);
                            if(e.getAgrupar()!= (group)) e.setAgrupar(group);
                            if(e.getOperacion()!= (operations)) e.setOperacion(operations);
                            if(e.getTipoOrden()!= (order)) e.setTipoOrden(order);
                            if(e.getNumElementos()!= (numElementos)) e.setNumElementos(numElementos);
                            this.estudioFacade.edit(e);
                        }
             
            response.sendRedirect("AnalistaServlet");
            }
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
