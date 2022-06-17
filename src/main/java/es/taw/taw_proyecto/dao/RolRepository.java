package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    // comprobarRol
    public Rol findById();
    // findBynombre
    public Rol findByNombreIgnoreCase(String rolNombre);


}
