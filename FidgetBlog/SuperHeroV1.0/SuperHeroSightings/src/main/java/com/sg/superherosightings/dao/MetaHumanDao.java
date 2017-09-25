/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface MetaHumanDao {
    
    public void add(MetaHuman metaHuman);
    public void edit(MetaHuman metaHuman);
    public void delete(int id);
    public MetaHuman getOne(int id);
    public List<MetaHuman> getAll();
    
    
}
