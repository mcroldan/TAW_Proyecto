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
import taw.entities.Favoritos;
import taw.entities.Producto;
import taw.entities.Usuario;

/**
 *
 * @author Carlos
 */
@Stateless
public class FavoritosFacade extends AbstractFacade<Favoritos> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoritosFacade() {
        super(Favoritos.class);
    }
  
    public Favoritos findByProductoAndUsuario(int productoid, int usuarioid) {
        Query q;
        q = this.em.createQuery("SELECT f from Favoritos f WHERE f.producto.id = :productoid AND f.usuario.id = :usuarioid");
        q.setParameter("productoid", productoid);
        q.setParameter("usuarioid", usuarioid);
        List<Favoritos> res = q.getResultList();
        return(res.isEmpty())?null:res.get(0);
    } 

    public void crearNuevoFavorito(Usuario usuario, Producto p) {
        Favoritos nuevoFavorito = new Favoritos();
        nuevoFavorito.setUsuario(usuario);
        nuevoFavorito.setProducto(p);
        
        this.create(nuevoFavorito);
    }

    public void borrarFavorito(Favoritos f) {
        this.remove(f);
    }
    
}
