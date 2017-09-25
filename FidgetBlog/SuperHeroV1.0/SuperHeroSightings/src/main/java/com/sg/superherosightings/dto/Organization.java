/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dto;

/**
 *
 * @author vincentsiciliano
 */
public class Organization {
    
    private int organizationID;
    private int locationID;
    
    private String organizationName;
    private String organizationDescription;
    private Whereabout organizationLocation;
    
    public Organization(){
        
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public Whereabout getOrganizationLocation() {
        return organizationLocation;
    }

    public void setOrganizationLocation(Whereabout organizationLocation) {
        this.organizationLocation = organizationLocation;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
    
    
    
    
    
    
    
}
