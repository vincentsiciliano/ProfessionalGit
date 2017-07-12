/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringTaxServiceImpl implements FlooringTaxService {
    
    FlooringTaxDao taxDao;
    
    public FlooringTaxServiceImpl(FlooringTaxDao taxDao) {
        this.taxDao=taxDao;
    }
    
    @Override
    public Tax getTaxObj(String state){
        return taxDao.getTaxObj(state);
    }
    
    
    @Override
    public void setTaxObjects(){
        
        taxDao.addTaxObj(new Tax("Ohio", new BigDecimal("6.25")));
        taxDao.addTaxObj(new Tax("Pennsylvania", new BigDecimal("6.75")));
        taxDao.addTaxObj(new Tax("Minnesota", new BigDecimal("5.75")));
        taxDao.addTaxObj(new Tax("Indiana", new BigDecimal("6.00")));
        
        taxDao.writeTaxFile();
        
    }
    
    @Override
    public List<Tax> getTaxList(){
        return taxDao.getTaxList();
    }

    
}
