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
public class MetaHumanSighting {
    
    
    private int metaHumanSightingId;
    private int metaHumanId;
    private int sightingId;
    
    
    private MetaHuman metaHuman;
    private Sighting sighting;

    public MetaHumanSighting(MetaHuman metaHuman, Sighting sighting) {
        this.metaHuman = metaHuman;
        this.sighting = sighting;
    }

    public MetaHumanSighting() {
    }

    public MetaHuman getMetaHuman() {
        return metaHuman;
    }

    public void setMetaHuman(MetaHuman metaHuman) {
        this.metaHuman = metaHuman;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    public int getMetaHumanSightingId() {
        return metaHumanSightingId;
    }

    public void setMetaHumanSightingId(int metaHumanSightingId) {
        this.metaHumanSightingId = metaHumanSightingId;
    }

    public int getMetaHumanId() {
        return metaHumanId;
    }

    public void setMetaHumanId(int metaHumanId) {
        this.metaHumanId = metaHumanId;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }
    
    
    
    
    
}
