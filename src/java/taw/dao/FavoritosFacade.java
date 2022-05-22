/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import taw.dto.FavoritosDTO;
import taw.entities.Favoritos;
import taw.entities.Producto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class FavoritosFacade extends AbstractFacade<Favoritos> {
    @EJB UsuarioFacade usuarioFacade;
    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoritosFacade() {
        super(Favoritos.class);
    }
  
    public FavoritosDTO findByProductoAndUsuario(int productoid, int usuarioid) {
        Query q;
        q = this.em.createQuery("SELECT f from Favoritos f WHERE f.producto.id = :productoid AND f.usuario.id = :usuarioid");
        q.setParameter("productoid", productoid);
        q.setParameter("usuarioid", usuarioid);
        List<Favoritos> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return ent.get(0).toDTO();
        }
    }
    
}
