/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanSighting;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Whereabout;
import java.time.LocalDate;
import java.util.List;
import javax.tools.DocumentationTool.Location;
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
public class MetaHumanSightingDaoImplTest {
    
    ApplicationContext ctx;
    
    MetaHumanSightingDao metaHumanSightingDao;
    LocationDao locationDao;
    MetaHumanDao metaHumanDao;
    SightingDao sightingDao;
    
    int lastOne;
    int lastLoc;
    int lastSit;
    
    
    public MetaHumanSightingDaoImplTest() {
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
        locationDao = ctx.getBean("locationDao", LocationDaoImpl.class);
        metaHumanDao = ctx.getBean("metaHumanDao", MetaHumanDaoImpl.class);
        metaHumanSightingDao = ctx.getBean("metaHumanSightingDao", MetaHumanSightingDaoImpl.class);
        sightingDao = ctx.getBean("sightingDao", SightingDaoImpl.class);
        
        // delete all sightings
        List<Sighting> sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            sightingDao.delete(i.getSightingId());
        }
        
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
        
                
        MetaHumanSighting metaHumanSighting = new MetaHumanSighting();
        
        //get location for sighting
        location = locationDao.getOne(lastLoc);
        
        //get metahuman for bridge
        metaHuman = metaHumanDao.getOne(lastOne);
        
        //get sighting for bridge
        sighting = sightingDao.getOne(lastSit);
        //add location to sighting
        sighting.setSightingLocation(location);
        //set location id based on location obj
        sighting.setLocationId(location.getWhereaboutId());
        
        //add meta and org to bridge obj
        metaHumanSighting.setMetaHuman(metaHuman);
        metaHumanSighting.setSighting(sighting);
        
        
        
        //add bridge obj to bridge table
        metaHumanSightingDao.add(metaHumanSighting);
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class MetaHumanSightingDaoImpl.
     */
    @Test
    public void testAdd() {
        
        MetaHumanSighting metaHumanSighting = new MetaHumanSighting();
        
        //get location for sighting
        Whereabout location = locationDao.getOne(lastLoc - 1);
        
        //get metahuman for bridge
        MetaHuman metaHuman = metaHumanDao.getOne(lastOne - 1);
        
        //get sighting for bridge
        Sighting sighting = sightingDao.getOne(lastSit - 1);
        //add location to sighting
        sighting.setSightingLocation(location);
        //set location id based on location obj
        sighting.setLocationId(location.getWhereaboutId());
        
        //add meta and org to bridge obj
        metaHumanSighting.setMetaHuman(metaHuman);
        metaHumanSighting.setSighting(sighting);
        
        //add bridge obj to bridge table
        metaHumanSightingDao.add(metaHumanSighting);
        
        
    }
    
    @Test
    public void testGetAllWithMetaHuman() {
        assertEquals(1, metaHumanSightingDao.getAllWithMetaHuman(lastOne).size());
        
    }

    @Test
    public void testGetAllWithSighting() {
        assertEquals(1, metaHumanSightingDao.getAllWithSighting(lastSit).size());
    }
    
    
    @Test
    public void testGetAll() {
        assertEquals(1, metaHumanSightingDao.getAll().size());
        
    }
    
}
