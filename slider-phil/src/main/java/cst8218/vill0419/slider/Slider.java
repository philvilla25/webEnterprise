/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.vill0419.slider;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author philo
 */
@Entity
public class Slider implements Serializable {

    private static int X_LIMIT = 0;
    private static int Y_LIMIT = 0;
    private static int SIZE_LIMIT = 100;
    private int maxTravel = 0;
    private int currentTravel;
    private int movementDirection;
    private int TRAVEL_SPEED;
    private int dirChangeCount = 1;
    private int MAX_DIR_CHANGES = 5;
    private int DECAY_RATE = 1;
 
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slider)) {
            return false;
        }
        Slider other = (Slider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.vill0419.slider.Slider[ id=" + id + " ]";
    }
    
       /** 
     * Updates the properties to simulate the passing of one unit of time.
     */
    public void timeStep() {
        if (maxTravel > 0){
            currentTravel += movementDirection * TRAVEL_SPEED;
            if (Math.abs(currentTravel) >= maxTravel){
                movementDirection = -movementDirection;
                dirChangeCount++;
                if (dirChangeCount > MAX_DIR_CHANGES){
                    maxTravel -= DECAY_RATE;
                    dirChangeCount = 0;
                }
            }
        }
    }

    
}
