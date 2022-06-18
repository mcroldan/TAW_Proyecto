package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudioRepository extends JpaRepository<Estudio, Integer>, EstudioCustomRepository {
    @Query("select e from Estudio e where UPPER(e.nombre) like UPPER(CONCAT('%', :nombre, '%')) ")
    public List<Estudio> findEstudioByName(@Param("nombre") String nombre);

    public List<Estudio> findByNombreContaining(String nombre);

    @Query("SELECT max(e.id) FROM Estudio e")
    public Integer getLastId();

}
