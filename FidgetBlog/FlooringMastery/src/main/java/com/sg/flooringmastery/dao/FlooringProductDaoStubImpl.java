/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringProductDaoStubImpl implements FlooringProductDao {
    PrintWriter out;
    Scanner in;

    public FlooringProductDaoStubImpl() {
        
    }
    
    private Map<String, Product> productMap = new HashMap();
    
    @Override
    public void addProduct(Product product){
        productMap.put(product.getProductType(), product);
    }
    
    @Override
    public Product getProductObj(String key){
        return productMap.get(key);
    }
    
    @Override
    public List<Product> getProductList(){
        return new ArrayList<>(productMap.values());
    }
    
    @Override
    public void loadProductFile(){
        try {
            in = new Scanner(
                    new BufferedReader(  
                            new FileReader("TESTproducts.txt")));
        } catch (FileNotFoundException ex) {
           
        }
        
        String line;
        String[] pieces;
        
        while(in.hasNextLine()){
            line = in.nextLine();
            pieces = line.split("::");
            
            Product i = new Product(pieces[0]);
            i.setCostPerSquareFoot(new BigDecimal(pieces[1]));
            i.setLaborCostPerSquareFoot(new BigDecimal(pieces[2]));
     
            
            productMap.put(pieces[0], i);
        }
        in.close();
        
        
        
    }
    
    @Override
    public void writeProductFile(){
        
        List<Product> productList = new ArrayList<>(productMap.values());
        
        try {
            out = new PrintWriter(new FileWriter("TESTproducts.txt"));

        } catch (IOException ex) {
            Logger.getLogger(FlooringOrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Product i : productList){
        
            out.print(i.getProductType() + "::"
                    + i.getCostPerSquareFoot() + "::"
                    + i.getLaborCostPerSquareFoot() + "::"
                );

            out.println();
        }
        out.flush();
        out.close();

        
    }

}
