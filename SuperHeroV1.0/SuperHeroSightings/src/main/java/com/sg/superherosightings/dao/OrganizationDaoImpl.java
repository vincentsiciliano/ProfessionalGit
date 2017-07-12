/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
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
public class OrganizationDaoImpl implements OrganizationDao {
    /////INSERT
   
    private static final String SQL_INSERT_ORGANIZATION = "insert into Organization (OrganizationName, OrganizationDescription) values(?, ?)";
    
    
    private static final String SQL_SELECT_ORGANIZATION= "select * from Organization where OrganizationID = ?";
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_ORGANIZATION= "select * from Organization";
    
    
    /////UPDATE
    private static final String SQL_UPDATE_ORGANIZATION = "update Organization set OrganizationName = ?, OrganizationDescription = ?, LocationID = ? where OrganizationID = ?";
    
    /////DELETE
    
    
    private static final String SQL_DELETE_ORGANIZATION = "delete from Organization where OrganizationID = ?";
    private static final String SQL_DELETE_METAHUMAN_ORGANIZATION = "delete from MetaHuman_Organization where OrganizationID = ?";
    
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public OrganizationDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Organization organization){
        
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, organization.getOrganizationName(), organization.getOrganizationDescription());
        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(organizationId);
        System.out.println("Insert");
        
        
    }

    @Override
    public void edit(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, organization.getOrganizationName(), organization.getOrganizationDescription(), organization.getLocationID(), organization.getOrganizationID());
        
    }   
        
    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, id);
        System.out.println("delete method");
        
    }

    @Override
    public Organization getOne(int id) {
        try{
            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), id);
            return organization;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Organization> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION, new OrganizationMapper());
        
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
