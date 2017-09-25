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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FlooringOrderDaoStubImpl implements FlooringOrderDao {
    PrintWriter out;
    Scanner in;      
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    private String currentDate;
    
    private Map<String,Order> orderMap = new HashMap();
    
    /**
     *
     * @param order
     * @
     */
    @Override
    public void addOrder(Order order) {
        order.setOrderNumber(loadCurrentKey());
        orderMap.put(order.getOrderNumber(), order);
        String fileName = createSaveFileName(order);
        writeOrderFile(order, fileName);
        writeCurrentKey();
    }
    
    @Override
    public void removeOrder(Order order) {
        String fileName = createSaveFileName(order);
        orderMap.remove(order.getOrderNumber());
        writeOrderListToFile(fileName);
        clearMap();
        
    }
    
    @Override
    public Order getOrderFromMap(String orderNumber){
        return orderMap.get(orderNumber);
    }
    
    @Override
    public List<Order> getOrderList(String date){
        orderMap.clear();
        if(loadOrderFile(date)){
            return new ArrayList<>(orderMap.values());
        }else{
            return null;
        }
    }
    
    @Override
    public boolean loadOrderFile(String date){
        String dateFileName = "TESTOrders_" + date + ".csv";
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
            
            orderMap.put(pieces[0], order);
        }
        in.close();
        
        return true;
        
    }
    
    @Override
    public void writeOrderFile(Order order, String date) {
        String fileName = "TESTOrders_" + date + ".csv";
        try {
            out = new PrintWriter(new FileWriter(fileName, true));

        } catch (IOException ex) {
            System.out.println("Unable to write to Order save file");
        }
        
        orderMap.clear();
        loadOrderFile(createSaveFileName(order));
        
        if(!orderMap.containsKey(order.getOrderNumber())){
            orderMap.remove(order.getOrderNumber());
        
            out.print(order.getOrderNumber() + ","
                + order.getCustomerName().replace(",", "\\,") + ","
                //+ order.getOrderDate().format(formatter) + ","
                + order.getTaxObj().getState().replace(",", "\\,") + ","
                + order.getTaxObj().getTaxRate() + ","
                + order.getProduct().getProductType().replace(",", "\\,") + ","
                + order.getProduct().getCostPerSquareFoot() + ","
                + order.getProduct().getLaborCostPerSquareFoot() + "," 
                + order.getArea() + ","
                + order.getLaborCost() + ","
                + order.getMaterialCost() + ","
                + order.getTaxAmount() + ","
                + order.getTotal()
                );
            out.println();

            out.flush();
            out.close();
        }
    }
    
    @Override
    public void writeOrderListToFile(String date) {
        
        String fileName = "TESTOrders_" + date + ".csv";
        try {
            out = new PrintWriter(new FileWriter(fileName));

        } catch (IOException ex) {
            System.out.println("Unable to write to Order save file");
        }
        
        List<Order> orderList = new ArrayList<>(orderMap.values());
        
        for(Order i : orderList){
            
            out.print(i.getOrderNumber() + ","
                + i.getCustomerName() + ","
                //+ i.getOrderDate().format(formatter) + ","
                + i.getTaxObj().getState() + ","
                + i.getTaxObj().getTaxRate() + ","
                + i.getProduct().getProductType() + ","
                + i.getProduct().getCostPerSquareFoot() + ","
                + i.getProduct().getLaborCostPerSquareFoot() + "," 
                + i.getArea() + ","
                + i.getLaborCost() + ","
                + i.getMaterialCost() + ","
                + i.getTaxAmount() + ","
                + i.getTotal()
                );
            out.println();
        }
        
        out.flush();
        out.close();
    }
    
    private void writeCurrentKey() {
        String currentKey = loadCurrentKey();
        int numKey = Integer.parseInt(currentKey) + 1;
        try {
            out = new PrintWriter(new FileWriter("TESTorderKey.txt"));

        } catch (IOException ex) {
            System.out.println("Unable to write to Key (order number) save file");
        }
        
        out.print(Integer.toString(numKey));
        out.flush();
        out.close();
        
    }
    
    @Override
    public String loadCurrentKey(){
        
        try {
            in = new Scanner(
                    new BufferedReader(  
                            new FileReader("TESTorderKey.txt")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlooringOrderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String currentKey = in.nextLine();
        
        return currentKey;
    }
    
    
    @Override
    public void setCurrentDate(String currentDate){
        this.currentDate=currentDate;
        orderMap.clear();
        loadOrderFile(currentDate);
    }
    
    /**
     *
     * @param oldDate
     * @param order
     */
    @Override//removes order and saves updated list to file
    public void adjustPriorSaveFile(LocalDate oldDate, Order order) {
        String oldDateFileName= oldDate.format(formatter);
        clearMap();
        writeOrderListToFile(oldDateFileName);
        
    }
    
    @Override
    public void auditPreEdit(Order order){
        //do nothing
    }
    
    @Override
    public void processPostEdit(Order order) {
        String newFileName = createSaveFileName(order);
        writeOrderFile(order, newFileName);
        clearMap();
    }
    
    private String createSaveFileName(Order order){
        
        boolean found=false;
        String fileName="";
        for(Order i : new ArrayList<Order>(orderMap.values())){
            if(i.getOrderDate().equals(currentDate)){
                fileName=i.getOrderDate().format(formatter);
                found=true;
            }
        }
        if(!found){
            fileName=order.getOrderDate().format(formatter);
        }
        return fileName;
    }
    
    private void clearMap(){
        orderMap.clear();
    }

    
    
    
}
