package taw.servlet;

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
import org.jboss.weld.bean.builtin.FacadeInjectionPoint;
import taw.dao.UsuarioFacade;
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends BaseTAWServlet {
    
    @EJB UsuarioFacade usuarioFacade;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("loginInput");
            String password = request.getParameter("passInput");
            
            // CRUD (Only Read by now)
            /*List<Usuario> usuarios = this.usuarioFacade.findAll();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("jsplogged.jsp").forward(request, response);*/
            
            // Login Check
            Usuario u = this.usuarioFacade.comprobarUsuario(username, password);
            if(u == null){
                request.setAttribute("error", "Usuario no encontrado");
                request.getRequestDispatcher("jsplogin.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", u);
                if(u.getRol().getId() == 1){
                    request.getRequestDispatcher("inicioComprador.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("jsplogged.jsp").forward(request, response);
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
