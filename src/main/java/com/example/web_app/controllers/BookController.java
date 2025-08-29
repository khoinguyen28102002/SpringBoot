package com.example.web_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.web_app.dto.BookRequestDTO;
import com.example.web_app.dto.BookResponseDTO;
import com.example.web_app.services.BookService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Getter
@Setter
@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/find")
    public List<BookResponseDTO> getBookByName(@RequestParam("name") String name) {
        return bookService.getBookByName(name);
    }

    @PostMapping("/add")
    public BookResponseDTO addBook(@RequestBody BookRequestDTO book) {
        return bookService.addNewBook(book);
    }
    
    @PutMapping("/update/{id}")
    public BookResponseDTO updateBook(@PathVariable String id, @RequestBody BookRequestDTO book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/del/{id}")
    public void deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
    }
    
    
}
