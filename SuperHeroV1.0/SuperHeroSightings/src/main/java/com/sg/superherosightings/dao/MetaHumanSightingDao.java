/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;
import com.sg.superherosightings.dto.MetaHumanSighting;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface MetaHumanSightingDao {
    
    public void add(MetaHumanSighting metaHumanSighting);
    public void delete(int id);
    public List<MetaHumanSighting> getAllWithMetaHuman(int id);
    public List<MetaHumanSighting> getAllWithSighting(int id);
    public List<MetaHumanSighting> getAll();
    
}
