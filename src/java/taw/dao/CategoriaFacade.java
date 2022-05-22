/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import taw.dto.CategoriaDTO;
import taw.entities.Categoria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ortega Chirito    50%
 * @author Alfonso García Gálvez    50%
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

    public List<CategoriaDTO> findByUserID(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DISTINCT c from Categoria c join c.categoriasPreferidasList cp ON c.id = cp.categoria.id WHERE cp.usuario.id = :userid");
        q.setParameter("userid", userid);
        List<Categoria> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }

    public List<CategoriaDTO> findCategoriasDisponibles(int userid) {
        Query q, q2;
//        WHERE NOT EXISTS (SELECT ca FROM CategoriasPreferidas ca WHERE ca.usuario.id = :userid)
        q = this.getEntityManager().createQuery("SELECT c FROM Categoria c JOIN c.categoriasPreferidasList ca where ca.usuario.id != :userid");
        q.setParameter("userid", userid);
        List<Categoria> ent = q.getResultList();
        q2 = this.getEntityManager().createQuery("SELECT c FROM Categoria c WHERE NOT EXISTS(select ca FROM CategoriasPreferidas ca WHERE ca.categoria = c)");
        ent.addAll(q2.getResultList());
        
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }
    
    public CategoriaDTO findBynombre(String N){
        Query q = this.getEntityManager().createQuery("SELECT c FROM categoria c WHERE c.ID LIKE :categoria OR c.nombre LIKE :categoria");
        q.setParameter("categoria", "%" + N + "%");

        List<Categoria> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0).toDTO();
        }

    }

    private List<CategoriaDTO> toDTOList(List<Categoria> ent) {
        List<CategoriaDTO> res = new ArrayList<>();
        for(Categoria c : ent){
            res.add(c.toDTO());
        }
        return res;
    }

    public Categoria findBynombreEntity(String n) {
        Query q = this.getEntityManager().createQuery("SELECT c FROM categoria c WHERE c.ID LIKE :categoria OR c.nombre LIKE :categoria");
        q.setParameter("categoria", "%" + n + "%");

        List<Categoria> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }
    
}
