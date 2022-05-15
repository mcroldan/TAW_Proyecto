/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taw.entities.CategoriasPreferidas;

/**
 *
 * @author Carlos
 */
@Stateless
public class CategoriasPreferidasFacade extends AbstractFacade<CategoriasPreferidas> {

    @PersistenceContext(unitName = "TAWBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasPreferidasFacade() {
        super(CategoriasPreferidas.class);
    }
    
}
