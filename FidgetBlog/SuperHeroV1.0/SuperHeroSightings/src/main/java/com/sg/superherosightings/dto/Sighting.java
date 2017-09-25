/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

import java.time.LocalDate;

/**
 *
 * @author vincentsiciliano
 */
public class Sighting {
    
    private int sightingId;
    private int locationId;
    private Whereabout sightingLocation;
    LocalDate sightingDate;
    private String sightingName;
    
    public Sighting(){
        
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Whereabout getSightingLocation() {
        return sightingLocation;
    }

    public void setSightingLocation(Whereabout sightingLocation) {
        this.sightingLocation = sightingLocation;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public String getSightingName() {
        return sightingName;
    }

    public void setSightingName(String sightingName) {
        this.sightingName = sightingName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    
    
    
    
}
