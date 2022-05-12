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
import taw.entities.Estudio;

/**
 *
 * @author xdmrg
 */
@Stateless
public class EstudioFacade extends AbstractFacade<Estudio> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudioFacade() {
        super(Estudio.class);
    }
    
            
    /*
        EstudioService
    */
    
    public List<Estudio> findByName(String name){
        Query q = this.getEntityManager().createQuery("SELECT e FROM Estudio e WHERE e.nombre LIKE :name");
        q.setParameter("name", "%" + name + "%");

        List<Estudio> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }        
    }
    
}



