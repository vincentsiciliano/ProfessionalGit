/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringTaxService {
    public Tax getTaxObj(String state);
    public void setTaxObjects();
    public List<Tax> getTaxList();
}
