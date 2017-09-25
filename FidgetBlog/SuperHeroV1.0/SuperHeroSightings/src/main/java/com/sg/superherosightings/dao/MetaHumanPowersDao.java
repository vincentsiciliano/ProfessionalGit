/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHumanPowers;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface MetaHumanPowersDao {
    
    public void add(MetaHumanPowers metaHumanPowers);
    public void delete(int id);
    public List<MetaHumanPowers> getAllWithMetaHuman(int id);
    public List<MetaHumanPowers> getAllWithPowers(int id);
    public List<MetaHumanPowers> getAll();
    
    
}
