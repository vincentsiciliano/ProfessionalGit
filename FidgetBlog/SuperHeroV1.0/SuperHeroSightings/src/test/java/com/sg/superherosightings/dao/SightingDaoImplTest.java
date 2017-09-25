/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Whereabout;
import java.time.LocalDate;
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
public class SightingDaoImplTest {
    
    
    ApplicationContext ctx;
    SightingDao sightingDao;
    LocationDao locationDao;
    
    int lastSit = 0;
    int lastLoc = 0;
           
    
    
    
    public SightingDaoImplTest() {
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
        sightingDao = ctx.getBean("sightingDao", SightingDaoImpl.class);
        locationDao = ctx.getBean("locationDao", LocationDaoImpl.class);

        // delete all sightings
        List<Sighting> sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            sightingDao.delete(i.getSightingId());
        }
        
        // deleta all Locations
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
            if(i.getWhereaboutId()>lastLoc){
                lastLoc = i.getWhereaboutId();
            }
            
        }
        
        
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(lastLoc - 1);
        sighting.setSightingName("Anarchy in Alaska!");
        sighting.setSightingDate(LocalDate.now());
        sightingDao.add(sighting);
                
        sighting = new Sighting();
        sighting.setLocationId(lastLoc - 2);
        sighting.setSightingName("Havoc in Hawaii!");
        sighting.setSightingDate(LocalDate.now());
        sightingDao.add(sighting);
        
        sighting = new Sighting();
        sighting.setLocationId(lastLoc);
        sighting.setSightingName("Problems in Panama!");
        sighting.setSightingDate(LocalDate.now());
        sightingDao.add(sighting);
        
        
        sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            if (i.getSightingId()>lastSit){
                lastSit = i.getSightingId();
            }
        }
        
        
        
        
        
        
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class SightingDaoImpl.
     */
    @Test
    public void testAdd() {
        
        Whereabout location = locationDao.getOne(lastLoc);
        
        Sighting sighting = new Sighting();
        sighting.setLocationId(location.getWhereaboutId());
        sighting.setSightingDate(LocalDate.now());
        sighting.setSightingLocation(location);
        sighting.setSightingName("Jeopardy In Japan!");
        
        sightingDao.add(sighting);
        
        assertEquals(4, sightingDao.getAll().size());
        
        
        
    }


    @Test
    public void testEdit() {
        
        Sighting sighting = sightingDao.getOne(lastSit);
        sighting.setSightingName("Apocalypse at Event Horizon");
        
        sightingDao.edit(sighting);
        
        assertEquals("Apocalypse at Event Horizon", sightingDao.getOne(lastSit).getSightingName());
        
        
        
    }

    /**
     * Test of delete method, of class SightingDaoImpl.
     */
    @Test
    public void testDelete() {
        
        sightingDao.delete(lastSit);
        
        assertEquals(2, sightingDao.getAll().size());
    }

    /**
     * Test of getOne method, of class SightingDaoImpl.
     */
    @Test
    public void testGetOne() {
        
        assertEquals("Problems in Panama!", sightingDao.getOne(lastSit).getSightingName());
    }

    /**
     * Test of getAll method, of class SightingDaoImpl.
     */
    @Test
    public void testGetAll() {
        
        assertEquals(3, sightingDao.getAll().size());
    }
    
}
