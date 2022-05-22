/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.CategoriaFacade;
import taw.dto.CategoriaDTO;
import taw.entities.Categoria;

/**
 *
 * @author Carlos Ortega Chirito
 */
@Stateless
public class CategoriaService {
    @EJB CategoriaFacade categoriaFacade;
    
    public List<CategoriaDTO> findByUserID(int userid){
        return this.categoriaFacade.findByUserID(userid);
    }
    public List<CategoriaDTO> findCategoriasDisponibles(int userid){
        return this.categoriaFacade.findCategoriasDisponibles(userid);
    }
    public CategoriaDTO findBynombre(String N){
        return this.categoriaFacade.findBynombre(N);
    }
    public Categoria findBynombreEntity(String n){
        return this.categoriaFacade.findBynombreEntity(n);
    }
}
