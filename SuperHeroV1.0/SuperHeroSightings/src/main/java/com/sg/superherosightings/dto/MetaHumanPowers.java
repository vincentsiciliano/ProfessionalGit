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
public class MetaHumanPowers {
    
    int metaHumanPowersId;
    int metaHumanId;
    int powerId;
    
    
    private MetaHuman metaHuman;
    private Powers power;
    
    public MetaHumanPowers(){
        
    }
    

    public MetaHuman getMetaHuman() {
        return metaHuman;
    }

    public void setMetaHuman(MetaHuman metaHuman) {
        this.metaHuman = metaHuman;
    }

    public Powers getPower() {
        return power;
    }

    public void setPower(Powers power) {
        this.power = power;
    }

    public MetaHumanPowers(MetaHuman metaHuman, Powers power) {
        this.metaHuman = metaHuman;
        this.power = power;
    }

    public int getMetaHumanPowersId() {
        return metaHumanPowersId;
    }

    public void setMetaHumanPowersId(int metaHumanPowersId) {
        this.metaHumanPowersId = metaHumanPowersId;
    }

    public int getMetaHumanId() {
        return metaHumanId;
    }

    public void setMetaHumanId(int metaHumanId) {
        this.metaHumanId = metaHumanId;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }
    
    
    
    
    

    
}
