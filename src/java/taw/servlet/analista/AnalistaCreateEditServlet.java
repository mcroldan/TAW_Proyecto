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
import taw.dto.EstudioDTO;
import taw.dto.UsuarioDTO;
import taw.entities.Estudio;
import taw.entities.Usuario;
import taw.services.EstudioService;
import taw.servlet.BaseTAWServlet;

/**
 *
 * @author xdmrg
 */
@WebServlet(urlPatterns = {"/AnalistaCreateEditServlet"})
public class AnalistaCreateEditServlet extends BaseTAWServlet {

    @EJB EstudioService es;

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
        
        HttpSession session = request.getSession();
                
        if(super.comprobarSesion(request, response)){
            if(((Usuario)session.getAttribute("usuario")).getRol().getNombre().equalsIgnoreCase("analista")){
                
                try{
                        String id = request.getParameter("id_estudio");
                        String name = request.getParameter("name");
                        String tabla = request.getParameter("tabla");
                        String param = request.getParameter("param");
                        String group = request.getParameter("group");
                        String operations = request.getParameter("operations");
                        String order = request.getParameter("order");
                        Integer numElementos = Integer.parseInt(request.getParameter("numElementos"));
                        
                        if(name.equalsIgnoreCase("")) {throw new Exception(); }

                        EstudioDTO e = null;
                        if(id != null){
                            e = this.es.buscarEstudio(Integer.parseInt(id));
                        }
                        
                        if(e == null) { // El estudio no existe, se crea
                            this.es.crearEstudio(-1, name, tabla, param, group, operations, order, numElementos);
        
                        } else { // Se edita el estudio
                            this.es.modificarEstudio(e.getId(), name, tabla, param, group, operations, order, numElementos);
                            
                        }
             
            response.sendRedirect("AnalistaServlet");
             } catch(Exception e) {
             request.setAttribute("str", "Rellene correctamente todos los campos");
             request.getRequestDispatcher("jsp-crearestudio.jsp").forward(request, response);
            }
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
