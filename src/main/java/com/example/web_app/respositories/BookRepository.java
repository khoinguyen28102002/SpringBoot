package com.example.web_app.respositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.web_app.models.Book;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;


@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String TABLE_NAME = "postgresql.public.book";

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getString("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPrice(rs.getString("price"));
        return book;
    };

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM postgresql.public.book", bookRowMapper);
    }

    public List<Book> findBookByName(String name){
        return jdbcTemplate.query(
            "SELECT * FROM " + TABLE_NAME + " WHERE name = ?", 
            bookRowMapper, 
            name
        );
    }
    public Book findById(String id){
        try{
            return jdbcTemplate.queryForObject(
                "SELECT * FROM " + TABLE_NAME + " WHERE id = ?", 
                bookRowMapper, 
                id
            );
        }catch(EmptyResultDataAccessException e) {
            return null;
        }

    }
    public Book addNewBook(Book book){
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO " + TABLE_NAME + " (id, name, author, price) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(
            sql,
            id,
            book.getName(),
            book.getAuthor(),
            book.getPrice()
        );

        book.setId(id);
        return book;
    }
    public Book updateBook(Book book) {
        String sql = "UPDATE " + TABLE_NAME + " SET name = ?, author = ?, price = ? WHERE id = ?";
        
        int rowsAffected = jdbcTemplate.update(sql, 
            book.getName(), 
            book.getAuthor(), 
            book.getPrice(), 
            book.getId()
        );

        if (rowsAffected == 0) {
            throw new RuntimeException("Book with id " + book.getId() + " not found");
        }

        return findById(book.getId());
    }

    public void deleteById(String id){
        jdbcTemplate.update(
            "DELETE FROM " + TABLE_NAME + " WHERE id = ?", id
        );
    }

    
}
