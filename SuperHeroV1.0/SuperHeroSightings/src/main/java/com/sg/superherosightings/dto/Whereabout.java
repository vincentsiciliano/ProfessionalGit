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
public class Whereabout {
    
    private int whereaboutId;
    private String whereaboutDescription;
    private double longitude;
    private double latitude;
    private String whereaboutName;
    private String address;
    
    public Whereabout(){
        
    }

    public int getWhereaboutId() {
        return whereaboutId;
    }

    public void setWhereaboutId(int whereaboutId) {
        this.whereaboutId = whereaboutId;
    }

    public String getWhereaboutDescription() {
        return whereaboutDescription;
    }

    public void setWhereaboutDescription(String whereaboutDescription) {
        this.whereaboutDescription = whereaboutDescription;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getWhereaboutName() {
        return whereaboutName;
    }

    public void setWhereaboutName(String whereaboutName) {
        this.whereaboutName = whereaboutName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
         
    
    
    
    
}
