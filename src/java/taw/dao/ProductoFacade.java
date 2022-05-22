/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import taw.dto.ProductoDTO;
import taw.entities.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicados(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor)");
        q.setParameter("vendedor", userid);
        List<Producto> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }
    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(int userid, String filtroTitulo) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor AND LOWER(p.titulo) LIKE :filtroTitulo)");
        q.setParameter("vendedor", userid);
        q.setParameter("filtroTitulo", "%"+filtroTitulo.toLowerCase()+"%");
        List<Producto> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }    

    public List<ProductoDTO> findBoughtAndFavorites(int userid) {
        Query q;
        q = this.getEntityManager().createQuery("select DISTINCT p FROM Producto p LEFT JOIN p.favoritosList pf LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = TRUE) OR (pf.usuario.id = :userid)");
        q.setParameter("userid", userid);
        List<Producto> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
    }

    public Double maxPuja(Integer productoid) {
        Query q;
        q = this.em.createQuery("SELECT max(p.precio) FROM Puja p WHERE p.producto.id = :productoid");
        q.setParameter("productoid", productoid);
        List<Double> res = q.getResultList();
        return(res.isEmpty() || res.get(0) <= 0 )?null:res.get(0);
    }

    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(int userid, String filtroMarca) {
        Query q;
        q = this.getEntityManager().createQuery("select DiSTINCT p FROM Producto p LEFT JOIN p.pujaList pu WHERE (pu.adjudicado = FALSE OR pu.adjudicado = NULL) AND (p.vendedor.id != :vendedor AND LOWER(p.marca) LIKE :filtroMarca)");
        q.setParameter("vendedor", userid);
        q.setParameter("filtroMarca", "%"+filtroMarca.toLowerCase()+"%");
        List<Producto> ent = q.getResultList();
        if(ent.isEmpty()){
            return null;
        }else{
            return this.toDTOList(ent);
        }
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

    private List<ProductoDTO> toDTOList(List<Producto> ent) {
        List<ProductoDTO> res = new ArrayList<>();
        for(Producto p : ent){
            res.add(p.toDTO());
        }
        return res;
    }
    
}
