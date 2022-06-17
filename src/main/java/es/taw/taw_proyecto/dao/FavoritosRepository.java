package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {
    public Favoritos findByProductoAndUsuario(int productoid, int usuarioid);
}
