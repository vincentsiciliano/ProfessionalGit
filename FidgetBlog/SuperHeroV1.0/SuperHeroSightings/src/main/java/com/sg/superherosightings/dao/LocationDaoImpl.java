/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class LocationDaoImpl implements LocationDao {
    
     /////INSERT
    private static final String SQL_INSERT_LOCATION = "insert into Location (Longitude, Latitude, LocationName, LocationDescription, Address) values(?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_LOCATION = "select * from Location where LocationID = ?";
    
    /////SELECT ALL
    private static final String SQL_SELECT_ALL_LOCATION = "select * from Location";
    
    /////UPDATE
    private static final String SQL_UPDATE_LOCATION = "update Location set Longitude = ?, Latitude = ?, LocationName = ?, LocationDescription = ?, Address = ? where LocationID = ?";

    /////DELETE
    private static final String SQL_DELETE_LOCATION = "delete from Location where LocationID = ?";
    
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public LocationDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Whereabout location){
        
        jdbcTemplate.update(SQL_INSERT_LOCATION, location.getLongitude(), location.getLatitude(), location.getWhereaboutName(), location.getWhereaboutDescription(), location.getAddress());
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setWhereaboutId(locationId);
    }

    @Override
    public void edit(Whereabout location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getLongitude(), location.getLatitude(), location.getWhereaboutName(), location.getWhereaboutDescription(), location.getAddress(), location.getWhereaboutId());
        
    }   
        
    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
    }

    @Override
    public Whereabout getOne(int id) {
        try{
            Whereabout location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), id);
            return location;
            
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Whereabout> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION, new LocationMapper());
        
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
