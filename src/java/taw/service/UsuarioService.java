/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.UsuarioFacade;
import taw.entities.Usuario;

/**
 *
 * @author PC
 */
@Stateless
public class UsuarioService {
    @EJB UsuarioFacade usuarioFacade;
    
    public Usuario comprobarUsuario(String username, String password){
        return this.usuarioFacade.comprobarUsuario(username, password);
    }
    public void create(Usuario u){
        this.usuarioFacade.create(u);
    }
}
