package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.CategoriaRepository;
import es.taw.taw_proyecto.dto.CategoriaDTO;
import es.taw.taw_proyecto.dto.UsuarioDTO;
import es.taw.taw_proyecto.entity.Categoria;
import es.taw.taw_proyecto.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> findByUserID(int userid){
        return toDTOList(this.categoriaRepository.findByUsuariosCategoria_Id(userid));
    }
    public List<CategoriaDTO> findCategoriasDisponibles(int userid){
        return toDTOList(this.categoriaRepository.findCategoriasDisponibles(userid));
    }
    public CategoriaDTO findBynombre(String N){
        return this.categoriaRepository.findByNombre(N).toDTO();
    }
    public Categoria findBynombreEntity(String n){
        return this.categoriaRepository.findByNombre(n);
    }

    private List<CategoriaDTO> toDTOList(List<Categoria> listaCategorias){
        List<CategoriaDTO> res = new ArrayList<>();
        for(Categoria c : listaCategorias)
            res.add(c.toDTO());
        return res;
    }

}
