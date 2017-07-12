/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringView {
    private UserIO io;
    
    public FlooringView(UserIO io){
        this.io=io;
    }
    
    public int mainMenu(){
        String choice;
        do{
            choice =io.readString("MAIN MENU"
                + "\n[1] Display Orders"
                + "\n[2] Add an Order"
                + "\n[3] Edit and Order"
                + "\n[4] Remove an Order"
                + "\n[5] Save Current Work"
                + "\n[6] Exit"
                + "\n[9] Toggle Training/Production Mode"
            );
        }while(!"1".equals(choice)
                &&!"2".equals(choice)
                &&!"3".equals(choice)
                &&!"4".equals(choice)
                &&!"5".equals(choice)
                &&!"6".equals(choice)
                &&!"9".equals(choice)
            );
        
        return Integer.parseInt(choice);
    }
    
    public Order getCustomerNameAreaDate(){
        Order order = new Order();
        boolean ok=false;
        order.setCustomerName(io.readString("Please enter Customer Name: "));
        while(!ok){
            try{
                order.setOrderDate(LocalDate.parse(io.readString("Please enter Order Date: MMDDYYYY "),DateTimeFormatter.ofPattern("MMddyyyy")));
                ok=true;
            }catch(java.time.format.DateTimeParseException e){
                io.print("\nMESSAGE>>Order date must be entered in MMDDYYYY format.\n");
                ok=false;
            }
        }
        
        do{
            try{
                order.setArea(new BigDecimal(io.readString("Please enter Area of flooring product being used: ")));
                ok=true;
            }catch(NumberFormatException e){
                io.print("\nMESSAGE>>Not a valid number.\n");
                ok=false;
            }
        }while(!ok);
        
        return order;
    }
    
        
    public boolean editOrderDateIsChanged(Order order){
        String newOrderDate = io.readString("\nEnter Order Date (" + order.getOrderDate().toString() + "):");
        if(newOrderDate.trim().length()!=0){
           editOrderDate(newOrderDate, order);
           return true;
        }else{
            io.print("Unchanged.");
        }
        return false;
    }
    
    public void editOrderDate(String userDate, Order order){
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        try{
                date = LocalDate.parse(userDate,DateTimeFormatter.ofPattern("MMddyyyy"));
                order.setOrderDate(date);
            }catch(java.time.format.DateTimeParseException e){
                io.print("\nMESSAGE>>Order date must be entered in MMDDYYYY format\n");
                
            }
    }
    
    public String getProductName(List<Product> productList){
        boolean reloop=true;
        String productSelection;
        
        for(Product i : productList){
            io.print("Product Name: " + i.getProductType() + " -- Price Per Sq Ft" + i.getCostPerSquareFoot() + " -- Labor Cost Per Sq Ft: " + i.getLaborCostPerSquareFoot());
        }
        
        do{
            productSelection = (io.readString("Please enter Product Type: "));
            
            for(Product i: productList){
                if(i.getProductType().equalsIgnoreCase(productSelection)){
                    reloop=false;
                    return i.getProductType();
                }
            }
            io.print("\nMESSAGE>>Invalid Product Type.\n");
        }while(reloop==true);
        
        return "0";

    }
    
    public String getState(List<Tax> taxList){
        boolean reloop=true;
        String stateSelection;
        
        for(Tax i : taxList){
            io.print("State: " + i.getState() + " -- Tax Rate: " + i.getTaxRate());
        }
        
        do{
            stateSelection = (io.readString("Please enter State: "));
            
            for(Tax i: taxList){
                if(i.getState().equalsIgnoreCase(stateSelection)){
                    reloop=false;
                    return i.getState();
                }
            }
            io.print("\nMESSAGE>>Invalid State.\n");
        }while(reloop==true);
        
        return "0";
    }
    
    
    
    public void displayOrders(List<Order> orderList){
        for(Order i : orderList){
            io.print("================================================================");
            io.print("ORDER NUMBER: " + i.getOrderNumber());
            displaySingleOrder(i);
        }
    }
    
    public void displaySingleOrder(Order order){
        io.print("================================================================");
        io.print("Order Date: " + order.getOrderDate().toString()
            + "\nCustomer Name: " + order.getCustomerName()
            + "\nProduct type: " + order.getProduct().getProductType()
            + "\nArea: " + order.getArea()
            + "\n**Product Cost Per Sq Ft: " + order.getProduct().getCostPerSquareFoot().toString()
            + "\n**Product Labor Per Sq Ft: " + order.getProduct().getLaborCostPerSquareFoot().toString()
            + "\nCustomer State: " + order.getTaxObj().getState()
            + "\n**Tax Rate: " + order.getTaxObj().getTaxRate().toString()
            + "\nLabor Charge: " + order.getLaborCost()
            + "\nMaterials Charge: " + order.getMaterialCost()
            + "\nTax: " + order.getTaxAmount()
            + "\nTOTAL: " + order.getTotal()
        );
        io.print("================================================================");
    }


    
    public String getLookupDateFromUser(){
        boolean ok=false;
        LocalDate date = null;
        String stringEntry = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        while(!ok){
            try{
                stringEntry = io.readString("Please enter Order Date: MMDDYYYY or enter [MENU] for Main Menu ");
                date = LocalDate.parse(stringEntry,DateTimeFormatter.ofPattern("MMddyyyy"));
                ok=true;
            }catch(java.time.format.DateTimeParseException e){
                if("menu".equalsIgnoreCase(stringEntry)){
                    return "menu";
                }
                io.print("\nMESSAGE>>Order date must be entered in MMDDYYYY format.\n");
                ok=false;
            }
        }
        return date.format(formatter);
    }
    
    public void couldNotFindDate(){
        io.print("\nMESSAGE>>Could not find date.\n");
    }
    
    public boolean confirmWithUser(){
        String y;
        do{
            y = io.readString("\nCommit new order? [y/n]");
        }while(!"y".equals(y)&&!"n".equals(y));
        if(y.equals("y")){
            return true;
        }else{
            return false;
        }
    }
    
    public void confirmAddedMessage(){
        io.print("\nMESSAGE>>Order added.\n");
    }
    
    public void notAddedMessage(){
        io.print("\nMESSAGE>>Did not add order.\n");
    }
    
    public String getOrderNumberFromUser(){
        return io.readString("Please enter order number or enter [MENU] for Main Menu: ");
    }
    
    public void noSuchOrderNumberMessage(){
        io.print("\nMESSAGE>>No such Order Number. Try again.\n");
    }

    public void editOrderBanner(Order order) {
        io.print("Edit Order #" + order.getOrderNumber() + " -- Customer: " + order.getCustomerName() + " -- Date: " + order.getOrderDate().toString());
        io.print("-Press enter if you wish to make no edit to field-");
    }

    public void editCustomerName(Order order){
        String newCustomerName = io.readString("Enter Customer Name (" + order.getCustomerName() + "):");
        if(newCustomerName.trim().length()!=0){
            order.setCustomerName(newCustomerName);
        }else{
            io.print("Unchanged.");
        }
    }
    
    public Product editProductName(Order order, List<Product> productList){
        io.print("Enter new Product Type (" + order.getProduct().getProductType() + "):");
        
            boolean reloop=true;
            String productSelection;

            for(Product i : productList){
                io.print("Product Name: " + i.getProductType() + " -- Price Per Sq Ft" + i.getCostPerSquareFoot() + " -- Labor Cost Per Sq Ft: " + i.getLaborCostPerSquareFoot());
            }

            do{
                productSelection = (io.readString("Please enter Product Type: "));

                for(Product i: productList){
                    if(i.getProductType().equalsIgnoreCase(productSelection)){
                        return i;
                    }else if(productSelection.trim().length()==0){
                        io.print("Unchanged.");
                        return null;
                    }
                }
                io.print("\nMESSAGE>>Invalid Product Type.\n");
            }while(reloop==true);
            return null;
    }
    
    public void editArea(Order order){
        boolean ok=false;
        String newArea="";
        do{
            try{
                newArea = io.readString("Enter Area (" + order.getArea() + "):");
           
                if(newArea.trim().length()!=0){
                    order.setArea(new BigDecimal(newArea));
                    ok=true;
                }else{
                    io.print("Unchanged.");
                    break;
                }
            }catch(NumberFormatException e){
                io.print("\nMESSAGE>>Not a valid number.\n");
                ok=false;
            }
        }while(!ok);
    }
    
    public Tax editState(Order order, List<Tax> taxList){
        io.print("Enter State (" + order.getTaxObj().getState() + "):");
       
        boolean reloop=true;
        String stateSelection;

        for(Tax i : taxList){
            io.print("State: " + i.getState() + " -- Tax Rate: " + i.getTaxRate());
        }

        do{
            stateSelection = (io.readString("Please enter State: "));

            for(Tax i: taxList){
                if(i.getState().equalsIgnoreCase(stateSelection)){
                    return i;
                }else if(stateSelection.trim().length()==0){
                    io.print("Unchanged.");
                    return null;
                }
            }
            io.print("\nMESSAGE>>Invalid State.\n");
        }while(reloop==true);
        return null;
    }
    
    public void trainingModeMessage(){
        io.print("\nMESSAGE>>TRAINING MODE ACTIVATED\n");
    }
    
    public void productionModeMessage(){
        io.print("\nMESSAGE>>PRODUCTION MODE ACTIVATED\n");
    }
    
    public int getMode(){
        int choice;
        do{
            choice = io.readInt("Press [1] for Training Mode\nPress [2] for Production Mode");
        }while(choice!=1&&choice!=2);
        return choice;
    }
    
    public void trainingModeMenuHeader(){
        io.print("|TRAINING MODE| MAIN MENU - FILE PERSISTENCE DISABLED");
    }
    
    public void productionModeMenuHeader(){
        io.print("|PRODUCTION MODE| MAIN MENU - FILE PERSISTENCE ENABLED");
    }
    


    

    
    
    
    
    
}
