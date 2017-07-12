/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderDaoImpl;
import com.sg.flooringmastery.dao.FlooringOrderTrainingDaoImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringOrderServiceImpl implements FlooringOrderService {
    private FlooringOrderDao orderDao;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    ApplicationContext ctx;
    FlooringOrderDao productionDao;
    
    public FlooringOrderServiceImpl(FlooringOrderDao orderDao) {
        this.orderDao=orderDao;
        this.productionDao=orderDao;
        
    }
    
    @Override
    public Order assembleOrder(Order order, Product product, Tax tax){
        order.setProduct(product);
        order.setTaxObj(tax);
        calculate(order);
        return order;
    }
    
    @Override
    public void addOrder(Order order){
        orderDao.addOrder(order);
    }
    
    @Override
    public void calculate(Order order){
        order.setLaborCost(order.getProduct().getLaborCostPerSquareFoot().multiply(order.getArea()));
        order.setMaterialCost(order.getProduct().getCostPerSquareFoot().multiply(order.getArea()));
        order.setTaxAmount(order.getTaxObj().getTaxRate().multiply(order.getMaterialCost().divide(new BigDecimal("100"))));
        order.setTotal(order.getLaborCost().add(order.getMaterialCost().add(order.getTaxAmount())));
    }
    
    @Override
    public boolean fileExists(String date){
        return orderDao.loadOrderFile("Orders_" + date + ".csv");
    }
    
    @Override
    public List<Order> getListOfOrdersByDay(String date){
        return orderDao.getOrderList(date);
    }
    
    @Override
    public Order getOrderByKey(String key){
        return orderDao.getOrderFromMap(key);
    }
    
    @Override
    public void auditPreEdit(Order order){
        orderDao.auditPreEdit(order);
    }
    
    @Override
    public void processEditedOrder(Order order)  {
        orderDao.processPostEdit(order);
    }
    
    @Override
    public void removeOrder(Order order)  {
        orderDao.removeOrder(order);
    }
    
    @Override
    public void adjustPriorSaveFile(LocalDate oldDate, Order order)  {
        orderDao.adjustPriorSaveFile(oldDate, order);
    }
    
    @Override
    public void toggleToTrainingMode(){
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.orderDao = ctx.getBean("trainingOrderDao", FlooringOrderTrainingDaoImpl.class);
    }
    
    @Override
    public void toggleToProdMode(){
        this.orderDao = productionDao;
    }
    


    
 
    
    
    
}
