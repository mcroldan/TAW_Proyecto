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
import taw.entities.Rol;
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
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
    
}
