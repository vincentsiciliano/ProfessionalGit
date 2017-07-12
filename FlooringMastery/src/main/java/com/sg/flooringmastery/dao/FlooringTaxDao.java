/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringTaxDao {
    public void addTaxObj(Tax tax);
    public Tax getTaxObj(String key);
    public void loadTaxFile();
    public void writeTaxFile();
    public List<Tax> getTaxList();
}
