/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import taw.dto.PujaDTO;
import taw.entities.Producto;
import taw.entities.Puja;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class PujaFacade extends AbstractFacade<Puja> {
    @EJB UsuarioFacade usuarioFacade;
    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujaFacade() {
        super(Puja.class);
    }

    public List<PujaDTO> findByUserID(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.comprador.id = :comprador");
        q.setParameter("comprador", userid);
        List<Puja> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }
    
    private List<PujaDTO> toDTOList(List<Puja> ent) {
        List<PujaDTO> res = new ArrayList<>();
        for(Puja c : ent){
            res.add(c.toDTO());
        }
        return res;
    }
    
    private List<Puja> findByUserIDAndProductID(int userid, int productoid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.comprador.id = :comprador AND p.producto.id = :productoid");
        q.setParameter("comprador", userid);
        q.setParameter("productoid", productoid);
        return q.getResultList();
    }

    
    
    public void nuevaPuja(String precio, Integer usuarioid, Producto producto) {
        List<Puja> pujaAnt = this.findByUserIDAndProductID(usuarioid, producto.getId());
        if(!pujaAnt.isEmpty()){
            this.remove(pujaAnt.get(0));
        }
        Puja nuevaPuja = new Puja();
        nuevaPuja.setAdjudicado(false);
        nuevaPuja.setComprador(this.usuarioFacade.find(usuarioid));
        nuevaPuja.setFecha(new Date());
        nuevaPuja.setPrecio(Double.parseDouble(precio));
        nuevaPuja.setProducto(producto);
        
        this.create(nuevaPuja);
    }

    public void borrarPuja(Integer id) {
        Puja pujaABorrar = this.find(id);
        this.remove(pujaABorrar);
    }
    
}
