/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vincentsiciliano
 */
public class MetaHumanDaoImplTest {
    
     int lastOne;
    
    MetaHumanDao metaHumanDao;
    
    public MetaHumanDaoImplTest() {
        
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
        
        metaHumanDao = ctx.getBean("metaHumanDao", MetaHumanDaoImpl.class);

        // delete all metas
        List<MetaHuman> metaHumanList = metaHumanDao.getAll();
        for (MetaHuman i : metaHumanList) {
            metaHumanDao.delete(i.getMetaHumanID());
           
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
        

    }
    
    @After
    public void tearDown() {
        
        
    }

    /**
     * Test of add method, of class MetaHumanDaoImpl.
     */
    @Test
    public void testAdd() {
        MetaHuman metaHuman = new MetaHuman();
        metaHuman.setMetaHumanName("Edgar");
        metaHuman.setMetaHumanSecretName("Pierre");
        
        metaHumanDao.add(metaHuman);
        
        assertEquals("Edgar", metaHumanDao.getOne(lastOne + 1).getMetaHumanName());
    }
    
    @Test
    public void testEdit() {
        MetaHuman metaHuman = metaHumanDao.getOne(lastOne);
        metaHuman.setMetaHumanName("Mystique");
        metaHuman.setMetaHumanSecretName("Raven Darkhölme");
        
        metaHumanDao.edit(metaHuman);
        
        assertEquals("Raven Darkhölme",metaHumanDao.getOne(lastOne).getMetaHumanSecretName());
        assertEquals("Mystique",metaHumanDao.getOne(lastOne).getMetaHumanName());
        
    }
    
    @Test
    public void testDelete() {
        metaHumanDao.delete(lastOne);
        assertEquals(4, metaHumanDao.getAll().size());
        
    }
    
    @Test
    public void testGetOne() {
        assertEquals("Remy Etienne LeBeau",metaHumanDao.getOne(lastOne).getMetaHumanSecretName());
        
    }
    
    @Test
    public void testGetAll() {
        assertEquals(5, metaHumanDao.getAll().size());
    }

    
}
