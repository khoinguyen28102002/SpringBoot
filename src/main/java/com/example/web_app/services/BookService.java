package com.example.web_app.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.web_app.dto.BookRequestDTO;
import com.example.web_app.dto.BookResponseDTO;
import com.example.web_app.exceptions.ApiException;
import com.example.web_app.mapper.MapperBook;
import com.example.web_app.models.Book;
import com.example.web_app.respositories.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    public List<BookResponseDTO> getAllBooks(){
        // List<Book> allBooks = bookRepo.findAll();
        // List<BookResponseDTO> allBooksDTO = new ArrayList<>();
        // for(Book b: allBooks){
        //     allBooksDTO.add(MapperBook.modelToResponseDto(b));
        // }
        // return allBooksDTO;
        return bookRepo.findAll().stream()
                .map(MapperBook::modelToResponseDto)
                .toList();
    }

    public List<BookResponseDTO> getBookByName(String name){
        if(name == ""){
            throw new ApiException("No id field!", HttpStatus.BAD_REQUEST);
        }
        
        return bookRepo.findBookByName(name).stream()
                .map(MapperBook::modelToResponseDto)
                .toList();
    }
    
    public BookResponseDTO addNewBook(BookRequestDTO book){
        Book modelBook = MapperBook.dtoRequestToModel(book);
        return MapperBook.modelToResponseDto(bookRepo.save(modelBook));
    }

    public BookResponseDTO updateBook(String id, BookRequestDTO book){
        Book repositoryBook = bookRepo.findById(id).orElseThrow(
            () -> new ApiException("Not found book by id: " + id, HttpStatus.NOT_FOUND)
        );

        repositoryBook.setName(book.getName());
        repositoryBook.setAuthor(book.getAuthor());
        repositoryBook.setPrice(book.getPrice());

        Book savedBook = bookRepo.save(repositoryBook);

        return MapperBook.modelToResponseDto(savedBook);
    }

    public void deleteBook(String id){
        bookRepo.deleteById(id);
    }
}
