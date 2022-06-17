package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.CategoriasPreferidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasPreferidasRepository extends JpaRepository<CategoriasPreferidas, Integer> {
    public CategoriasPreferidas findByUsuarioAndCategoria(int usuarioid, int categoriaid);
}
