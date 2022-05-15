/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dao;

import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import taw.entities.Estudio;
import taw.entities.Usuario;

/**
 *
 * @author xdmrg
 */
@Stateless
public class EstudioFacade extends AbstractFacade<Estudio> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudioFacade() {
        super(Estudio.class);
    }
    
    /*
        EstudioService
    */
    
    public List<Estudio> findByName(String name){
        Query q = this.getEntityManager().createQuery("SELECT e FROM Estudio e WHERE UPPER(e.nombre) LIKE UPPER(:name)");
        q.setParameter("name", "%" + name + "%");

        List<Estudio> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }        
    }
    
    public Integer getLastId(){
        Query q = this.getEntityManager().createQuery("SELECT max(e.id) FROM Estudio e");
        Integer n = (Integer)q.getSingleResult();
        if(n == null){
            return 1;
        } 
        return n;
    }
    
    public List findAnalistaQueryNoGroup(String tabla, String orden, Integer num, String tipoOrden){
        String queryStr = "SELECT o FROM :tabla o ORDER BY o.:orden :tipoOrden";
        queryStr = queryStr.replace(":tabla", tabla);
        queryStr = queryStr.replace(":orden", orden);
        queryStr = queryStr.replace(":tipoOrden", tipoOrden);
        
        Query q = this.getEntityManager().createQuery(queryStr);
        q.setMaxResults(num);
        
        List lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }  
        
    }
    
    public List findAnalistaQueryGroup(String tabla, String orden, Integer num, String tipoOrden, String grupo){
        if(grupo.equalsIgnoreCase("vendedor")) {grupo = "vendedor.id"; orden = "vendedor.id";}
        if(grupo.equalsIgnoreCase("categoria")) {grupo = "categoria.nombre"; orden = "categoria.nombre";}
        
        String queryStr = "SELECT o.:grupo as Grupo, count(o.id) as Cantidad FROM :tabla o GROUP BY o.:grupo ORDER BY o.:orden :tipoOrden";
        queryStr = queryStr.replace(":tabla", tabla);
        queryStr = queryStr.replace(":orden", grupo);
        queryStr = queryStr.replace(":tipoOrden", tipoOrden);
        queryStr = queryStr.replaceAll(":grupo", grupo);
        System.out.println(queryStr);

        Query q = this.getEntityManager().createQuery(queryStr);
        q.setMaxResults(num);

        List lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }    
    }   
    
    
    public List findAnalistaQueryGroupPercentage(String tabla, String orden, Integer num, String tipoOrden, String grupo, Integer count){
        List<Object[]> lista = findAnalistaQueryGroup(tabla, orden, num, tipoOrden, grupo);
        
        System.out.println(count);
        
        if(lista == null){
            return null;
        }
        
        for(Object[] o : lista){
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);
            o[1] = df.format((((Number)o[1]).floatValue())/count*100) + " %";
        }

        return lista;
    }   
    
}
