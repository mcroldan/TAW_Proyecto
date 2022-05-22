/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.RolFacade;
import taw.entities.Rol;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class RolService {
    @EJB RolFacade rolFacade;
    
    public Rol comprobarRol(int rol){
        return this.rolFacade.comprobarRol(rol);
    }
    public Rol findBynombre(String N){
        return this.rolFacade.findBynombre(N);
    }
}
