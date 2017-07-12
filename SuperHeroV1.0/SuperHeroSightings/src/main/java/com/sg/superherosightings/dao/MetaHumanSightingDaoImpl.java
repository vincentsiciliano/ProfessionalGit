/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanSighting;
import com.sg.superherosightings.dto.Sighting;
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
public class MetaHumanSightingDaoImpl implements MetaHumanSightingDao {
/////INSERT
    private static final String SQL_INSERT_METAHUMAN_SIGHTING = "insert into MetaHuman_Sighting(MetaHumanID, SightingID) values(?, ?)";
    
    
    private static final String SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_SIGHTING = "select * from MetaHuman_Sighting where MetaHumanID = ?";
    private static final String SQL_SELECT_ALL_SIGHTING_FROM_METAHUMAN_SIGHTING = "select * from MetaHuman_Sighting where SightingID = ?";
    
    
    
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_METAHUMAN_SIGHTING = "select * from MetaHuman_Sighting";
    
    
    /////UPDATE
    
    
    
    
    
    /////DELETE
    private static final String SQL_DELETE_METAHUMAN_SIGHTING = "delete from MetaHuman_Sighting where MetaHumanID = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public MetaHumanSightingDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(MetaHumanSighting metaHumanSighting){
        
        jdbcTemplate.update(SQL_INSERT_METAHUMAN_SIGHTING, metaHumanSighting.getMetaHuman().getMetaHumanID(), metaHumanSighting.getSighting().getSightingId());
        int setMetaHumanSightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        metaHumanSighting.setMetaHumanSightingId(setMetaHumanSightingId);
    }

    @Override
    public void delete(int id) {
        
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_SIGHTING, id);
        
        
    }

    @Override
    public List<MetaHumanSighting> getAllWithMetaHuman(int id) {
        try{
            List<MetaHumanSighting> metaHumanSightingList = jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_SIGHTING, new MetaHumanSightingMapper(), id);
            return metaHumanSightingList;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    @Override
    public List<MetaHumanSighting> getAllWithSighting(int id) {
        try{
            List<MetaHumanSighting> metaHumanSightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING_FROM_METAHUMAN_SIGHTING, new MetaHumanSightingMapper(), id);
            return metaHumanSightingList;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<MetaHumanSighting> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_SIGHTING, new MetaHumanSightingMapper());
        
    }
    
    private static final class MetaHumanSightingMapper implements RowMapper<MetaHumanSighting> {

        @Override
        public MetaHumanSighting mapRow(ResultSet rs, int i) throws SQLException {
            MetaHumanSighting metaHumanSighting = new MetaHumanSighting();
            metaHumanSighting.setMetaHumanSightingId(rs.getInt("MetaHuman_SightingID"));
            metaHumanSighting.setMetaHumanId(rs.getInt("MetaHumanID"));
            metaHumanSighting.setSightingId(rs.getInt("SightingID"));
            
            return metaHumanSighting;
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
   

    
}
