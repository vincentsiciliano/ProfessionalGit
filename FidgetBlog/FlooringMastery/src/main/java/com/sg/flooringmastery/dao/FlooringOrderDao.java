/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringOrderDao {
    
    public void addOrder(Order order);
    public void removeOrder(Order order);
    public Order getOrderFromMap(String orderNumber);
    public List<Order> getOrderList(String date);
    public boolean loadOrderFile(String date);
    public void writeOrderFile(Order order, String fileName);
    public void writeOrderListToFile(String fileName);
    public String loadCurrentKey();
    public void adjustPriorSaveFile(LocalDate oldDate, Order order);
    public void processPostEdit(Order order);
    public void setCurrentDate(String currentDate);
    public void auditPreEdit(Order order);
     
}
