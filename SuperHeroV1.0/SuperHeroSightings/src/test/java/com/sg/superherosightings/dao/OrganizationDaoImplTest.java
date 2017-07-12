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
public class OrganizationDaoImplTest {
    
    ApplicationContext ctx;
    
    OrganizationDao organizationDao;
    LocationDao locationDao;
    SightingDao sightingDao;
    
    int lastOrg = 1;
    int lastLoc = 0;
    
            
    
    public OrganizationDaoImplTest() {
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
        organizationDao = ctx.getBean("organizationDao", OrganizationDaoImpl.class);
        locationDao = ctx.getBean("locationDao", LocationDaoImpl.class);
        sightingDao = ctx.getBean("sightingDao", SightingDaoImpl.class);
        
        // delete all sightings
        List<Sighting> sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            sightingDao.delete(i.getSightingId());
        }
        
        
        
        

        // delete all orgs
        List<Organization> organizationList = organizationDao.getAll();
        for (Organization i : organizationList) {
            organizationDao.delete(i.getOrganizationID());
            System.out.println("delete loop");
           
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
        
        Organization organization = new Organization();
        organization.setOrganizationName("Good Boys and Girls Corp");
        organization.setOrganizationDescription("They seem p nice");
        organization.setLocationID(lastLoc);
        organizationDao.add(organization);
        
        organization = new Organization();
        organization.setOrganizationName("Bug People, Inc");
        organization.setOrganizationDescription("They seem creepy...and slimy");
        organization.setLocationID(lastLoc- 1);
        organizationDao.add(organization);
        
        organization = new Organization();
        organization.setOrganizationName("Puppies, Inc");
        organization.setOrganizationDescription("Cute! But evil...");
        organization.setLocationID(lastLoc - 2);
        organizationDao.add(organization);
        
        
        organizationList = organizationDao.getAll();
        for (Organization i : organizationList) {
            if(i.getOrganizationID()>lastOrg){
                lastOrg = i.getOrganizationID();
            }
        }
            
        

        

        
        
    }
    
    
    @After
    public void tearDown() {
        
        
    }

    @Test
    public void testAdd() {
        
        Whereabout location = locationDao.getOne(lastLoc);
        
        Organization organization = new Organization();
        organization.setOrganizationName("Bad Guys, inc");
        organization.setOrganizationDescription("These guys are bad");
        organization.setOrganizationLocation(location);
        organizationDao.add(organization);
        
        assertEquals("Bad Guys, inc", organizationDao.getOne(lastOrg + 1).getOrganizationName());
        assertEquals("HideOut3", organization.getOrganizationLocation().getWhereaboutName());
        
    }
    
    

    
}
