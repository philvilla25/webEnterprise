
package cst8218.vill0419.slider.ejb;

import cst8218.vill0419.slider.phil.entity.Slider;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/*
 * Description: part of the EJB layer where the EntityManager is instantiated to handle communitcation with database persistence
 * Student Name: Philogene Villanueva
 * Due Date: 2024-06-20
 * Program/Course/Section:  24S_CST8218 
 * 
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
    
}
