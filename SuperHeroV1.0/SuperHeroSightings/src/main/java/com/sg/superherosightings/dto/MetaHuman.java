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
public class MetaHuman {
    
    private int MetaHumanID;
    private String MetaHumanName;
    private String MetaHumanSecretName;
    
    public MetaHuman(){
        
    }

    public int getMetaHumanID() {
        return MetaHumanID;
    }

    public void setMetaHumanID(int MetaHumanID) {
        this.MetaHumanID = MetaHumanID;
    }

    public String getMetaHumanName() {
        return MetaHumanName;
    }

    public void setMetaHumanName(String MetaHumanName) {
        this.MetaHumanName = MetaHumanName;
    }

    public String getMetaHumanSecretName() {
        return MetaHumanSecretName;
    }

    public void setMetaHumanSecretName(String MetaHumanSecretName) {
        this.MetaHumanSecretName = MetaHumanSecretName;
    }
    
    
    
}
