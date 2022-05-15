/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import taw.entities.Producto;

/**
 *
 * @author Carlos
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> findAllButMyProducts(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.vendedor.id != :vendedor");
        q.setParameter("vendedor", userid);
        return q.getResultList();
    }

    public List<Producto> findBoughtAndFavorites(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DISTINCT p from Producto p join p.favoritosList f join p.pujaList pu where f.usuario.id = :userid OR (pu.comprador.id = :userid AND pu.adjudicado = TRUE)");
        q.setParameter("userid", userid);
        return q.getResultList();
    }
    
        public List<Producto> findByCategoria(String filtro){
        Query q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categotia LIKE :producto ");
        q.setParameter("producto", "%" + filtro + "%");

        List<Producto> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }        
        
    }
    
}