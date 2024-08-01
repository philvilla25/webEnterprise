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

/*
 * Description: The Entity Class
 * Student Name: Philogene Villanueva
 * Due Date: 2024-06-20
 * Program/Course/Section:  24S_CST8218 
 * 
 */
@Entity
public class Slider implements Serializable {

    public static final int INITIAL_SIZE = 20;
    public static final int TRAVEL_SPEED = 2; // 2 pixels per timeStep
    public static final int MAX_DIR_CHANGES = 5;
    public static final int DECAY_RATE = 1;
    public static final int X_LIMIT = 400;
    public static final int Y_LIMIT = 400;
    public static final int SIZE_LIMIT = 50;
    public static final int MAX_TRAVEL_LIMIT = 500;
    private static final long serialVersionUID = 1L;
    private static final int CHANGE_RATE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer x;
    private Integer y;
    private Integer sliderSize;
    private Integer maxTravel = MAX_TRAVEL_LIMIT;
    private Integer currentTravel;
    private Integer movementDirection;
    private Integer dirChangeCount = 1;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
        this.maxTravel = MAX_TRAVEL_LIMIT;
    }

    /**
     * Updates the properties to simulate the passing of one unit of time.
     */
    public void timeStep() {
        // Increment the current travel distance by the movement direction
        currentTravel += movementDirection + TRAVEL_SPEED;

        // Check if the current travel distance has reached or exceeded the maximum travel distance in either direction
        if (currentTravel >= maxTravel || currentTravel <= -maxTravel) {
            // Reverse the direction of movement
            movementDirection = -movementDirection;

            // Increment the direction change counter
            dirChangeCount++;

            // Check if the direction has changed the maximum number of allowed times
            if (dirChangeCount >= MAX_DIR_CHANGES) {
                // Reduce the maximum travel distance by the decay rate
                maxTravel -= DECAY_RATE;

                // Ensure the maximum travel distance does not become negative
                if (maxTravel < 0) {
                    maxTravel = 0;
                }

                // Reset the direction change counter
                dirChangeCount = 0;
            }
        }
    }

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

    /**
     * Updates the current Slider with non-null values from the new Slider.
     *
     * @param newSlider the Slider object containing new values
     */
    public void update(Slider newSlider) {
        // If the new Slider's ID is not null, update the current Slider's ID
        if (newSlider.getId() != null) {
            this.id = newSlider.getId();
        }
        // If the new Slider's X coordinate is not null, update the current Slider's X coordinate
        if (newSlider.getX() != null) {
            this.x = newSlider.getX();
        }
        // If the new Slider's Y coordinate is not null, update the current Slider's Y coordinate
        if (newSlider.getY() != null) {
            this.y = newSlider.getY();
        }
        // If the new Slider's current travel distance is not null, update the current Slider's current travel distance
        if (newSlider.getCurrentTravel() != null) {
            this.currentTravel = newSlider.getCurrentTravel();
        }
        if (newSlider.getSize() != null){
           this.sliderSize = newSlider.getSize();
        }

    }
}
