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
import taw.entities.Rol;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    public Rol comprobarRol(int rol){
        System.err.println("\n\n\n" + rol + "\n\n\n");
        Query q = this.getEntityManager().createQuery("SELECT r FROM Rol r WHERE r.id = :rol");
        q.setParameter("rol", rol);
        
        List<Rol> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        } 
    }
    
    public Rol findBynombre(String N){
        Query q = this.getEntityManager().createQuery("SELECT r FROM rol r WHERE r.ID LIKE :rol OR r.nombre LIKE :rol");
        q.setParameter("rol", "%" + N + "%");

        List<Rol> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }

    }
        
}
