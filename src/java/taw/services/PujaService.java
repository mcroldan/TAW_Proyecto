/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.ProductoFacade;
import taw.dao.PujaFacade;
import taw.dao.UsuarioFacade;
import taw.dto.PujaDTO;
import taw.entities.Producto;
import taw.entities.Puja;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class PujaService {
    @EJB PujaFacade pujaFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    
    public List<PujaDTO> findByUserID(int userid){
        return this.pujaFacade.findByUserID(userid);
    }
    public List<Puja> findByUserIDAndProductID(int userid, int productoid){
        return this.pujaFacade.findByUserIDAndProductID(userid, productoid);
    }
    public void nuevaPuja(String precio, int usuarioid, int productoid) {
        List<Puja> pujaAnt = this.pujaFacade.findByUserIDAndProductID(usuarioid, productoid);
        if(!pujaAnt.isEmpty()){
            this.pujaFacade.remove(pujaAnt.get(0));
        }
        Puja nuevaPuja = new Puja();
        nuevaPuja.setAdjudicado(false);
        nuevaPuja.setComprador(this.usuarioFacade.find(usuarioid));
        nuevaPuja.setFecha(new Date());
        nuevaPuja.setPrecio(Double.parseDouble(precio));
        nuevaPuja.setProducto(this.productoFacade.find(productoid));
        
        this.pujaFacade.create(nuevaPuja);
    }

    public void borrarPuja(Integer id) {
        Puja pujaABorrar = this.pujaFacade.find(id);
        this.pujaFacade.remove(pujaABorrar);
    }
}
