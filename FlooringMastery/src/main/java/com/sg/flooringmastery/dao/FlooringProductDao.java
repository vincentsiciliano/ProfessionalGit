/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringProductDao {
    public void addProduct(Product product);
    public Product getProductObj(String key);
    public List<Product> getProductList();
    public void loadProductFile();
    public void writeProductFile();
    
}
