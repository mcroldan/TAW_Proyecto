/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet.administrador;

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
import taw.dao.RolFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Rol;
import taw.entities.Usuario;
import taw.servlet.BaseTAWServlet;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "UsuarioCrearEditarServlet", urlPatterns = {"/UsuarioCrearEditarServlet"})
public class UsuarioCrearEditarServlet extends BaseTAWServlet {

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
        
                 
        
        List<Usuario> usuarios;
        HttpSession session = request.getSession();
                
        if(super.comprobarSesion(request, response)){
            if(((Usuario)session.getAttribute("usuario")).getRol().getNombre().equalsIgnoreCase("administrador")){
                
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String cp = request.getParameter("cp");
                        String nombre = request.getParameter("nombre");
                        String apellidos = request.getParameter("apellidos");
                        String direccion = request.getParameter("direccion");
                        String ciudad = request.getParameter("ciudad");
                        String pais = request.getParameter("pais");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        Integer edad = Integer.parseInt(request.getParameter("edad"));
                        Character sexo = (request.getParameter("sexo").charAt(0));
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        
                        Rol rol = rolFacade.findBynombre(request.getParameter("rol"));


                        Usuario u = null;
                        if(id != null){
                            Integer idd = id;
                            u = this.usuarioFacade.find(idd);
                        }
                        
                        if(u == null) { // El Usuario no existe, se crea
                            u = new Usuario();
                            u.setId(this.usuarioFacade.count()+1);
                            u.setCp(cp);
                            u.setNombre(nombre);
                            u.setApellidos(apellidos);
                            u.setDireccion(direccion);
                            u.setCiudad(ciudad);
                            u.setPais(pais);
                            u.setTelefono(telefono);
                            u.setEmail(email);
                            u.setEdad(edad);
                            u.setSexo(sexo);
                            u.setUsername(username);
                            u.setPassword(password);
                            u.setRol(rol);
                            this.usuarioFacade.create(u);
                        } else { // Se edita el Usuario
                            if(!u.getNombre().equalsIgnoreCase(nombre)) u.setNombre(nombre);
                            if(!u.getApellidos().equalsIgnoreCase(apellidos))  u.setApellidos(apellidos);
                            if(!u.getDireccion() .equalsIgnoreCase(direccion)) u.setDireccion(direccion);
                            if(!u.getCiudad() .equalsIgnoreCase(ciudad)) u.setCiudad(ciudad);
                            if(!u.getPais() .equalsIgnoreCase(pais)) u.setPais(pais);
                            if(!u.getTelefono() .equalsIgnoreCase(telefono)) u.setTelefono(telefono);
                            if(!u.getEmail() .equalsIgnoreCase(email)) u.setEmail(email);
                            if(u.getEdad()!= (edad)) u.setEdad(edad);
                            if(u.getSexo()!= (sexo)) u.setSexo(sexo);
                            if(!u.getUsername().equalsIgnoreCase(username)) u.setUsername(username);
                            if(!u.getPassword() .equalsIgnoreCase(password)) u.setPassword(password);
                            if(u.getRol()!= (rol)) u.setRol(rol);
                            this.usuarioFacade.edit(u);
                        }
             
            response.sendRedirect("AdministradorServlet");
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
