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
import taw.entities.Categoria;
import taw.entities.CategoriasPreferidas;
import taw.entities.Usuario;
import taw.servlet.CategoriaPreferidaQuitarServlet;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class CategoriasPreferidasFacade extends AbstractFacade<CategoriasPreferidas> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasPreferidasFacade() {
        super(CategoriasPreferidas.class);
    }

    public void crearRelacion(Usuario user, Categoria cat) {
        CategoriasPreferidas nuevaRelacion = new CategoriasPreferidas();
        
        nuevaRelacion.setUsuario(user);
        nuevaRelacion.setCategoria(cat);
        
        this.create(nuevaRelacion);
    }
    public CategoriasPreferidas findByUserAndCategory(Categoria categoria, Usuario usuario){
        Query q;
        q = this.em.createQuery("SELECT cat FROM CategoriasPreferidas cat WHERE cat.categoria = :categ AND cat.usuario = :user");
        q.setParameter("categ", categoria);
        q.setParameter("user", usuario);
        List<CategoriasPreferidas> res = q.getResultList();
        return (res.isEmpty())?null:res.get(0);
        
    }
    public void borrarCategoriaPreferida(Categoria cat, Usuario user) {
        CategoriasPreferidas catBorrar = this.findByUserAndCategory(cat,user);
        if(catBorrar != null){
            this.remove(catBorrar);
        }
    }
}
