package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.FavoritosRepository;
import es.taw.taw_proyecto.dao.ProductoRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.FavoritosDTO;
import es.taw.taw_proyecto.entity.Favoritos;

import java.util.ArrayList;
import java.util.List;

public class FavoritosService {
    private FavoritosRepository favoritosRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;

    public FavoritosDTO findByIdByProductoAndUsuario(int productoid, int usuarioid){
        return (favoritosRepository.findByProductoAndUsuario(productoid, usuarioid).toDTO());
    }

    public void crearNuevoFavorito(int userid, int productoid) {
        Favoritos nuevoFavorito = new Favoritos();
        nuevoFavorito.setUsuarioByUsuario(this.usuarioRepository.findById(userid).orElse(null));
        nuevoFavorito.setProductoByProducto(this.productoRepository.findById(productoid).orElse(null));

        this.favoritosRepository.save(nuevoFavorito);
    }

    public void borrarFavorito(Integer favoritoid) {
        this.favoritosRepository.delete(this.favoritosRepository.findById(favoritoid).orElse(null));
    }

    private List<FavoritosDTO> toDTOList(List<Favoritos> listaFavoritos){
        List<FavoritosDTO> res = new ArrayList<>();
        for(Favoritos f : listaFavoritos)
            res.add(f.toDTO());
        return res;
    }
}
