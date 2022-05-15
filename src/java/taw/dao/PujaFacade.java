/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.SingularAttribute;
import taw.entities.CategoriasPreferidas;
import taw.entities.Producto;
import taw.entities.Puja;
import taw.entities.Usuario;

/**
 *
 * @author Carlos
 */
@Stateless
public class PujaFacade extends AbstractFacade<Puja> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujaFacade() {
        super(Puja.class);
    }

    public List<Puja> findByUserID(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.comprador.id = :comprador");
        q.setParameter("comprador", userid);
        return q.getResultList();
    }
    private List<Puja> findByUserIDAndProductID(int userid, int productoid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.comprador.id = :comprador AND p.producto.id = :productoid");
        q.setParameter("comprador", userid);
        q.setParameter("productoid", productoid);
        return q.getResultList();
    }

    
    
    public void nuevaPuja(String precio, Usuario usuario, Producto producto) {
        List<Puja> pujaAnt = this.findByUserIDAndProductID(usuario.getId(), producto.getId());
        if(!pujaAnt.isEmpty()){
            this.remove(pujaAnt.get(0));
        }
        Puja nuevaPuja = new Puja();
        nuevaPuja.setAdjudicado(false);
        nuevaPuja.setComprador(usuario);
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
