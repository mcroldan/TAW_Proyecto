package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.FavoritosRepository;
import es.taw.taw_proyecto.dao.ProductoRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.FavoritosDTO;
import es.taw.taw_proyecto.entity.Favoritos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritosService {
    private FavoritosRepository favoritosRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public void setFavoritosRepository(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public FavoritosDTO findByIdByProductoAndUsuario(int productoid, int usuarioid){
        return (favoritosRepository.findByProductoAndUsuario(productoid, usuarioid).toDTO());
    }

    public void alternarFavorito(int userid, int productoid){
        FavoritosDTO f = this.findByIdByProductoAndUsuario(userid, productoid);
        if(f == null) this.crearNuevoFavorito(userid,productoid);
        else this.borrarFavorito(f.getId());
    }

    private void crearNuevoFavorito(int userid, int productoid) {
        Favoritos nuevoFavorito = new Favoritos();
        nuevoFavorito.setUsuarioByUsuario(this.usuarioRepository.findById(userid).orElse(null));
        nuevoFavorito.setProductoByProducto(this.productoRepository.findById(productoid).orElse(null));

        this.favoritosRepository.save(nuevoFavorito);
    }

    private void borrarFavorito(Integer favoritoid) {
        this.favoritosRepository.delete(this.favoritosRepository.findById(favoritoid).orElse(null));
    }

    private List<FavoritosDTO> toDTOList(List<Favoritos> listaFavoritos){
        List<FavoritosDTO> res = new ArrayList<>();
        for(Favoritos f : listaFavoritos)
            res.add(f.toDTO());
        return res;
    }
}
