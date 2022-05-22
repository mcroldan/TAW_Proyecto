/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.FavoritosFacade;
import taw.dao.ProductoFacade;
import taw.dao.UsuarioFacade;
import taw.dto.FavoritosDTO;
import taw.entities.Favoritos;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class FavoritosService {
    @EJB FavoritosFacade favoritosFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    
    public FavoritosDTO findByProductoAndUsuario(int productoid, int usuarioid){
        return this.favoritosFacade.findByProductoAndUsuario(productoid, usuarioid);
    }
    
    public void crearNuevoFavorito(int userid, int productoid) {
        Favoritos nuevoFavorito = new Favoritos();
        nuevoFavorito.setUsuario(this.usuarioFacade.find(userid));
        nuevoFavorito.setProducto(this.productoFacade.find(productoid));
        
        this.favoritosFacade.create(nuevoFavorito);
    }

    public void borrarFavorito(Integer favoritoid) {
        this.favoritosFacade.remove(this.favoritosFacade.find(favoritoid));
    }
}
