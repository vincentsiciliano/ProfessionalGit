/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Whereabout;
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
public class LocationDaoImplTest {
    
    
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    
    
    int lastOne;
    
    public LocationDaoImplTest() {
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
        locationDao = ctx.getBean("locationDao", LocationDaoImpl.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDaoImpl.class);
        sightingDao = ctx.getBean("sightingDao", SightingDaoImpl.class);
        
        
        // delete all Organizations
        List<Organization> organizationList = organizationDao.getAll();
        for (Organization i : organizationList) {
            organizationDao.delete(i.getOrganizationID());
           
        }
        
        // delete all sightings
        List<Sighting> sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            sightingDao.delete(i.getSightingId());
        }
        
        
        
        
        
        // delete all metas
        List<Whereabout> locationList = locationDao.getAll();
        for (Whereabout i : locationList) {
            locationDao.delete(i.getWhereaboutId());
           
        }
        
        
        Whereabout location = new Whereabout();
        location.setLatitude(12);
        location.setLongitude(15);
        location.setWhereaboutDescription("Somewhere on the equator");
        location.setWhereaboutName("HideOut1");
        location.setAddress("Unknown");
        locationDao.add(location);
        
        
        location = new Whereabout();
        location.setLatitude(100);
        location.setLongitude(90);
        location.setWhereaboutDescription("Someplace cold");
        location.setWhereaboutName("HideOut2");
        location.setAddress("Unknown");
        locationDao.add(location);
        
        
        location = new Whereabout();
        location.setLatitude(57);
        location.setLongitude(-100);
        location.setWhereaboutDescription("Someplace temperate in Asia");
        location.setWhereaboutName("HideOut3");
        location.setAddress("Unknown");
        locationDao.add(location);
        
        locationList = locationDao.getAll();
        for (Whereabout i : locationList) {
            lastOne=i.getWhereaboutId();
        }
        
        
        
        
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testAdd() {
        Whereabout location = new Whereabout();
        location.setWhereaboutName("Bat Cave");
        location.setWhereaboutDescription("bat cave with technology");
        location.setLatitude(50);
        location.setLongitude(70);
        
        locationDao.add(location);
        
        assertEquals("Bat Cave", locationDao.getOne(lastOne + 1).getWhereaboutName());
        
    }
    
    @Test
    public void testEdit() {
        Whereabout location = locationDao.getOne(lastOne);
        location.setWhereaboutDescription("It's just an igloo");
        
        locationDao.edit(location);
        
        assertEquals("It's just an igloo", locationDao.getOne(location.getWhereaboutId()).getWhereaboutDescription());
    }

    /**
     * Test of delete method, of class LocationDaoImpl.
     */
    @Test
    public void testDelete() {
        locationDao.delete(lastOne);
        
        assertEquals(2, locationDao.getAll().size());
        
    }

    /**
     * Test of getOne method, of class LocationDaoImpl.
     */
    @Test
    public void testGetOne() {
        Whereabout location = locationDao.getOne(lastOne);
        
        assertEquals("HideOut3", location.getWhereaboutName());
        
    }

    /**
     * Test of getAll method, of class LocationDaoImpl.
     */
    @Test
    public void testGetAll() {
        
        assertEquals(3, locationDao.getAll().size());
        
    }
    
}
