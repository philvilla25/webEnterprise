/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.vill0419.sliderFacade;

import cst8218.vill0419.slider.Slider;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author philo
 */
@Stateless
@LocalBean
public class SliderFacade extends AbstractFacade<Slider>{
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    public SliderFacade() {
        super(Slider.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
