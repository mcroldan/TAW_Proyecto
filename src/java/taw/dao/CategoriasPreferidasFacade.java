/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taw.entities.CategoriasPreferidas;

/**
 *
 * @author xdmrg
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
