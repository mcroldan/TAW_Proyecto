package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    // no funciona
    @Query("select DISTINCT p FROM Producto p LEFT JOIN p.pujasById pu " +
            "WHERE (pu.adjudicado = FALSE) AND (p.vendedor <> :userid)")
    public List<Producto> findAllSalvoMisProductosYLosAdjudicados(@Param("usuarioid") int usuarioid);
    @Query("select DiSTINCT p FROM Producto p LEFT JOIN p.pujasById pu " +
            "WHERE (pu.adjudicado = FALSE OR pu.adjudicado is null) " +
            "AND (p.vendedor <> :vendedorid AND LOWER(p.titulo) LIKE :filtroTitulo)")
    public List<Producto> findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(@Param("vendedorid") int vendedorid, @Param("filtroTitulo") String filtrotitulo);
    @Query("select DiSTINCT p FROM Producto p LEFT JOIN p.pujasById pu " +
            "WHERE (not pu.adjudicado OR pu.adjudicado is null ) " +
            "AND (p.vendedor <> :vendedorid AND LOWER(p.marca) LIKE :filtroMarca)")
    public List<Producto> findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(@Param("vendedoird") int vendedorid, @Param("filtroMarca") String filtroMarca);
    @Query("select DISTINCT p FROM Producto p LEFT JOIN p.favoritosById pf LEFT JOIN p.pujasById pu WHERE (pu.adjudicado = TRUE) OR (pf.usuario = :userid)")

    public List<Producto> findBoughtAndFavorites(@Param("userid") int userid);
    @Query("select max(p.precio) from Puja p where p.producto = :productoid")
    public Double maxPuja(@Param("productoid") int productoid);
    @Query("SELECT p FROM Producto p WHERE p.categoriaByCategoria.nombre like :categoriaFiltro")
    public List<Producto> findByCategoria(@Param("categoriaFiltro") String categoriaFiltro);

}
