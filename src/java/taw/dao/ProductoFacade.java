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
import taw.entities.Puja;

/**
 *
 * @author Carlos Ortega Chirito
 * @author Alfonso García Gálvez
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> findAllSalvoMisProductosYLosAdjudicados(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor)");
        q.setParameter("vendedor", userid);
        List<Producto> res = q.getResultList();
        return res;
    }
    public List<Producto> findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(int userid, String filtroTitulo) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor AND LOWER(p.titulo) LIKE :filtroTitulo)");
        q.setParameter("vendedor", userid);
        q.setParameter("filtroTitulo", "%"+filtroTitulo.toLowerCase()+"%");
        List<Producto> res = q.getResultList();
        return res;
    }    

    public List<Producto> findBoughtAndFavorites(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DISTINCT p FROM Producto p LEFT JOIN p.favoritosList pf LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = TRUE) OR (pf.usuario.id = :userid)");
        q.setParameter("userid", userid);
        List<Producto> res = q.getResultList();
        return res;
    }
    
    public List<Producto> filtroNombreSimilar(String filtro){
        Query q;
        q = em.createNamedQuery("Producto.findbyTitulo");
        q.setParameter("titulo","%"+filtro+"%");
        return q.getResultList();
    }
    
    public List<Producto> filtroPrecioSalida (String filtro){
        Query q;
        
        q = em.createNamedQuery("SELECT e FROM Producto e WHERE e.precioSalida >= :precioSalida");
        q.setParameter("precioSalida", "%" + filtro + "%");
        return q.getResultList();
    }
    
    public List<Producto> filtroMarca (String filtro){
        Query q;
        
        q = em.createNamedQuery("SELECT e FROM Producto e WHERE e.marca >= :marca");
        q.setParameter("marca", "%" + filtro + "%");
        return q.getResultList();
    }
    
    public List<Producto> filtroFechaInicio (String filtro){
        Query q;
        
        q = em.createNamedQuery("SELECT e FROM Producto e WHERE e.FechaInicio >= :FechaInicio");
        q.setParameter("FechaInicio", "%" + filtro + "%");
        return q.getResultList();
    }

    public Double maxPuja(Integer id) {
        Query q;
        q = this.em.createQuery("SELECT max(p.precio) FROM Puja p");
        List<Double> res = q.getResultList();
        return(res.isEmpty())?null:res.get(0);
    }

    public List<Producto> findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(int userid, String filtroMarca) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor AND LOWER(p.marca) LIKE :filtroMarca)");
        q.setParameter("vendedor", userid);
        q.setParameter("filtroMarca", "%"+filtroMarca.toLowerCase()+"%");
        List<Producto> res = q.getResultList();
        return res;
    }
    
    public List<Producto> findByCategoria(String filtro){
        Query q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categoria.nombre LIKE :producto ");
        q.setParameter("producto", "%" + filtro + "%");

        List<Producto> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }

    }
    
}
