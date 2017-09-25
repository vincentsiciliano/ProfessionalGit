/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Whereabout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vincentsiciliano
 */
public class SightingDaoImpl implements SightingDao {
    /////INSERT
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting(LocationID, SightingDate, SightingName) values(?, ?, ?)";
    
    
    
    private static final String SQL_SELECT_SIGHTING = "select * from Sighting where SightingID = ?";
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_SIGHTING = "select * from Sighting";
    
    
    /////UPDATE
    private static final String SQL_UPDATE_SIGHTING = "update Sighting set LocationID = ?, SightingDate = ?, SightingName = ? where SightingID = ?";
    
    
    /////DELETE
    private static final String SQL_DELETE_SIGHTING = "delete from Sighting where SightingID = ?";
    private static final String SQL_DELETE_METAHUMAN_SIGHTING = "delete from MetaHuman_Sighting where SightingID = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    
    @Inject
    public SightingDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Sighting sighting){
        
        jdbcTemplate.update(SQL_INSERT_SIGHTING, sighting.getLocationId(), sighting.getSightingDate().toString(), sighting.getSightingName());
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
    }

    @Override
    public void edit(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getLocationId(), sighting.getSightingDate().toString(), sighting.getSightingName(), sighting.getSightingId());
        
    }   
        
    @Override
    public void delete(int id) {
        
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_SIGHTING, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }

    @Override
    public Sighting getOne(int id) {
        try{
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), id);
            return sighting;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Sighting> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING, new SightingMapper());
        
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            
            Organization organization = new Organization();
            organization.setLocationID(rs.getInt("OrganizationID"));
            organization.setOrganizationName(rs.getString("OrganizationName"));
            organization.setOrganizationDescription(rs.getString("OrganizationDescription"));
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            
            return organization;
        }
    }
    
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingID"));
            sighting.setSightingDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());
            sighting.setSightingName(rs.getString("SightingName"));
            sighting.setLocationId(rs.getInt("LocationID"));
            
                
            return sighting;
        }
    }
    
    
        private static final class LocationMapper implements RowMapper<Whereabout> {

        @Override
        public Whereabout mapRow(ResultSet rs, int i) throws SQLException {
            
            Whereabout location = new Whereabout();
            location.setWhereaboutId(rs.getInt("LocationID"));
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            location.setWhereaboutName(rs.getString("LocationName"));
            location.setWhereaboutDescription(rs.getString("LocationDescription"));
                
            return location;
        }
    
 
    }
}


