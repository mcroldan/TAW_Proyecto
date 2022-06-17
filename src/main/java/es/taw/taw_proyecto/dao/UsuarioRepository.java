package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Categoria;
import es.taw.taw_proyecto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // findByName
    public List<Usuario> findByNombreContaining(String nombre);
    // comprobarUsuario
    public Usuario findUsuarioByUsernameAndPassword(String username, String password);
    // getLastId
    @Query("select max(u.id)from Usuario u")
    public Integer getLastId();
}
