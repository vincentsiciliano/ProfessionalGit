/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHumanOrganization;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface MetaHumanOrganizationDao {
    
    public void add(MetaHumanOrganization metaHumanOrganization);
    public void delete(int id);
    public List<MetaHumanOrganization> getAllWithMetaHuman(int id);
    public List<MetaHumanOrganization> getAllWithOrganization(int id);
    public List<MetaHumanOrganization> getAll();
    
}
