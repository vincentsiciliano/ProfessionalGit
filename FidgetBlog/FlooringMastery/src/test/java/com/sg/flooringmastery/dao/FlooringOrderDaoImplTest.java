/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vincentsiciliano
 */
public class FlooringOrderDaoImplTest {
    FlooringProductDao productDaoStub;
    FlooringTaxDao taxDaoStub;
    FlooringOrderDao orderDaoStub;
    Order order;
    LocalDate date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    
    public FlooringOrderDaoImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        productDaoStub = ctx.getBean("productDaoStub", FlooringProductDaoStubImpl.class);
        taxDaoStub = ctx.getBean("taxDaoStub", FlooringTaxDaoStubImpl.class);
        orderDaoStub = ctx.getBean("orderDaoStub", FlooringOrderDaoStubImpl.class);
    }
    
    @BeforeClass
    public static void setUpClass() {

        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        Product product = new Product("Carpet");
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        productDaoStub.addProduct(product);
        
        product = new Product("Laminate");
        product.setCostPerSquareFoot(new BigDecimal("1.75"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        productDaoStub.addProduct(product);
        
        product = new Product("Tile");
        product.setCostPerSquareFoot(new BigDecimal("3.50"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        productDaoStub.addProduct(product);
        
        product = new Product("Wood");
        product.setCostPerSquareFoot(new BigDecimal("5.15"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        productDaoStub.addProduct(product);
        
        Tax tax = new Tax("Ohio",new BigDecimal("6.25"));
        taxDaoStub.addTaxObj(tax);
        
        tax = new Tax("Pennsylvania",new BigDecimal("6.75"));
        taxDaoStub.addTaxObj(tax);
        
        tax = new Tax("Minnesota",new BigDecimal("5.75"));
        taxDaoStub.addTaxObj(tax);
        
        tax = new Tax("Indiana",new BigDecimal("6.00"));
        taxDaoStub.addTaxObj(tax);
        
        order = new Order();
        order.setCustomerName("Alison");
        order.setTaxObj(taxDaoStub.getTaxObj("Ohio"));
        order.setProduct(productDaoStub.getProductObj("Carpet"));
        
        order.setArea(new BigDecimal("10"));
        order.setLaborCost(new BigDecimal("11"));
        order.setMaterialCost(new BigDecimal("12"));
        order.setTaxAmount(new BigDecimal("13"));
        order.setTotal(new BigDecimal("14"));
        
        date = LocalDate.parse("01022001",DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setOrderDate(date);
        
        orderDaoStub.addOrder(order);
    }
    
    @After
    public void tearDown() {
        
        orderDaoStub.adjustPriorSaveFile(date, order);
        orderDaoStub.adjustPriorSaveFile(LocalDate.parse("01032001",DateTimeFormatter.ofPattern("MMddyyyy")), order);
    }

    /**
     * Test of addOrder method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testAddOrder() {

        assertEquals(1,orderDaoStub.getOrderList("01022001").size());
    }

    /**
     * Test of removeOrder method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testRemoveOrder() {
        
        orderDaoStub.removeOrder(order);
        assertEquals(0,orderDaoStub.getOrderList("01022001").size());
    }

    /**
     * Test of getOrderFromMap method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testGetOrderFromMap() {
//        Order order2 = orderDaoStub.getOrderFromMap(orderDaoStub.loadCurrentKey());
//        assertEquals(Integer.toString(Integer.parseInt(order2.getOrderNumber()) + 1), orderDaoStub.loadCurrentKey());
    }

    /**
     * Test of getOrderList method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testGetOrderList() {
        assertEquals(1,orderDaoStub.getOrderList("01022001").size());
    }

    /**
     * Test of loadOrderFile method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testLoadOrderFile() {
        
        //Successful if other tests are successful; All other tested methods implement this method
    }

    /**
     * Test of writeOrderFile method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testWriteOrderFile() {
        //Successful if other tests are successful; All other tested methods implement this method
    }

    /**
     * Test of writeOrderListToFile method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testWriteOrderListToFile() {
        //Successful if other tests are successful; All other tested methods implement this method
    }

    /**
     * Test of loadCurrentKey method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testLoadCurrentKey() {
        //Successful if other tests are successful; All other tested methods implement this method
    }

    /**
     * Test of setCurrentDate method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testSetCurrentDate() {
        //Successful if other tests are successful; Other tested methods implement this method
    }

    /**
     * Test of adjustPriorSaveFile method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testAdjustPriorSaveFile() {
        //Successful if other tests are successful; All other method tests implement this method (in teardown)
    }

    /**
     * Test of auditPreEdit method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testAuditPreEdit() {
        //does nothing (for audit use only)
    }

    /**
     * Test of processPostEdit method, of class FlooringOrderDaoImpl.
     */
    @Test
    public void testProcessPostEdit() {
        date = LocalDate.parse("01032001",DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setOrderDate(date);
        orderDaoStub.processPostEdit(order);
        assertEquals(1,orderDaoStub.getOrderList("01032001").size());
    }
    
}
