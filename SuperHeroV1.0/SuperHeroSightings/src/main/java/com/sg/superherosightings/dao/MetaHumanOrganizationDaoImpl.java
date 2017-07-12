/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanOrganization;
import com.sg.superherosightings.dto.Organization;
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
public class MetaHumanOrganizationDaoImpl implements MetaHumanOrganizationDao {
    
    /////INSERT
    private static final String SQL_INSERT_METAHUMAN_ORGANIZATION = "insert into MetaHuman_Organization(MetaHumanID, OrganizationID) values(?, ?)";
    
    
    private static final String SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_ORGANIZATION = "select * from MetaHuman_Organization where MetaHumanID = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATION_FROM_METAHUMAN_ORGANIZATION = "select * from MetaHuman_Organization where OrganizationID = ?";
    
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_METAHUMAN_ORGANIZATION = "select * from MetaHuman_Organization";
    
    
    /////UPDATE
    
    
    
    
    
    /////DELETE
    private static final String SQL_DELETE_METAHUMAN_ORGANIZATION = "delete from MetaHuman_Organization where MetaHumanID = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public MetaHumanOrganizationDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

 @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(MetaHumanOrganization metaHumanOrganization){
        
        jdbcTemplate.update(SQL_INSERT_METAHUMAN_ORGANIZATION, metaHumanOrganization.getMetaHuman().getMetaHumanID(), metaHumanOrganization.getOrganization().getOrganizationID());
        int metaHumanOrganizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        metaHumanOrganization.setMetaHumanOrganizationId(metaHumanOrganizationId);
    }


    @Override
    public void delete(int id) {
        
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_ORGANIZATION, id);
    }

    @Override
    public List<MetaHumanOrganization> getAllWithMetaHuman(int id) {
        try{
             return jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_ORGANIZATION, new MetaHumanOrganizationMapper(), id);
            
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    @Override
    public List<MetaHumanOrganization> getAllWithOrganization(int id) {
        try{
             return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION_FROM_METAHUMAN_ORGANIZATION, new MetaHumanOrganizationMapper(), id);
            
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    

    @Override
    public List<MetaHumanOrganization> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_ORGANIZATION, new MetaHumanOrganizationMapper());
        
    }
    
    private static final class MetaHumanOrganizationMapper implements RowMapper<MetaHumanOrganization> {

        @Override
        public MetaHumanOrganization mapRow(ResultSet rs, int i) throws SQLException {
            MetaHumanOrganization metaHumanOrganization = new MetaHumanOrganization();
            metaHumanOrganization.setMetaHumanOrganizationId(rs.getInt("MetaHuman_OrganizationID"));
            metaHumanOrganization.setMetaHumanId(rs.getInt("MetaHumanID"));
            metaHumanOrganization.setOrganizationId(rs.getInt("OrganizationID"));
            
            return metaHumanOrganization;
        }
    }
    
    private static final class MetaHumanMapper implements RowMapper<MetaHuman> {

        @Override
        public MetaHuman mapRow(ResultSet rs, int i) throws SQLException {
            MetaHuman metaHuman = new MetaHuman();
            metaHuman.setMetaHumanID(rs.getInt("MetaHumanID"));
            metaHuman.setMetaHumanName(rs.getString("MetaHumanName"));
            metaHuman.setMetaHumanSecretName(rs.getString("MetaHumanOrganization"));
            
            return metaHuman;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            organization.setOrganizationName(rs.getString("OrganizationName"));
            organization.setOrganizationDescription(rs.getString("OrganizationDescription"));
            organization.setLocationID(rs.getInt("LocationID"));
            
            return organization;
            
        }
        
        
      
        
    }
        
        
   

    
}
