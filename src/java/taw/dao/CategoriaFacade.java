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
        q = this.getEntityManager().createQuery("select DISTINCT c from Categoria c join c.categoriasPreferidasList cp ON c.id = cp.categoria.id WHERE cp.usuario.id = :userid");
        q.setParameter("userid", userid);
        return q.getResultList();
    }

    public List<Categoria> findCategoriasDisponibles(int userid) {
        Query q, q2;
//        WHERE NOT EXISTS (SELECT ca FROM CategoriasPreferidas ca WHERE ca.usuario.id = :userid)
        q = this.getEntityManager().createQuery("SELECT c FROM Categoria c JOIN c.categoriasPreferidasList ca where ca.usuario.id != :userid");
        q.setParameter("userid", userid);
        List<Categoria> res = q.getResultList();
        q2 = this.getEntityManager().createQuery("SELECT c FROM Categoria c WHERE NOT EXISTS(select ca FROM CategoriasPreferidas ca WHERE ca.categoria = c)");
        res.addAll(q2.getResultList());
        return res;
    }
    
}
