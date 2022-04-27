/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taw.dao.RolFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Rol;
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
 */
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    @EJB UsuarioFacade usuarioFacade;
    @EJB RolFacade rolFacade;
    
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
        
        try{
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String codigo_postal = request.getParameter("cp");
            String ciudad = request.getParameter("ciudad");
            String pais = request.getParameter("pais");
            String telefono = request.getParameter("tel");
            String email = request.getParameter("email");
            char sexo = (Character)request.getParameter("sexo").charAt(0);
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
           
            if(ciudad.equals("") || nombre.equals("") || apellidos.equals("") || direccion.equals("") || usuario.equals("") || password.equals("")
                    || codigo_postal.equals("") || pais.equals("") || telefono.equals("") || email.equals("")){
                throw new Exception(telefono);
            }
            int edad = Integer.parseInt(request.getParameter("edad"));
            int rol = Integer.parseInt(request.getParameter("rol"));
            
            Usuario u = new Usuario();
            
            u.setCp(codigo_postal);
            u.setNombre(nombre);
            u.setApellidos(apellidos);
            u.setDireccion(direccion);
            u.setCiudad(ciudad);
            u.setPais(pais);
            u.setTelefono(telefono);
            u.setEmail(email);
            u.setEdad(edad);
            u.setSexo(sexo);
            u.setUsername(usuario);
            u.setPassword(password);
            u.setRol(rolFacade.comprobarRol(rol));

            this.usuarioFacade.create(u);
            

            request.setAttribute("error", "Nuevo usuario creado con éxito. Inicie sesión");
            request.getRequestDispatcher("jsplogin.jsp").forward(request, response);
        }catch(Exception err){
            request.setAttribute("error", "Rellene todos los campos obligatorios correctamente para crear un usuario.");
            request.getRequestDispatcher("jsplogin.jsp").forward(request, response);
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
