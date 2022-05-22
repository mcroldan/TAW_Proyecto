/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import java.util.List;
import taw.dao.UsuarioFacade;
import taw.dto.UsuarioDTO;
import taw.entities.Usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class UsuarioService{
    @EJB UsuarioFacade usuarioFacade;
    
    public UsuarioDTO comprobarUsuario(String username, String password){
        Usuario usuario = this.usuarioFacade.comprobarUsuario(username, password);
        if(usuario != null){
            return usuario.toDTO();
        }else{
            return null;
        }
    }
    
    public List<Usuario> findByName(String filtro){
        return this.usuarioFacade.findByName(filtro);
    }
    public Integer getLastId(){
        return this.usuarioFacade.getLastId();
    }
            

    public void create(Usuario u) throws Exception{
            this.usuarioFacade.create(u);
    }
}
