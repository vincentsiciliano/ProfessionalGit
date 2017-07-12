/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class PowersDaoImpl implements PowersDao{
    
     /////INSERT
    
    private static final String SQL_INSERT_POWER = "insert into Powers (PowerDescription) value(?)";

    
    private static final String SQL_SELECT_POWER = "select * from Powers where PowerID = ?";
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_POWERS = "select * from Powers";
    
    
    /////UPDATE
    private static final String SQL_UPDATE_POWER = "update Powers set PowerDescription = ? where PowerID = ?";

    /////DELETE
    private static final String SQL_DELETE_POWER = "delete from Powers where PowerID = ?";
    
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public PowersDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Powers power){
        
        jdbcTemplate.update(SQL_INSERT_POWER, power.getPowerDescription());
        int powerId= jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        power.setPowerID(powerId);
    }

    @Override
    public void edit(Powers power) {
        jdbcTemplate.update(SQL_UPDATE_POWER, power.getPowerDescription(), power.getPowerID());
        
    }   
        
    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_POWER, id);
    }

    @Override
    public Powers getOne(int id) {
        try{
            Powers power = jdbcTemplate.queryForObject(SQL_SELECT_POWER, new PowersMapper(), id);
            return power;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Powers> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowersMapper());
        
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
