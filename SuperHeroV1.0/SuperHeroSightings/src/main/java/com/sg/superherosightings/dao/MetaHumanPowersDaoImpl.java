/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.MetaHuman;
import com.sg.superherosightings.dto.MetaHumanPowers;
import com.sg.superherosightings.dto.Powers;
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
public class MetaHumanPowersDaoImpl implements MetaHumanPowersDao {
    /////INSERT
    private static final String SQL_INSERT_METAHUMAN_POWERS = "insert into MetaHuman_Powers(MetaHumanID, PowerID) values(?, ?)";
    
    
    private static final String SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_POWER = "select * from MetaHuman_Powers where MetaHumanID = ?";
    private static final String SQL_SELECT_ALL_POWERS_FROM_METAHUMAN_POWER = "select * from MetaHuman_Powers where PowerID = ?";
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_METAHUMAN_POWERS = "select * from MetaHuman_Powers";
    
    
    /////UPDATE
   
    
    
    /////DELETE
    private static final String SQL_DELETE_METAHUMAN_POWER = "delete from MetaHuman_Powers where MetaHuman_PowersID = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public MetaHumanPowersDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    


    @Override
       @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
       public void add(MetaHumanPowers metaHumanPower){

           jdbcTemplate.update(SQL_INSERT_METAHUMAN_POWERS, metaHumanPower.getMetaHuman().getMetaHumanID(), metaHumanPower.getPower().getPowerID());
           int metaHumanPowerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
           metaHumanPower.setMetaHumanPowersId(metaHumanPowerId);
       }
        
    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_METAHUMAN_POWER, id);
    }

    @Override
    public List<MetaHumanPowers> getAllWithMetaHuman(int id) {
        try{
            List<MetaHumanPowers> metaHumanPowersList = jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_FROM_METAHUMAN_POWER, new MetaHumanPowersMapper(), id);
            return metaHumanPowersList;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    
    @Override
    public List<MetaHumanPowers> getAllWithPowers(int id) {
        try{
            List<MetaHumanPowers> metaHumanPowersList = jdbcTemplate.query(SQL_SELECT_ALL_POWERS_FROM_METAHUMAN_POWER, new MetaHumanPowersMapper(), id);
            return metaHumanPowersList;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<MetaHumanPowers> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_METAHUMAN_POWERS, new MetaHumanPowersMapper());
        
    }


    
    private static final class MetaHumanPowersMapper implements RowMapper<MetaHumanPowers> {

        @Override
        public MetaHumanPowers mapRow(ResultSet rs, int i) throws SQLException {
            MetaHumanPowers metaHumanPower = new MetaHumanPowers();
            metaHumanPower.setMetaHumanPowersId(rs.getInt("MetaHuman_PowersID"));
            metaHumanPower.setMetaHumanId(rs.getInt("MetaHumanID"));
            metaHumanPower.setPowerId(rs.getInt("PowerID"));
            
            return metaHumanPower;
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
    
    private static final class PowersMapper implements RowMapper<Powers> {

        @Override
        public Powers mapRow(ResultSet rs, int i) throws SQLException {
            Powers power = new Powers();
            power.setPowerID(rs.getInt("PowerID"));
            power.setPowerDescription(rs.getString("PowerDescription"));
            
            return power;
            
        }
        
    }

    
}
