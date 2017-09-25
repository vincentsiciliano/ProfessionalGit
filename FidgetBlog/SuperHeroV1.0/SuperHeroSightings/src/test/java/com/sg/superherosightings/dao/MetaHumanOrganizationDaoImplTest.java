/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanOrganization;
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
public class MetaHumanOrganizationDaoImplTest {
    
    
    ApplicationContext ctx;
    
    MetaHumanOrganizationDao metaHumanOrganizationDao;
    OrganizationDao organizationDao;
    LocationDao locationDao;
    MetaHumanDao metaHumanDao;
    SightingDao sightingDao;
    
    int lastOne;
    int lastLoc;
    int lastOrg;
    
    
    
    public MetaHumanOrganizationDaoImplTest() {
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
        metaHumanDao = ctx.getBean("metaHumanDao", MetaHumanDaoImpl.class);
        metaHumanOrganizationDao = ctx.getBean("metaHumanOrganizationDao", MetaHumanOrganizationDaoImpl.class);
        sightingDao = ctx.getBean("sightingDao", SightingDaoImpl.class);
        
        // delete all orgs
        List<MetaHumanOrganization> metaHumanOrganizationList = metaHumanOrganizationDao.getAll();
        for (MetaHumanOrganization i : metaHumanOrganizationList) {
            organizationDao.delete(i.getMetaHumanOrganizationId());
        }
        
        // delete all sightings
        List<Sighting> sightingList = sightingDao.getAll();
        for (Sighting i : sightingList) {
            sightingDao.delete(i.getSightingId());
        }
        
        // delete all orgs
        List<Organization> organizationList = organizationDao.getAll();
        for (Organization i : organizationList) {
            organizationDao.delete(i.getOrganizationID());
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
        
        MetaHumanOrganization metaHumanOrganization = new MetaHumanOrganization();
        
        //get location for organization
        location = locationDao.getOne(lastLoc);
        
        //get metahuman for bridge
        metaHuman = metaHumanDao.getOne(lastOne);
        
        //get organization for bridge
        organization = organizationDao.getOne(lastOrg);
        //add location to organization
        organization.setOrganizationLocation(location);
        //set location id based on location obj
        organization.setLocationID(location.getWhereaboutId());
        
        //add meta and org to bridge obj
        metaHumanOrganization.setMetaHuman(metaHuman);
        metaHumanOrganization.setOrganization(organization);
        
        //add bridge obj to bridge table
        metaHumanOrganizationDao.add(metaHumanOrganization);
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class MetaHumanOrganizationDaoImpl.
     */
    @Test
    public void testAdd() {
        
        MetaHumanOrganization metaHumanOrganization = new MetaHumanOrganization();
        
        //get location for organization
        Whereabout location = locationDao.getOne(lastLoc - 1);
        
        //get metahuman for bridge
        MetaHuman metaHuman = metaHumanDao.getOne(lastOne - 1);
        
        //get organization for bridge
        Organization organization = organizationDao.getOne(lastOrg - 1);
        //add location to organization
        organization.setOrganizationLocation(location);
        //set location id based on location obj
        organization.setLocationID(location.getWhereaboutId());
        
        //add meta and org to bridge obj
        metaHumanOrganization.setMetaHuman(metaHuman);
        metaHumanOrganization.setOrganization(organization);
        
        //add bridge obj to bridge table
        metaHumanOrganizationDao.add(metaHumanOrganization);
        
        assertEquals(2, metaHumanOrganizationDao.getAll().size());
        
    }
    
    
    public void testGetAllWithMetaHuman(){
        
        assertEquals(1, metaHumanOrganizationDao.getAllWithMetaHuman(lastOne).size());
        assertEquals(1, metaHumanOrganizationDao.getAllWithMetaHuman(lastOne - 1).size());
    }
    
    public void testGetAllWithOrganization(){
        
        assertEquals(1, metaHumanOrganizationDao.getAllWithOrganization(lastOrg).size());
        assertEquals(1, metaHumanOrganizationDao.getAllWithOrganization(lastOrg - 1).size());
    }
    
    public void testGetAall(){
        
        assertEquals(2, metaHumanOrganizationDao.getAll().size());
    }
    
    

}
