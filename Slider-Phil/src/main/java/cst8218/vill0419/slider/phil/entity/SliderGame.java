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
 * @author Philogene Villanueva
 */
@Startup
@Singleton
@LocalBean
public class SliderGame {
   
    @EJB
    private sliderFacade sliderFacade;
    private static final int CHANGE_RATE = 60;

    public SliderGame() {
    }

    /**
     * Initializes the game loop that updates all sliders at a fixed rate. This
     * method is annotated with @PostConstruct to indicate that it should be
     * called once all dependency injections have been completed.
     * Student Name: Philogene Villanueva
     * Due Date: 2024-06-20
     * Program/Course/Section:  24S_CST8218 
     */
    @PostConstruct
    public void go() {
        // Start a new thread to run the game loop
        new Thread(new Runnable() {
            public void run() {
                // The game loop runs indefinitely
                while (true) {
                    // Retrieve all sliders from the database
                    List<Slider> sliders = sliderFacade.findAll();

                    // Iterate over each slider
                    for (Slider slider : sliders) {
                        // Update the slider's state
                        slider.timeStep();

                        // Save the changes to the database
                        sliderFacade.edit(slider);
                    }

                    // Sleep to wait for the next frame of the animation
                    try {
                        // Wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long) (1.0 / CHANGE_RATE * 1000));
                    } catch (InterruptedException exception) {
                        // Print the stack trace if an interruption occurs
                        exception.printStackTrace();
                    }
                }
            }
        }).start(); // Start the thread
    }

}
