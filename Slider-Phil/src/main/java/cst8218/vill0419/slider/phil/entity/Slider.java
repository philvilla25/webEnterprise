/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.vill0419.slider.phil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author Phil
 */
@Entity
public class Slider implements Serializable {

    /**
     * @return the currentTravel
     */
    public Integer getCurrentTravel() {
        return currentTravel;
    }

    /**
     * @param currentTravel the currentTravel to set
     */
    public void setCurrentTravel(Integer currentTravel) {
        this.currentTravel = currentTravel;
    }

    /**
     * @return the movementDirection
     */
    public Integer getMovementDirection() {
        return movementDirection;
    }

    /**
     * @param movementDirection the movementDirection to set
     */
    public void setMovementDirection(Integer movementDirection) {
        this.movementDirection = movementDirection;
    }

    /**
     * @return the dirChangeCount
     */
    public Integer getDirChangeCount() {
        return dirChangeCount;
    }

    /**
     * @param dirChangeCount the dirChangeCount to set
     */
    public void setDirChangeCount(Integer dirChangeCount) {
        this.dirChangeCount = dirChangeCount;
    }

  
    public static final int INITIAL_SIZE = 20;
    public static final int TRAVEL_SPEED = 2; // 2 pixels per timeStep
    public static final int MAX_DIR_CHANGES = 5;
    public static final int DECAY_RATE = 1;
    public static final int X_LIMIT = 800;
    public static final int Y_LIMIT = 600;
    public static final int SIZE_LIMIT = 50;
    public static final int MAX_TRAVEL_LIMIT = 100;
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer x;
    private Integer y;
    private Integer sliderSize;
    private Integer maxTravel;
    private Integer currentTravel;
    private Integer movementDirection;
    private Integer dirChangeCount;
    
//    public Slider(){}
//    
//    public Slider(int x, int y) {
//        this.sliderSize = INITIAL_SIZE;
//        this.x = x;
//        this.y = y;
//        this.currentTravel = INITIAL_SIZE;
//        this.maxTravel = INITIAL_SIZE;
//        this.movementDirection = TRAVEL_SPEED;
//        this.dirChangeCount = 0;
//    }

    @Id
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slider)) {
            return false;
        }
        Slider other = (Slider) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.vill0419.slider.phil.entity.Slider[ id=" + getId() + " ]";
    }
    
      /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return sliderSize;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.sliderSize = size;
    }

    /**
     * @return the maxTravel
     */
    public Integer getMaxTravel() {
        return maxTravel;
    }

    /**
     * @param maxTravel the maxTravel to set
     */
    public void setMaxTravel(Integer maxTravel) {
        this.maxTravel = maxTravel;
    }
   
    /** 
     * Updates the properties to simulate the passing of one unit of time.
     */
      public void timeStep() {
        currentTravel += movementDirection;
        if (currentTravel >= maxTravel || currentTravel <= -maxTravel) {
            movementDirection = -movementDirection;
            dirChangeCount++;
            if (dirChangeCount >= MAX_DIR_CHANGES) {
                maxTravel -= DECAY_RATE;
                if (maxTravel < 0) {
                    maxTravel = 0;
                }
                dirChangeCount = 0;
            }
        }
    }

    
}
