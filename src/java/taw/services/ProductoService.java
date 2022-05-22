/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.ProductoFacade;
import taw.dto.ProductoDTO;
import taw.entities.Producto;

/**
 *
 * @author PC
 */
@Stateless
public class ProductoService {
    @EJB ProductoFacade productoFacade;
    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicados(int userid){
        return this.productoFacade.findAllSalvoMisProductosYLosAdjudicados(userid);
    }
    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(int userid, String filtroTitulo){
        return this.productoFacade.findAllSalvoMisProductosYLosAdjudicadosFiltroTitulo(userid, filtroTitulo);
    }
    public List<ProductoDTO> findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(int userid, String filtroTitulo){
        return this.productoFacade.findAllSalvoMisProductosYLosAdjudicadosFiltroMarca(userid, filtroTitulo);
    }
    public List<ProductoDTO> findBoughtAndFavorites(int userid){
        return this.productoFacade.findBoughtAndFavorites(userid);
    }
    public Double maxPuja(Integer productoid){
        return this.productoFacade.maxPuja(productoid);
    }
    
    // AQUÏ HE USADO PRODUCTO PORQUE LA PARTE DEL ADMINISTRADOR NO
    // HA SIDO REFACTORIZADA A SERVICE Y DTO
    public List<Producto> findByCategoria(String filtro){
        return this.productoFacade.findByCategoria(filtro);
    }

    public ProductoDTO findDTO(int id) {
        return this.productoFacade.find(id).toDTO();
    }
}
