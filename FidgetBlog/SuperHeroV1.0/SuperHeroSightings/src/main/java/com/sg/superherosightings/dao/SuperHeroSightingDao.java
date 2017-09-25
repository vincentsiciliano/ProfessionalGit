/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vincentsiciliano
 */
public class SuperHeroSightingDao {
    
    private JdbcTemplate jdbcTemplate;
    
    
    
    /////INSERT
    private static final String SQL_INSERT_METAHUMAN = "insert into MetaHuman (MetaHumanName, SecretName) values(?, ?)";
    private static final String SQL_INSERT_POWER = "insert into Powers (PowerDescription) value(?)";
    private static final String SQL_INSERT_LOCATION = "insert into Location (Longitude, Latitude, LocationName, LocationDescription, Address) values(?, ?, ?, ?, ?)";
    
    private static final String SQL_INSERT_ORGANIZATION = "insert into Organization (OrganizationName, OrganizationDescription, LocationID) values(?, ?, ?)";
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting(LocationID, SightingDate, SightingName) values(?, ?, ?)";
    
    private static final String SQL_INSERT_METAHUMAN_POWERS = "insert into MetaHuman_Powers(MetaHumanID, PowerID) values(?, ?)";
    private static final String SQL_INSERT_METAHUMAN_ORGANIZATION = "insert into MetaHuman_Organization(MetaHumanID, OrganizationID) values(?, ?)";
    private static final String SQL_INSERT_METAHUMAN_SIGHTING = "insert into MetaHuman_Sighting(MetaHumanID, SightingID) values(?, ?)";
    
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_METAHUMAN = "select * from MetaHuman";
    
    
    /////UPDATE
    private static final String SQL_UPDATE_METAHUMAN = "update MetaHuman set MetaHumanName = ?, SecretName = ? where MetaHumanID = ?";
    private static final String SQL_UPDATE_POWERS = "update Powers set PowerDescription = ? where PowerID = ?";
    private static final String SQL_UPDATE_LOCATION = "update Location set Longitude = ?, Latitude = ?, LocationName = ?, LocationDescription = ?, Address = ? where LocationID = ?";
    
    private static final String SQL_UPDATE_ORGANIZATION = "update Organization set OrganizationName = ?, OrganizationDescription = ?, LocationID = ? where OrganizationID = ?";
    private static final String SQL_UPDATE_SIGTHING = "update Sighting set LocationID = ?, SightingDate = ?, SightingName = ?";
    
    
    /////DELETE
    private static final String SQL_DELETE_METAHUMAN = "delete from MetaHuman where MetaHumanID = ?";
    private static final String SQL_DELETE_POWERS = "delete from Powers where PowerID = ?";
    private static final String SQL_DELETE_LOCATION = "delete from Location where LocationID = ?";
    
    private static final String SQL_DELETE_ORGANIZATION = "delete from Organization where Organization = ?";
    private static final String SQL_DELETE_SIGHTING = "delete from Sighting where Sighting = ?";
    
    private static final String SQL_DELETE_METAHUMAN_POWERS = "delete from MetaHuman_Powers where MetaHumanID = ?";
    private static final String SQL_DELETE_METAHUMAN_ORGANIZATION = "delete from MetaHuman_Organization where MetaHumanID = ?";
    private static final String SQL_DELETE_METAHUMAN_LOCATION = "delete from MetaHuman_Location where MetaHumanID = ?";
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Inject
    public SuperHeroSightingDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    
    
}
