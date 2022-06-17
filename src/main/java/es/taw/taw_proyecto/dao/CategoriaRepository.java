package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Categoria;
import es.taw.taw_proyecto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // findByUserID
    public List<Categoria> findByUsuariosCategoria_Id(int id);
    // findCategoriasDisponibles
    @Query("SELECT c FROM Categoria c WHERE NOT EXISTS (select u from c.usuariosCategoria u where :userid not in (u.id))")
    public List<Categoria> findCategoriasDisponibles(@Param("userid") int userid);
    // findBynombre && findBynombre
    public Categoria findByNombre(String nombre);


    // mostrarProductos (CategoriaPreferidaFacade)
    @Query("select distinct p from Producto p left join p.pujasById pu where " +
            "(pu.adjudicado = false or pu.adjudicado is null ) " +
            "and (p.vendedor <> :usuarioid and p.categoria = :categoriaid)")
    public List<Producto> mostrarProductos(@Param("usuarioid") int usuarioid, @Param("categoriaid") int categoriaid);
}
