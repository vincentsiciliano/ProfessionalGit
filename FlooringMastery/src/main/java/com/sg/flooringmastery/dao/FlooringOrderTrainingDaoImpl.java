/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringOrderTrainingDaoImpl implements FlooringOrderDao {
    PrintWriter out;
    Scanner in;  
    private String orderNumber="";
            
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    
    private Map<LocalDate,List<Order>> orderMap = new HashMap();
    private List<String> loadedOrderFiles = new ArrayList<>();
    
    public FlooringOrderTrainingDaoImpl(){
        this.orderNumber=loadCurrentKey();
    }
    
    @Override
    public void addOrder(Order order){
        
//        List<Order> checkNullList = orderMap.get(order.getOrderDate());
//        if(checkNullList!=null){
//            Iterator<Order> i = orderMap.get(order.getOrderDate()).iterator();
//            while(i.hasNext()){
//                Order j = i.next();
//                if(j.getOrderNumber().equals(order.getOrderNumber())){
//                    
//                }
//            }
//        }
        
        order.setOrderNumber(this.orderNumber);
        if(orderMap.get(order.getOrderDate())!=null){

            Iterator<Order> i = orderMap.get(order.getOrderDate()).iterator();
            while(i.hasNext()){
                Order j = i.next();
                if(j.getOrderNumber().equals(order.getOrderNumber())){
                        i.remove();
                    }
            }
        }
            
        if(orderMap.containsKey(order.getOrderDate())){
            orderMap.get(order.getOrderDate()).add(order);
            System.out.println("Added to existing array");
        }else{
            List<Order> tempList = new ArrayList<>();
            tempList.add(order);
            orderMap.put(order.getOrderDate(), tempList);
            System.out.println("Added to new array");
        }
        incrementOrderNumber();
        
    }
    
    @Override
    public void removeOrder(Order order){
        if(orderMap.containsKey(order.getOrderDate())){
            orderMap.get(order.getOrderDate()).remove(order);
            //System.out.println("Removed to existing array");
        }else{
            //System.out.println("An unknown error occurred");
        }
        
    }
    
    @Override
    public Order getOrderFromMap(String orderNumber){
        List<List<Order>> orderList = new ArrayList<>(orderMap.values());
        for(List<Order> i : orderList){
            for(Order j : i){
                if(orderNumber.equals(j.getOrderNumber())){
                    return j;
                }
            }
        }
        return null;    
    }
    
    @Override//issue here
    public List<Order> getOrderList(String date){
        LocalDate mapKeyDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        if(!loadedOrderFiles.contains(date)){
            loadOrderFile(date);
        }
        
        return orderMap.get(mapKeyDate);   
    }
        
    
    
    @Override
    public boolean loadOrderFile(String date){
        
        loadedOrderFiles.add(date);
        String dateFileName = "Orders_" + date + ".csv";
        try {
            in = new Scanner(
                    new BufferedReader(  
                            new FileReader(dateFileName)));
        } catch (FileNotFoundException ex) {
            return false;
        }
        
        String line;
        String[] pieces;
        
        while(in.hasNextLine()){
            line = in.nextLine();
            
            pieces = line.split(",");
            
            Order order = new Order();
            order.setOrderNumber(pieces[0]);
            order.setCustomerName(pieces[1]);
            order.setTaxObj(new Tax(pieces[2], new BigDecimal(pieces[3])));
            order.setProduct(new Product(pieces[4],new BigDecimal(pieces[5]),new BigDecimal(pieces[6])));
            order.setArea(new BigDecimal(pieces[7]));
            order.setLaborCost(new BigDecimal(pieces[8]));
            order.setMaterialCost(new BigDecimal(pieces[9]));
            order.setTaxAmount(new BigDecimal(pieces[10]));
            order.setTotal(new BigDecimal(pieces[11]));
            
            order.setOrderDate(LocalDate.parse(date,DateTimeFormatter.ofPattern("MMddyyyy")));
            
            if(!orderMap.containsKey(order.getOrderDate())){
                orderMap.put(order.getOrderDate(), new ArrayList<>());
            }
            orderMap.get(order.getOrderDate()).add(order);
        }
        in.close();
        
        return true;
    }
    
    @Override
    public void writeOrderFile(Order order, String fileName){
        //System.out.println("Not saved. In training mode.");
    }
    
    @Override
    public void writeOrderListToFile(String fileName){
        //System.out.println("Not saved. In training mode.");
    }

    @Override
    public String loadCurrentKey(){
        
        try {
            in = new Scanner(
                    new BufferedReader(  
                            new FileReader("orderKey.txt")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringOrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String currentKey = in.nextLine();
        
        return currentKey;
    }
    
    @Override
    public void setCurrentDate(String currentDate){

    }
    
    /**
     *
     * @param oldDate
     * @param order
     */
    @Override//removes order and saves updated list to file
    public void adjustPriorSaveFile(LocalDate oldDate, Order order){
        if(orderMap.get(oldDate)!=null){
            Iterator<Order> i = orderMap.get(oldDate).iterator();
            while(i.hasNext()){
                Order j = i.next();
                if(j.getOrderNumber().equals(order.getOrderNumber())){
                        i.remove();
                }
            }
        }
    }
    
    @Override
    public void processPostEdit(Order order){
        addOrder(order);
    }
    
    private void incrementOrderNumber(){
        int numOrder = Integer.parseInt(orderNumber)+1;
        this.orderNumber = Integer.toString(numOrder);
    }
    
    @Override
    public void auditPreEdit(Order order){
        //do nothing
    }
    
   
    

    
    
    
}
