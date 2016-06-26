/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class DvdDaoDbImpl implements DvdDao {

    private static final String SQL_INSERT_DVD = "INSERT INTO DvdLibrary.Dvd (title, releaseDate, rating, studio, director) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_DVD = "UPDATE DvdLibrary.Dvd SET title = ?, releaseDate=?, rating=?, studio=?, director=? WHERE id=?";
    private static final String SQL_DELETE_DVD = "DELETE FROM DvdLibrary.Dvd where id = ?";
    private static final String SQL_GET_DVD = "SELECT * FROM DvdLibrary.Dvd where id = ?";
    private static final String SQL_GET_DVD_LIST = "SELECT * FROM DvdLibrary.Dvd";
//    private static final String SQL_SEARCH_BY = "SELECT * FROM DvdLibrary.Dvd WHERE ? = ?";
    private static final String SQL_SEARCH_BY_TITLE = "SELECT * FROM DvdLibrary.Dvd WHERE title = ?";
    private static final String SQL_SEARCH_BY_DIRECTOR = "SELECT * FROM DvdLibrary.Dvd WHERE director = ?";
    private static final String SQL_SEARCH_BY_RATING = "SELECT * FROM DvdLibrary.Dvd WHERE rating = ?";
    private static final String SQL_SEARCH_BY_STUDIO = "SELECT * FROM DvdLibrary.Dvd WHERE studio = ?";

    private static final String SQL_SEARCH_DATE = "SELECT * FROM DvdLibrary.Dvd WHERE releaseDate > ?";

    private JdbcTemplate jdbcTemplate;

    public DvdDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dvd create(Dvd dvd) {

        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getRating(),
                dvd.getStudio(),
                dvd.getDirector());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);  //gets next unique id

        dvd.setId(id);

        return dvd;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {

        jdbcTemplate.update(SQL_DELETE_DVD, id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dvd get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_DVD, new DvdMapper(), id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dvd> getList() {
        return jdbcTemplate.query(SQL_GET_DVD_LIST, new DvdMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Dvd dvd) {

        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate(),
                dvd.getRating(),
                dvd.getStudio(),
                dvd.getDirector(),
                dvd.getId());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dvd> searchByDate(Date date) {

        return jdbcTemplate.query(SQL_SEARCH_DATE, new DvdMapper(), date);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dvd> searchBy(String attributeType, String attributeValue) {

        switch (attributeType) {
                
                case "title":
                    return jdbcTemplate.query(SQL_SEARCH_BY_TITLE, new DvdMapper(), attributeValue);
                case "director":
                    return jdbcTemplate.query(SQL_SEARCH_BY_DIRECTOR, new DvdMapper(), attributeValue);
                case "rating":
                    return jdbcTemplate.query(SQL_SEARCH_BY_RATING, new DvdMapper(), attributeValue);
                case "studio":
                    return jdbcTemplate.query(SQL_SEARCH_BY_STUDIO, new DvdMapper(), attributeValue);
        }
        
        return null;
        
//        return jdbcTemplate.query(SQL_SEARCH_BY, new DvdMapper(), attributeType, attributeValue);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, List<Dvd>> sortByDirectorRating(String director) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public double calculateAverageDvdAge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dvd getNewestDvd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dvd getOldestDvd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {

            Dvd dvd = new Dvd();

            dvd.setId(rs.getInt("id"));  //getting the data from "id" column for this iteration through a row
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseDate(rs.getDate("releaseDate"));
            dvd.setRating(rs.getString("rating"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setDirector(rs.getString("director"));

            return dvd;

        }

    }

}
