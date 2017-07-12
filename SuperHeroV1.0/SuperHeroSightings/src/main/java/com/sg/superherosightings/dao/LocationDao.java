/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;


import com.sg.superherosightings.dto.Whereabout;
import java.util.List;


/**
 *
 * @author vincentsiciliano
 */
public interface LocationDao {
    
    public void add(Whereabout location);
    public void edit(Whereabout location);
    public void delete(int id);
    public Whereabout getOne(int id);
    public List<Whereabout> getAll();
    
    
}
