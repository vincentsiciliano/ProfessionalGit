/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
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
public class MetaHumanDaoImpl implements MetaHumanDao {
    
     
    /////INSERT
    private final String SQL_INSERT_METAHUMAN = "insert into MetaHuman (MetaHumanName, MetaHumanSecretName) values(?, ?)";
    
    /////UPDATE
    private final String SQL_UPDATE_METAHUMAN = "update MetaHuman set MetaHumanName = ?, MetaHumanSecretName = ? where MetaHumanID = ?";
    
    /////DELETE
    private final String SQL_DELETE_METAHUMAN = "delete from MetaHuman where MetaHumanID = ?";
    private final String SQL_DELETE_METAHUMAN_ORGANIZATION = "delete from MetaHuman_Organization where MetaHumanID = ?";
    private final String SQL_DELETE_METAHUMAN_POWERS = "delete from MetaHuman_Powers where MetaHumanID = ?";
    private final String SQL_DELETE_METAHUMAN_SIGHTING = "delete from MetaHuman_Sighting where MetaHumanID = ?";
    
    /////SELECT
    private static final String SQL_SELECT_METAHUMAN = "select * from MetaHuman where MetaHumanID = ?";
    
    /////SELECTALL
    private final String SQL_SELECT_ALL_METAHUMAN = "select * from MetaHuman";
    
    
    
        
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public MetaHumanDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public MetaHumanDaoImpl(){
        
    }
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(MetaHuman metaHuman){
        
        jdbcTemplate.update(SQL_INSERT_METAHUMAN, metaHuman.getMetaHumanName(), metaHuman.getMetaHumanSecretName());
        int metaHumanId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        metaHuman.setMetaHumanID(metaHumanId);
    }

    @Override
    public void edit(MetaHuman metaHuman) {
        jdbcTemplate.update(SQL_UPDATE_METAHUMAN,metaHuman.getMetaHumanName(), metaHuman.getMetaHumanSecretName(), metaHuman.getMetaHumanID());
        
    }   
        
    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_SIGHTING, id);
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_POWERS, id);
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_METAHUMAN, id);
        
    }

    @Override
    public MetaHuman getOne(int id) {
        try{
            MetaHuman metaHuman = jdbcTemplate.queryForObject(SQL_SELECT_METAHUMAN, new MetaHumanMapper(), id);
            return metaHuman;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<MetaHuman> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN, new MetaHumanMapper());
        
    }
    
    private static final class MetaHumanMapper implements RowMapper<MetaHuman> {

        @Override
        public MetaHuman mapRow(ResultSet rs, int i) throws SQLException {
            
            MetaHuman metaHuman = new MetaHuman();
            metaHuman.setMetaHumanID(rs.getInt("MetaHumanID"));
            metaHuman.setMetaHumanName(rs.getString("MetaHumanName"));
            metaHuman.setMetaHumanSecretName(rs.getString("MetaHumanSecretName"));
                
            return metaHuman;
        }
    }
   





}