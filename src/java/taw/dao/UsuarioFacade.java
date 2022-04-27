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
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> findByName(String filtro){
        Query q = this.getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :usuario OR u.apellidos LIKE :usuario");
        q.setParameter("usuario", "%" + filtro + "%");

        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }        
        
    }
    
    public Usuario comprobarUsuario(String username, String password){
        Query q = this.getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :username AND u.password LIKE :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        } 
    }
    
    public Integer getLastId(){
        Query q = this.getEntityManager().createQuery("SELECT max(u.id) FROM Usuario u");
        
        List<Integer> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        } 
    }
}
