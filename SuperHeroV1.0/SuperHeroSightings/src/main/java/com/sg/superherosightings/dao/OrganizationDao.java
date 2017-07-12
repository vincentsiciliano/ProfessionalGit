/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface OrganizationDao {
    
    public void add(Organization organization);
    public void edit(Organization organization);
    public void delete(int id);
    public Organization getOne(int id);
    public List<Organization> getAll();
    
    
    
}
