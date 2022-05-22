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

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class CategoriasPreferidasFacade extends AbstractFacade<CategoriasPreferidas> {
    @EJB UsuarioFacade usuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
    
    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasPreferidasFacade() {
        super(CategoriasPreferidas.class);
    }

    public void crearRelacion(Integer userid, Integer categoriaid) {
        CategoriasPreferidas nuevaRelacion = new CategoriasPreferidas();
        
        
        nuevaRelacion.setUsuario(this.usuarioFacade.find(userid));
        nuevaRelacion.setCategoria(this.categoriaFacade.find(categoriaid));
        
        this.create(nuevaRelacion);
    }
    public CategoriasPreferidas findByUserAndCategory(int categoriaid, int userid){
        Query q;
        q = this.em.createQuery("SELECT cat FROM CategoriasPreferidas cat WHERE cat.categoria.id = :categoriaid AND cat.usuario.id = :userid");
        q.setParameter("categoriaid", categoriaid);
        q.setParameter("userid", userid);
        List<CategoriasPreferidas> res = q.getResultList();
        return (res.isEmpty())?null:res.get(0);
        
    }
    public void borrarCategoriaPreferida(int categoriaid, int userid) {
        CategoriasPreferidas catBorrar = this.findByUserAndCategory(categoriaid, userid);
        if(catBorrar != null){
            this.remove(catBorrar);
        }
    }
}
