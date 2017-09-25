/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
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

public class FlooringTaxDaoImpl implements FlooringTaxDao {
    PrintWriter out;
    Scanner in;

    public FlooringTaxDaoImpl() {
        loadTaxFile();
    }
    
    private Map<String, Tax> taxMap = new HashMap();
    
    @Override
    public void addTaxObj(Tax tax){
        taxMap.put(tax.getState(), tax);
    }
    
    @Override
    public Tax getTaxObj(String key){
        return taxMap.get(key);
    }
    
    @Override
    public List<Tax> getTaxList(){
        return new ArrayList<>(taxMap.values());
        
    }
    
    @Override
    public void loadTaxFile(){
        try {
            in = new Scanner(
                    new BufferedReader(  
                            new FileReader("taxdata.txt")));
        } catch (FileNotFoundException ex) {
            
        }
        
        String line;
        String[] pieces;
        
        while(in.hasNextLine()){
            line = in.nextLine();
            pieces = line.split("::");
            
            Tax i = new Tax(pieces[0], new BigDecimal(pieces[1]));
            
            taxMap.put(pieces[0], i);
        }
        in.close();
    }
    
    @Override
    public void writeTaxFile(){
        
        List<Tax> taxList = new ArrayList<>(taxMap.values());
        
        try {
            out = new PrintWriter(new FileWriter("taxdata.txt"));

        } catch (IOException ex) {
            Logger.getLogger(FlooringOrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Tax i : taxList){
        
            out.print(i.getState() + "::"
                    + i.getTaxRate()
                );
            out.println();
        }
        out.flush();
        out.close();
    }
    
    
    

}
