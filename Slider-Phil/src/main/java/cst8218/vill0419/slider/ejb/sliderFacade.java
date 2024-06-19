/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.vill0419.slider.ejb;

import cst8218.vill0419.slider.phil.entity.Slider;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Phil
 */
@Stateless
public class sliderFacade extends AbstractFacade<Slider>{
    @PersistenceContext(unitName = "my_persistence_slider")
    private EntityManager em;

    public sliderFacade() {
        super(Slider.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
