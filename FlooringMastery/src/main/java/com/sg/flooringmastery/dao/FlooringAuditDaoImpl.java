/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringAuditDaoImpl implements FlooringAuditDao {
    private PrintWriter out;
    private final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditToFile(String auditMessage) {
                
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }catch(IOException e){
            
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + auditMessage);
        out.flush();
        
    }
    
}
