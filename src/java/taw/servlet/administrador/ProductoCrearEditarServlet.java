/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.dao.CategoriaFacade;
import taw.dao.ProductoFacade;
import taw.dao.UsuarioFacade;
import taw.entities.Categoria;
import taw.entities.Producto;
import taw.entities.Usuario;
import taw.servlet.BaseTAWServlet;

/**
 *
 * @author Alfonso García Gálvez
 */
@WebServlet(name = "ProductoCrearEditarServlet", urlPatterns = {"/ProductoCrearEditarServlet"})
public class ProductoCrearEditarServlet extends BaseTAWServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private CategoriaFacade categoriaFacade;

    @EJB
    private ProductoFacade productoFacade;

    
    
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
                        Usuario vendedor = usuarioFacade.findByName(request.getParameter("usuario")).get(0);
                        String titulo = request.getParameter("titulo");
                        String descripcion = request.getParameter("descripcion");
                        Double preciosalida = Double.parseDouble(request.getParameter("preciosalida"));
                        String urlfoto = request.getParameter("urlfoto");
                        String marca = request.getParameter("marca");
                        Categoria categoria = categoriaFacade.findBynombre(request.getParameter("categoria"));
                        //Date fechainicio=new SimpleDateFormat("dd/MM/yyyy").format(request.getParameter("fechainicio"));
                        
                        


                        Producto p = null;
                        if(id != null){
                            Integer idd = id;
                            p = this.productoFacade.find(idd);
                        }
                        
                        if(p == null) { // El Usuario no existe, se crea
                            p = new Producto();
                            p.setId(this.productoFacade.count()+1);
                            p.setVendedor(vendedor);
                            p.setTitulo(titulo);
                            p.setDescripcion(descripcion);
                            p.setPreciosalida(preciosalida);
                            p.setUrlFoto(urlfoto);
                            p.setMarca(marca);
                            p.setCategoria(categoria);
                            
                            this.productoFacade.create(p);
                        } else { // Se edita el Producto
                            if(p.getVendedor()!= (vendedor)) p.setVendedor(vendedor);
                            if(!p.getTitulo().equalsIgnoreCase(titulo))  p.setTitulo(titulo);
                            if(p.getPreciosalida() != (preciosalida)) p.setPreciosalida(preciosalida);
                            if(!p.getUrlFoto() .equalsIgnoreCase(urlfoto)) p.setUrlFoto(urlfoto);
                            if(!p.getMarca() .equalsIgnoreCase(marca)) p.setMarca(marca);
                            if(p.getCategoria()!= (categoria)) p.setCategoria(categoria);
                            this.productoFacade.edit(p);
                        }
             
            response.sendRedirect("AdministradorProductoServlet");
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
