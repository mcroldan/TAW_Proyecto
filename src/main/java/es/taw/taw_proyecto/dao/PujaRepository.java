package es.taw.taw_proyecto.dao;

import es.taw.taw_proyecto.entity.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PujaRepository extends JpaRepository<Puja, Integer> {
    // findByUserId
    public List<Puja> findByUsuarioByComprador(int userid);
    // findByUserIDAndProductID
    public List<Puja> findByCompradorAndProducto(int userid, int productoid);

}
