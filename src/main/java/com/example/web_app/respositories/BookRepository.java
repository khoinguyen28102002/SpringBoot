package com.example.web_app.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.web_app.models.Book;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findBookByName(String name);
}
