/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public interface FlooringOrderService {
    public Order assembleOrder(Order order, Product product, Tax tax);
    public List<Order> getListOfOrdersByDay(String date);
    public void addOrder(Order order);
    public boolean fileExists(String date);
    public Order getOrderByKey(String key);
    public void processEditedOrder(Order order);
    public void auditPreEdit(Order order);
    public void calculate(Order order);
    public void removeOrder(Order order);
    public void adjustPriorSaveFile(LocalDate date, Order order);
    public void toggleToProdMode();
    public void toggleToTrainingMode();
}
