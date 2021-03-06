/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import taw.entities.CategoriasPreferidas;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import taw.entities.Producto;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class CategoriasPreferidasFacade extends AbstractFacade<CategoriasPreferidas> {
    
    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasPreferidasFacade() {
        super(CategoriasPreferidas.class);
    }
    public CategoriasPreferidas findByUserAndCategory(int categoriaid, int userid){
        Query q;
        q = this.em.createQuery("SELECT cat FROM CategoriasPreferidas cat WHERE cat.categoria.id = :categoriaid AND cat.usuario.id = :userid");
        q.setParameter("categoriaid", categoriaid);
        q.setParameter("userid", userid);
        List<CategoriasPreferidas> res = q.getResultList();
        return (res.isEmpty())?null:res.get(0);
        
    }

    public List<Producto> mostrarProductos(int categoriaid, int usuarioid) {
        Query q;
        q = this.em.createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :usuarioid) AND (p.categoria.id = :categoriaid)");
        q.setParameter("categoriaid", categoriaid);
        q.setParameter("usuarioid", usuarioid);
        List<Producto> res = q.getResultList();
        return (res.isEmpty())?null:res;
    }
}
