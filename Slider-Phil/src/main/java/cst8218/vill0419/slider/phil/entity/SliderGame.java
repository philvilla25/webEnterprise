/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.vill0419.slider.phil.entity;

import cst8218.vill0419.slider.ejb.sliderFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Startup;
import java.util.List;

/**
 *
 * @author Phil
 */
@Startup
@Singleton
@LocalBean
public class SliderGame {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB private sliderFacade sliderFacade;
    private static final int CHANGE_RATE = 60;
    public SliderGame(){};   
    
@PostConstruct
public void go() {
    new Thread(new Runnable() {
        public void run() {
            // the game runs indefinitely
            while (true) {
                //update all the sliders and save changes to the database
                List<Slider> sliders = sliderFacade.findAll();
                for (Slider slider : sliders) {
                    slider.timeStep();
                    sliderFacade.edit(slider);
                }
                //sleep while waiting to process the next frame of the animation
                try {
                    // wake up roughly CHANGE_RATE times per second
                    Thread.sleep((long)(1.0/CHANGE_RATE*1000));                               
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }).start();
}


}
