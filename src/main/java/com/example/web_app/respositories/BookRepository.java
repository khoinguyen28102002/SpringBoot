package com.example.web_app.respositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.web_app.models.Book;
import java.util.List;
import java.util.UUID;


@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
            "SELECT * FROM postgresql.public.book WHERE name = ?", 
            bookRowMapper, 
            name
        );
    }
    public Book findById(String id){
        try{
            return jdbcTemplate.queryForObject(
                "SELECT * FROM postgresql.public.book WHERE id = ?", 
                bookRowMapper, 
                id
            );
        }catch(EmptyResultDataAccessException e) {
            return null;
        }

    }
    public Book addNewBook(Book book){
        String sql = "INSERT INTO book (id, name, author, price) VALUES (?, ?, ?, ?) RETURNING id";
        String generatedId = UUID.randomUUID().toString();

        String returnedId = jdbcTemplate.queryForObject(
            sql,
            String.class,
            generatedId, book.getName(), book.getAuthor(), book.getPrice()
        );

        book.setId(returnedId);
        return book;
    }
    public Book updateBook(Book book) {
        int rowsAffected = jdbcTemplate.update(
            "UPDATE book SET name = ?, author = ?, price = ? WHERE id = ? ",
            bookRowMapper,
            book.getName(),
            book.getAuthor(),
            book.getPrice(),
            book.getId()
        );
        if (rowsAffected > 0) {
            String sqlSelect = "SELECT * FROM books WHERE id = ?";
            return jdbcTemplate.queryForObject(sqlSelect, bookRowMapper, book.getId());
        } else {
            return null; 
        }
    }

    public void deleteById(String id){
        jdbcTemplate.update(
            "DELETE FROM postgres.public.book WHERE id = ?", id
        );
    }

    
}
