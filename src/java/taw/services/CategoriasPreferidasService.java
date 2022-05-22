/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.CategoriaFacade;
import taw.dao.CategoriasPreferidasFacade;
import taw.dao.UsuarioFacade;
import taw.dto.ProductoDTO;
import taw.entities.CategoriasPreferidas;
import taw.entities.Producto;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class CategoriasPreferidasService {
    @EJB UsuarioFacade usuarioFacade;
    @EJB CategoriaFacade categoriaFacade;
    @EJB CategoriasPreferidasFacade categoriasPreferidasFacade;
    
    public void crearRelacion(Integer userid, Integer categoriaid){
        CategoriasPreferidas nuevaRelacion = new CategoriasPreferidas();
        nuevaRelacion.setUsuario(this.usuarioFacade.find(userid));
        nuevaRelacion.setCategoria(this.categoriaFacade.find(categoriaid));
        
        this.categoriasPreferidasFacade.create(nuevaRelacion);
    }
    public CategoriasPreferidas findByUserAndCategory(int categoriaid, int userid){
        return this.categoriasPreferidasFacade.findByUserAndCategory(categoriaid, userid);
    }
    public void borrarCategoriaPreferida(int categoriaid, int userid){
        CategoriasPreferidas catBorrar = this.findByUserAndCategory(categoriaid, userid);
        if(catBorrar != null){
            this.categoriasPreferidasFacade.remove(catBorrar);
        }
    }

    public List<ProductoDTO> mostrarProductos(int categoriaid, int usuarioid) {
        List<Producto> productos = this.categoriasPreferidasFacade.mostrarProductos(categoriaid, usuarioid);
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
