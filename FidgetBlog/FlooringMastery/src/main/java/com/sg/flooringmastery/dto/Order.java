/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author vincentsiciliano
 */
public class Order {

    private String orderNumber;
    private String customerName;
    private LocalDate orderDate;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal taxAmount;
    private BigDecimal total;
    private Product product;
    private Tax tax;
    
    public Order() {
        
    }
    

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.orderNumber = OrderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal LaborCost) {
        this.laborCost = LaborCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setTaxObj(Tax tax){
        this.tax=tax;
    }
    
    public Tax getTaxObj(){
        return tax;
    }
    
    public void setProduct(Product product){
        this.product=product;
    }
    
    public Product getProduct(){
        return product;
    }
    
    @Override
    public String toString(){
        return "Order Number: "+orderNumber 
                + "; Name: " + customerName 
                + "; Order Date: " + orderDate.toString() 
                + "; State: " + tax.getState() 
                + "; Product Type: "+product.getProductType() 
                + "; Area: " + area.toString() 
                + "; Material Cost: " + materialCost.toString() 
                + "; Labor Cost: " + laborCost.toString() 
                + "; Tax Amount: " + taxAmount.toString() 
                + "; Total: " + total.toString();
    }
    
    
    
    
    
}
