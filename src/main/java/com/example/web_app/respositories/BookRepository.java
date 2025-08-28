package com.example.web_app.respositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.web_app.models.Book;

@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<Book>(
        List.of(
            new Book("1", "book1", "author1", "100"),
            new Book("2", "book2", "author2", "200"),
            new Book("3", "book3", "author3", "300")
        )
    );

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookByName(String name){
        for(Book b: books){
            if(name.compareTo(b.getName()) == 0){
                return b;
            }
        }
        return null;
    }
    public Book createBook(Book book){
        String newId = String.valueOf(books.size() + 1);
        book.setId(newId);
        books.add(book);
        return book;
    }

    public Book updateBook(String id, Book book){
        for(Book b: books){
            if(id.compareTo(b.getId()) == 0){
                b.setName(book.getName());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                return b;
            }
        }
        return null;
    }

    public void deleteBook(String id){
        books.removeIf(b->id.equals(b.getId()));
    }
    
}
