/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringProductServiceImpl implements FlooringProductService {
    FlooringProductDao productDao;
    
    public FlooringProductServiceImpl(FlooringProductDao productDao) {
        this.productDao=productDao;
    }
    
    @Override
    public Product getProductObj(String productName){
        return productDao.getProductObj(productName);
    }
    
    @Override
    public void setProducts(){
        
        productDao.addProduct(new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10")));
        productDao.addProduct(new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10")));
        productDao.addProduct(new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15")));
        productDao.addProduct(new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.15")));
        
        productDao.writeProductFile();
        
    }
    
    @Override
    public List<Product> getProductList(){
        return productDao.getProductList();
    }

    
}
