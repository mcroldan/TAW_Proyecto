/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import taw.entities.Puja;

/**
 *
 * @author xdmrg
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

    public List<Puja> findByID(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Puja p where p.comprador.id = :comprador");
        q.setParameter("comprador", userid);
        return q.getResultList();
    }
    
}
