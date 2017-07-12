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
public class MetaHumanOrganization {
    
    
    private int metaHumanOrganizationId;
    private int metaHumanId;
    private int organizationId;
    
    private MetaHuman metaHuman;
    private Organization organization;
    
    public MetaHumanOrganization(MetaHuman metaHuman, Organization organization){
        this.metaHuman=metaHuman;
        this.organization=organization;
    }

    public MetaHuman getMetaHuman() {
        return metaHuman;
    }

    public void setMetaHuman(MetaHuman metaHuman) {
        this.metaHuman = metaHuman;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public MetaHumanOrganization() {
    }
    
    
    public int getMetaHumanOrganizationId() {
        return metaHumanOrganizationId;
    }

    public void setMetaHumanOrganizationId(int metaHumanOrganizationId) {
        this.metaHumanOrganizationId = metaHumanOrganizationId;
    }

    public int getMetaHumanId() {
        return metaHumanId;
    }

    public void setMetaHumanId(int metaHumanId) {
        this.metaHumanId = metaHumanId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    
    
    
}
