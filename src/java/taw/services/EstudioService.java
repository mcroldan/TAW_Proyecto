/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.EstudioFacade;
import taw.dao.ProductoFacade;
import taw.dao.UsuarioFacade;
import taw.dto.EstudioDTO;
import taw.dto.ProductoDTO;
import taw.dto.UsuarioDTO;
import taw.entities.Estudio;
import taw.entities.Producto;
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
 */

@Stateless
public class EstudioService {
    
    @EJB EstudioFacade ef;
    @EJB UsuarioFacade uf;
    @EJB ProductoFacade pf;
    
    private List<EstudioDTO> listaEstudioADTO (List<Estudio> lista) {
        List<EstudioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Estudio estudio : lista) {
                listaDTO.add(estudio.toDTO());
            }
        }
        return listaDTO;
    }
    
    private List<ProductoDTO> listaProductoADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }
    
    private List<UsuarioDTO> listaUsuarioADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<EstudioDTO> listarEstudios (String filtroNombre) {
        List<Estudio> lista = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            lista = this.ef.findAll();        
        } else {
            lista = this.ef.findByName(filtroNombre);
        }
        
        return this.listaEstudioADTO(lista);                
    } 
    
    public EstudioDTO buscarEstudio (Integer id) {
        Estudio estudio = this.ef.find(id);
        return estudio.toDTO();
    }
    
    public void borrarEstudio(Integer id) {
        Estudio estudio = this.ef.find(id);

        this.ef.remove(estudio);        
    }
    
    private void rellenarEstudio(Estudio e, Integer id, String nombre, 
                                String tabla, String ordenar, String agrupar, 
                                String operacion, String tipoOrden, 
                                Integer numElementos){
        e.setId(id);
        e.setNombre(nombre);
        e.setTabla(tabla);
        if(!agrupar.equalsIgnoreCase("-")){
            ordenar = agrupar;
        }
        e.setOrdenar(ordenar);
        e.setAgrupar(agrupar);
        if(!agrupar.equalsIgnoreCase("") && operacion.equalsIgnoreCase("")){
            operacion = "Cantidad";
        }
        e.setOperacion(operacion);
        e.setTipoOrden(tipoOrden);
        e.setNumElementos(numElementos);
    }
    
    public void crearEstudio(Integer id, String nombre, 
                                String tabla, String ordenar, String agrupar, 
                                String operacion, String tipoOrden, 
                                Integer numElementos){
        Estudio e = new Estudio();
        
        if(id < 0){
            id = this.ef.getLastId()+1;
        }
        
        this.rellenarEstudio(e, id, nombre, tabla, ordenar, agrupar, 
                operacion, tipoOrden, numElementos);
        
        this.ef.create(e);
    }
    
    public void modificarEstudio(Integer id, String nombre, 
                                String tabla, String ordenar, String agrupar, 
                                String operacion, String tipoOrden, 
                                Integer numElementos){
        Estudio e = this.ef.find(id);
        
        this.rellenarEstudio(e, id, nombre, tabla, ordenar, 
                agrupar, operacion, tipoOrden, numElementos);
        
        this.ef.edit(e);
    }
    
    public void duplicarEstudio(Integer id){
        Estudio e = this.ef.find(id);
        
        this.crearEstudio(-1, e.getNombre() + " - Copy", e.getTabla(), e.getOrdenar(), 
                e.getAgrupar(), e.getOperacion(), e.getTipoOrden(), e.getNumElementos());
    }
    
    public List getQueryList(EstudioDTO e){
            Integer count = 1;

            if(e.getTabla().equalsIgnoreCase("Usuario")){
                count = this.uf.count();
            } else if(e.getTabla().equalsIgnoreCase("Producto")){
                count = this.pf.count();
            }

            List l;
            if(e.getAgrupar().equalsIgnoreCase("-")){
                l = this.ef.findAnalistaQueryNoGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden());
            } else {
                if(e.getOperacion().equalsIgnoreCase("Cantidad")) {
                    l = this.ef.findAnalistaQueryGroup(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar());
                } else {
                    l = this.ef.findAnalistaQueryGroupPercentage(e.getTabla(), e.getOrdenar(), e.getNumElementos(), e.getTipoOrden(), e.getAgrupar(), count);
                }   
                return l; // En este paso, la lista es de objetos, no de entidades
            }
            if(e.getTabla().equalsIgnoreCase("Usuario")){
                return this.listaUsuarioADTO(l);
            } else {
                return this.listaProductoADTO(l);
            }
       
    }
}
