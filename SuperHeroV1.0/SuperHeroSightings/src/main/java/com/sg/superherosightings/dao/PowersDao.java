/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Powers;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface PowersDao {
    
    public void add(Powers power);
    public void edit(Powers power);
    public void delete(int id);
    public Powers getOne(int id);
    public List<Powers> getAll();
    
    
}
