package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Producto;
import es.taw.taw_proyecto.entity.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.DecimalFormat;
import java.util.List;

public class EstudioCustomRepositoryImpl implements EstudioCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findAnalistaQueryNoGroup(String tabla, String orden, Integer num, String tipoOrden) {
        Sort sort;
        if(tipoOrden.equalsIgnoreCase("asc")) sort = Sort.by(Sort.Direction.ASC, orden);
        else sort = Sort.by(Sort.Direction.DESC, orden);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class queryClass;
        if(tabla.equalsIgnoreCase("Usuario")) {
            queryClass = Usuario.class;
        } else {
            queryClass = Producto.class;
        }
        CriteriaQuery query = cb.createQuery(queryClass);
        Root user = query.from(queryClass);

        //Path<String> emailPath = user.get("email");

        /*List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }*/

        query.select(user)
                .orderBy(QueryUtils.toOrders(sort, user, cb));
               // .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).setMaxResults(num)
                .getResultList();
    }

    @Override
    public List findAnalistaQueryGroup(String tabla, String orden, Integer num, String tipoOrden, String grupo){
        if(grupo.equalsIgnoreCase("vendedor")) {grupo = "vendedor.id"; orden = "vendedor.id";}
        if(grupo.equalsIgnoreCase("categoria")) {grupo = "categoria.nombre"; orden = "categoria.nombre";}

        String queryStr = "SELECT o.:grupo as Grupo, count(o.id) as Cantidad FROM :tabla o GROUP BY o.:grupo ORDER BY o.:orden :tipoOrden";
        queryStr = queryStr.replace(":tabla", tabla);
        queryStr = queryStr.replace(":orden", grupo);
        queryStr = queryStr.replace(":tipoOrden", tipoOrden);
        queryStr = queryStr.replaceAll(":grupo", grupo);
        System.out.println(queryStr);

        Query q = this.entityManager.createQuery(queryStr);
        q.setMaxResults(num);

        List lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }
    }

    @Override
    public List findAnalistaQueryGroupPercentage(String tabla, String orden, Integer num, String tipoOrden, String grupo, Long count){
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
