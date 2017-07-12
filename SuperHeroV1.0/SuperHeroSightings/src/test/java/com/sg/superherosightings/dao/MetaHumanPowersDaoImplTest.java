/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanPowers;
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
public class MetaHumanPowersDaoImplTest {
    
    ApplicationContext ctx;
    PowersDao powersDao;
    MetaHumanDao metaHumanDao;
    MetaHumanPowersDao metaHumanPowersDao;
    
    int lastOne;
    int lastPow;
    
    
    
    public MetaHumanPowersDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        metaHumanDao = ctx.getBean("metaHumanDao", MetaHumanDaoImpl.class);
        powersDao = ctx.getBean("powersDao", PowersDaoImpl.class);
        metaHumanPowersDao = ctx.getBean("metaHumanPowersDao", MetaHumanPowersDaoImpl.class);
        
        // delete all metas
        List<MetaHuman> metaHumanList = metaHumanDao.getAll();
        for (MetaHuman i : metaHumanList) {
            metaHumanDao.delete(i.getMetaHumanID());
           
        }
        
        // delete all pows
        List<Powers> powersList = powersDao.getAll();
        for (Powers i : powersList) {
            powersDao.delete(i.getPowerID());
           
        }
        
        MetaHuman metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Wonder Woman");
        metaHuman.setMetaHumanSecretName("Gal");
        metaHumanDao.add(metaHuman);
        
        metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Spider Man");
        metaHuman.setMetaHumanSecretName("Peter Parker");
        metaHumanDao.add(metaHuman);
        
        metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Professor X");
        metaHuman.setMetaHumanSecretName("Charles Xavier");
        metaHumanDao.add(metaHuman);
        
        metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Cyclops");
        metaHuman.setMetaHumanSecretName("Scott Summers");
        metaHumanDao.add(metaHuman);
        
        metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Gambit");
        metaHuman.setMetaHumanSecretName("Remy Etienne LeBeau");
        metaHumanDao.add(metaHuman);
        
        metaHumanList = metaHumanDao.getAll();
        for (MetaHuman i : metaHumanList) {
        lastOne = i.getMetaHumanID();
           
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
        
        
        MetaHumanPowers metaHumanPower = new MetaHumanPowers();
        
        //get metahuman for bridge
        metaHuman = metaHumanDao.getOne(lastOne);
        
        //get power for bridge
        power = powersDao.getOne(lastPow);
        

        //add meta and pow to bridge obj
        metaHumanPower.setMetaHuman(metaHuman);
        metaHumanPower.setPower(power);
        
        //add bridge obj to bridge table
        metaHumanPowersDao.add(metaHumanPower);
   
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class MetaHumanPowersDaoImpl.
     */
    @Test
    public void testAdd() {
        
        MetaHumanPowers metaHumanPower = new MetaHumanPowers();
        
        //get metahuman for bridge
         MetaHuman metaHuman = metaHumanDao.getOne(lastOne - 1);
        
        //get power for bridge
        Powers power = powersDao.getOne(lastPow - 1);
        

        //add meta and pow to bridge obj
        metaHumanPower.setMetaHuman(metaHuman);
        metaHumanPower.setPower(power);
        
        //add bridge obj to bridge table
        metaHumanPowersDao.add(metaHumanPower);
        
        assertEquals(2, metaHumanPowersDao.getAll().size());
        

    }




    @Test
    public void testGetLlWithMetaHuman() {
        assertEquals(1, metaHumanPowersDao.getAllWithMetaHuman(lastOne).size());
        
        
    }
    
    @Test
    public void testGetLlWithMetaPowers() {
        assertEquals(1, metaHumanPowersDao.getAllWithPowers(lastPow).size());
        
        
    }

    /**
     * Test of getAll method, of class MetaHumanPowersDaoImpl.
     */
    @Test
    public void testGetAll() {
    }
    
}
