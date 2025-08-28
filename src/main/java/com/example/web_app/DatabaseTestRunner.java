package com.example.web_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTestRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String result = jdbcTemplate.queryForObject("SELECT version()", String.class);
            System.out.println("Kết nối database thành công! Phiên bản PostgreSQL: " + result);
        } catch (Exception e) {
            System.err.println("Kết nối database thất bại: " + e.getMessage());
        }
    }
}
