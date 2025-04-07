package kea.iabr.countries.repository;

import kea.iabr.countries.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> findAll() {
        String sql = "SELECT * FROM countries";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Country(rs.getLong("id"), rs.getString("name")));
    }

    public Country findByName(String name) {
        String sql = "SELECT * FROM countries WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) ->
                new Country(rs.getLong("id"), rs.getString("name")));
    }

}







