/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Sighting;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface SightingDao {
    
    public void add(Sighting sighting);
    public void edit(Sighting sighting);
    public void delete(int id);
    public Sighting getOne(int id);
    public List<Sighting> getAll();
    
    
}
