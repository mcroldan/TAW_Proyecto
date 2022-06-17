package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.CategoriaRepository;
import es.taw.taw_proyecto.dao.CategoriasPreferidasRepository;
import es.taw.taw_proyecto.dao.UsuarioRepository;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.entity.CategoriasPreferidas;
import es.taw.taw_proyecto.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class CategoriadPreferidasService {
    private UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;
    private CategoriasPreferidasRepository categoriasPreferidasRepository;

    public void crearRelacion(Integer userid, Integer categoriaid){
        CategoriasPreferidas nuevaRelacion = new CategoriasPreferidas();
        nuevaRelacion.setUsuarioByUsuario(this.usuarioRepository.findById(userid).orElse(null));
        nuevaRelacion.setCategoriaByCategoria(this.categoriaRepository.findById(categoriaid).orElse(null));

        this.categoriasPreferidasRepository.save(nuevaRelacion);
    }
    public CategoriasPreferidas findByUserAndCategory(int categoriaid, int userid){
        return this.categoriasPreferidasRepository.findByUsuarioAndCategoria(categoriaid, userid);
    }
    public void borrarCategoriaPreferida(int categoriaid, int userid){
        CategoriasPreferidas catBorrar = this.findByUserAndCategory(categoriaid, userid);
        if(catBorrar != null){
            this.categoriasPreferidasRepository.delete(catBorrar);
        }
    }

    public List<ProductoDTO> mostrarProductos(int categoriaid, int usuarioid) {
        List<Producto> productos = this.categoriaRepository.mostrarProductos(categoriaid, usuarioid);
        if (productos == null) {
            return null;
        }else{
            List<ProductoDTO> res = new ArrayList<>();
            for(Producto p : productos)
                res.add(p.toDTO());
            return res;
        }
    }
}
