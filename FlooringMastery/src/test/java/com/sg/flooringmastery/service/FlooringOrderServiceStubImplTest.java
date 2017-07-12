/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderDaoImpl;
import com.sg.flooringmastery.dao.FlooringOrderDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringOrderTrainingDaoImpl;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dao.FlooringTaxDaoStubImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class FlooringOrderServiceStubImplTest {
    
    FlooringOrderDao orderDaoStub;
    FlooringProductDao productDaoStub;
    FlooringTaxDao taxDaoStub;
    FlooringOrderService orderService;
    
    Order order;
    Product carpet, laminate, tile, wood;
    Tax ohio, penn, minn, indiana;
    LocalDate date;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    
    public FlooringOrderServiceStubImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        productDaoStub = ctx.getBean("productDaoStub", FlooringProductDaoStubImpl.class);
        taxDaoStub = ctx.getBean("taxDaoStub", FlooringTaxDaoStubImpl.class);
        orderDaoStub = ctx.getBean("orderDaoStub", FlooringOrderDaoStubImpl.class);
        orderService = ctx.getBean("orderServiceStub", FlooringOrderServiceStubImpl.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        carpet = new Product("Carpet");
        carpet.setCostPerSquareFoot(new BigDecimal("2.25"));
        carpet.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        productDaoStub.addProduct(carpet);
        
        laminate = new Product("Laminate");
        laminate.setCostPerSquareFoot(new BigDecimal("1.75"));
        laminate.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        productDaoStub.addProduct(laminate);
        
        tile = new Product("Tile");
        tile.setCostPerSquareFoot(new BigDecimal("3.50"));
        tile.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        productDaoStub.addProduct(tile);
        
        wood = new Product("Wood");
        wood.setCostPerSquareFoot(new BigDecimal("5.15"));
        wood.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        productDaoStub.addProduct(wood);
        
        ohio = new Tax("Ohio",new BigDecimal("6.25"));
        taxDaoStub.addTaxObj(ohio);
        
        penn = new Tax("Pennsylvania",new BigDecimal("6.75"));
        taxDaoStub.addTaxObj(penn);
        
        minn = new Tax("Minnesota",new BigDecimal("5.75"));
        taxDaoStub.addTaxObj(minn);
        
        indiana = new Tax("Indiana",new BigDecimal("6.00"));
        taxDaoStub.addTaxObj(indiana);
        
        order = new Order();
        order.setCustomerName("Alison");
        order.setArea(new BigDecimal("10"));
        order.setOrderNumber("1");
        date = LocalDate.parse("01022001",DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setOrderDate(date);
        
        order = orderService.assembleOrder(order, wood, ohio);
        orderService.addOrder(order);
        
        
    }
    
    @After
    public void tearDown() {

        orderService.adjustPriorSaveFile(date, order);
    }

    /**
     * Test of assembleOrder method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testAssembleOrder() {
        
        assertEquals("Alison",order.getCustomerName());
        assertEquals("Wood",order.getProduct().getProductType());
        assertEquals("Ohio",order.getTaxObj().getState());
    }

    /**
     * Test of addOrder method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testAddOrder() {
       
        assertEquals(1,orderService.getListOfOrdersByDay("01022001").size());
    }

    /**
     * Test of calculate method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testCalculate() {
        assertEquals(new BigDecimal("47.50"),order.getLaborCost());
        assertEquals(new BigDecimal("51.50"),order.getMaterialCost());
        assertEquals(new BigDecimal("3.22"),order.getTaxAmount());
        assertEquals(new BigDecimal("102.22"),order.getTotal());
    }

    /**
     * Test of getListOfOrdersByDay method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testGetListOfOrdersByDay() {
        order = new Order();
        order.setCustomerName("Sharon");
        order.setArea(new BigDecimal("12"));
        order.setOrderNumber("2");
        date = LocalDate.parse("01022001",DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setOrderDate(date);
        
        order = orderService.assembleOrder(order, tile, penn);
        orderService.addOrder(order);
        
        assertEquals(2,orderService.getListOfOrdersByDay("01022001").size());
        
        
    }

    /**
     * Test of getOrderByKey method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testGetOrderByKey() {
        String key = Integer.toString(Integer.parseInt(orderDaoStub.loadCurrentKey())-1);
        orderDaoStub.loadOrderFile("01022001");
        Order order2 = orderService.getOrderByKey(key);
        assertEquals("Alison", order2.getCustomerName()); //must look at TESTorderKey.txt for current key
    }

    /**
     * Test of auditPreEdit method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testAuditPreEdit() {
    }

    /**
     * Test of processEditedOrder method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testProcessEditedOrder() {
        date = LocalDate.parse("01032001",DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setOrderDate(date);
        orderDaoStub.processPostEdit(order);
        assertEquals(1,orderService.getListOfOrdersByDay("01032001").size());
    }

    /**
     * Test of removeOrder method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testRemoveOrder() {
        orderService.removeOrder(order);
        assertEquals(0,orderService.getListOfOrdersByDay("01022001").size());
    }

    /**
     * Test of adjustPriorSaveFile method, of class FlooringOrderServiceImpl.
     */
    @Test
    public void testAdjustPriorSaveFile() {
        //Successful if other tests are successful; All other method tests implement this method (in teardown)
    }

    /**
     * Test of toggleToTrainingMode method, of class FlooringOrderServiceImpl.
     */
}