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
import taw.entities.Categoria;

/**
 *
 * @author xdmrg
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    public List<Categoria> findByUserID(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DISTINCT c from Categoria c join c.categoriasPreferidasList cp WHERE cp.usuario.id = :userid");
        q.setParameter("userid", userid);
        return q.getResultList();
    }
    
    
        public Categoria findBynombre(String N){
        Query q = this.getEntityManager().createQuery("SELECT c FROM categoria c WHERE c.ID LIKE :categoria OR c.nombre LIKE :categoria");
        q.setParameter("categoria", "%" + N + "%");

        List<Categoria> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }        
        
    }
    
}
