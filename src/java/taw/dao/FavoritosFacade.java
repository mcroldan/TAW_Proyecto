/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taw.entities.Favoritos;

/**
 *
 * @author Carlos
 */
@Stateless
public class FavoritosFacade extends AbstractFacade<Favoritos> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoritosFacade() {
        super(Favoritos.class);
    }
    
}
