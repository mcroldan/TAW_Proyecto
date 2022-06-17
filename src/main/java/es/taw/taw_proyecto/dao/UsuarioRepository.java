package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Categoria;
import es.taw.taw_proyecto.entity.Usuario;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // findByName
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE :usuario OR u.apellidos LIKE :usuario")
    public List<Usuario> findByName(@Param("usuario") String nombre);
    // comprobarUsuario
    public Usuario findUsuarioByUsernameAndPassword(String username, String password);
    // getLastId
    @Query("select max(u.id)from Usuario u")
    public Integer getLastId();
}
