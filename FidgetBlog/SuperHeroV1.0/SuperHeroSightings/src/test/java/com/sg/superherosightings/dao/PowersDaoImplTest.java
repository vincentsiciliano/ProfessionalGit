/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Powers;
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
public class PowersDaoImplTest {
    
    PowersDao powersDao;
    int lastPow;
    
    
    
    public PowersDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        powersDao = ctx.getBean("powersDao", PowersDaoImpl.class);

        // delete all pows
        List<Powers> powersList = powersDao.getAll();
        for (Powers i : powersList) {
            powersDao.delete(i.getPowerID());
           
        }
        
        Powers power = new Powers();
        power.setPowerDescription("Laser Vision");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Psycho-Kinesis");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Teleportation");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Weather Control");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Pyro-Technics");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Ice-Technics");
        powersDao.add(power);
        
        power = new Powers();
        power.setPowerDescription("Shape-Shifting");
        powersDao.add(power);
        
        powersList = powersDao.getAll();
        for (Powers i : powersList) {
            lastPow=i.getPowerID();
        }
        
        
        
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class PowersDaoImpl.
     */
    @Test
    public void testAdd() {
        
        Powers power = new Powers();
        power.setPowerDescription("Flight");
        powersDao.add(power);
        
        assertEquals(8, powersDao.getAll().size());
        
    }

    @Test
    public void testEdit() {
        
        Powers power = powersDao.getOne(lastPow);
        power.setPowerDescription("Throwing with accuracy");
        powersDao.edit(power);
        
        assertEquals("Throwing with accuracy", powersDao.getOne(lastPow).getPowerDescription());
        
        
    }

    @Test
    public void testDelete() {
        
        powersDao.delete(lastPow);
        assertEquals(6, powersDao.getAll().size());
        
    }


    @Test
    public void testGetOne() {
        
        Powers power = powersDao.getOne(lastPow);
        assertEquals("Shape-Shifting", power.getPowerDescription());
        
    }

    /**
     * Test of getAll method, of class PowersDaoImpl.
     */
    @Test
    public void testGetAll() {
        
        assertEquals(7, powersDao.getAll().size());
    }
    
}
