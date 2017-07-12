/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringOrderService;
import com.sg.flooringmastery.service.FlooringProductService;
import com.sg.flooringmastery.service.FlooringTaxService;
import com.sg.flooringmastery.ui.FlooringView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public class Controller {
    FlooringView view;
    FlooringOrderService orderService;
    FlooringProductService productService;
    FlooringTaxService taxService;
    
    public Controller(FlooringView view, FlooringOrderService orderService, FlooringProductService productService, FlooringTaxService taxService) {
        this.view=view;
        this.orderService=orderService;
        this.productService=productService;
        this.taxService=taxService;
    }
    
    public void run(){
        
        boolean keepGoing=true;
        int selection;
        while(keepGoing){
            
            selection=view.mainMenu();
            switch(selection){
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveWork();
                    break;
                case 6:
                    keepGoing=false;
                    break;
                case 9:
                    toggleTrainingMode();
                    break;
            }
        }
    }
    
    public void displayOrders(){
        String lookupDate = view.getLookupDateFromUser();
        List<Order> orderList = orderService.getListOfOrdersByDay(lookupDate);
        if(orderList==null||orderList.isEmpty()){
            view.couldNotFindDate();
        }else{
            view.displayOrders(orderList);
        }
    }
    
    public void addOrder(){
        List<Product> productList = productService.getProductList();
        List<Tax> taxList = taxService.getTaxList();
        
        Order order = view.getCustomerNameAreaDate();
        String productName = view.getProductName(productList);
        String state = view.getState(taxList);
        
        Product product = productService.getProductObj(productName);
        Tax tax = taxService.getTaxObj(state);
        
        order = orderService.assembleOrder(order, product, tax);
        view.displaySingleOrder(order);
        
        if(view.confirmWithUser()){
            orderService.addOrder(order);
            view.confirmAddedMessage();
        }else{
            view.notAddedMessage();
        }
    }
    
    public void editOrder(){
        List<Product> productList = productService.getProductList();
        List<Tax> taxList = taxService.getTaxList();
        
        Order order = findOrder();
        if(order!=null){
            orderService.auditPreEdit(order);//for auditing purpose only
            view.editOrderBanner(order);
            LocalDate tempDate = order.getOrderDate();
            view.editOrderDateIsChanged(order);
            view.editCustomerName(order);
            Product newProduct = view.editProductName(order, productList);
            Tax newTax = view.editState(order, taxList);
            view.editArea(order);
            
            if(newProduct!=null){
                order.setProduct(newProduct);
            }
            if(newTax!=null){
                order.setTaxObj(newTax);
            }
            if(tempDate!=order.getOrderDate()){
                orderService.adjustPriorSaveFile(tempDate, order);
            }

            orderService.calculate(order);
            orderService.processEditedOrder(order);
        }
    }
    
    public void removeOrder(){
        Order order = findOrder();
        if(order!=null){
            orderService.removeOrder(order);
        }

    }
    public void toggleTrainingMode(){
        
        if(view.getMode()==1){
            orderService.toggleToTrainingMode();
            view.trainingModeMessage();
        }else{
            orderService.toggleToProdMode();
            view.productionModeMessage();
        }
    }
    
    public void saveWork(){
        
    }
    
    private Order findOrder(){
        String lookupDate;
        Order order;
        List<Order> orderList;
        do{ 
            lookupDate = view.getLookupDateFromUser();
            if("menu".equals(lookupDate)){
                return null;
            }

            orderList= orderService.getListOfOrdersByDay(lookupDate);
            if(orderList==null||orderList.isEmpty()){
                view.couldNotFindDate();
            }else{
                view.displayOrders(orderList);
            }
        }while(orderList==null||orderList.isEmpty());    


        do{
            String orderNumber = view.getOrderNumberFromUser();
            if("menu".equalsIgnoreCase(orderNumber)){
                return null;
            }
            order = orderService.getOrderByKey(orderNumber);
            if(order==null){
                view.noSuchOrderNumberMessage();
            }else{
                break;
            }
        }while(order==null);
        
        return order;
    }
    
    
}
