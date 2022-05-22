/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet.administrador;

import taw.dao.RolFacade;
import taw.dao.UsuarioFacade;
import taw.dto.UsuarioDTO;
import taw.entities.Rol;
import taw.entities.Usuario;
import taw.servlet.BaseTAWServlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Alfonso García Gálvez
 */
@WebServlet(name = "AdministradorServlet", urlPatterns = {"/AdministradorServlet"})
public class AdministradorServlet extends BaseTAWServlet {

    @EJB
    private RolFacade rolFacade;

    @EJB
    private UsuarioFacade usuarioFacade;
    
    

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
        

        String filtro = request.getParameter("filtro");
        List<Usuario> usuarios;
        HttpSession session = request.getSession();
                
        if(super.comprobarSesion(request, response)){
            if(((UsuarioDTO)session.getAttribute("usuario")).getRol().getNombre().equalsIgnoreCase("administrador")){
                if(filtro == null || filtro.equals("")){
                    usuarios = this.usuarioFacade.findAll();
                } else {
                    usuarios = this.usuarioFacade.findByName(filtro);
                }
                
                List<Rol> roles=this.rolFacade.findAll();
                
                request.setAttribute("roles", roles);
                
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("CrudUsuarios.jsp").forward(request, response);
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
