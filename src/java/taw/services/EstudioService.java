/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taw.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import taw.dao.EstudioFacade;

/**
 *
 * @author xdmrg
 */

@Stateless
public class EstudioService {
    
    @EJB EstudioFacade ef;
    
    
}
