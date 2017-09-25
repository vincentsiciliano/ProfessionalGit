/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringProductService {
    public Product getProductObj(String productName);
    public void setProducts();
    public List<Product> getProductList();
}
