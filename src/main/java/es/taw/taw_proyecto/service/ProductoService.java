package es.taw.taw_proyecto.service;

import es.taw.taw_proyecto.dao.ProductoRepository;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.dto.ProductoDTO;
import es.taw.taw_proyecto.entity.Producto;
import es.taw.taw_proyecto.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private ProductoRepository productoRepository;

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductosDisponibles(int usuarioid){
        return this.toDTOList(this.productoRepository.findAllSalvoMisProductosYLosAdjudicados(usuarioid));
    }

    public List<ProductoDTO> listarProductosDisponiblesFiltroMarca(int usuarioid, String filtroMarca){
        return this.toDTOList(this.productoRepository.findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(usuarioid, filtroMarca));
    }

    public List<ProductoDTO> listarProductosDisponiblesFiltroTitulo(int usuarioid, String filtroTitulo){
        return this.toDTOList(this.productoRepository.findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(usuarioid, filtroTitulo));
    }

    public List<ProductoDTO> listarCompradosYFavoritos(int usuarioid){
        return this.toDTOList(this.productoRepository.findBoughtAndFavorites(usuarioid));
    }

    public Double maxPuja(int usuarioid){
        return this.productoRepository.maxPuja(usuarioid);
    }

    public List<Producto> findByCategoria(String filtro){
        return this.productoRepository.findByCategoria(filtro);
    }

    public ProductoDTO findDTO(int id) {
        return this.productoRepository.findById(id).orElse(null).toDTO();
    }

    private List<ProductoDTO> toDTOList(List<Producto> listaProductos){
        List<ProductoDTO> res = new ArrayList<>();
        for(Producto p : listaProductos)
            res.add(p.toDTO());
        return res;
    }
}
