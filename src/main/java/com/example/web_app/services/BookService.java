package com.example.web_app.services;

import java.util.ArrayList;
import java.util.List;

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
        List<Book> allBooks = bookRepo.getAllBooks();
        List<BookResponseDTO> allBooksDTO = new ArrayList<>();
        for(Book b: allBooks){
            allBooksDTO.add(MapperBook.modelToResponseDto(b));
        }
        return allBooksDTO;
    }

    public BookResponseDTO getBookByName(String name){
        if(name == ""){
            throw new ApiException("Name book is not set", HttpStatus.BAD_REQUEST);
        }
        Book repoBook = bookRepo.getBookByName(name);
        if(repoBook == null){
            throw new ApiException("Not found book by name: " + name, HttpStatus.NOT_FOUND);
        }
        return MapperBook.modelToResponseDto(repoBook);
    }
    public BookResponseDTO addNewBook(BookRequestDTO book){
        Book modelBook = MapperBook.dtoRequestToModel(book);
        return MapperBook.modelToResponseDto(bookRepo.createBook(modelBook));
    }

    public BookResponseDTO updateBook(String id, BookRequestDTO book){
        Book modelBook = MapperBook.dtoRequestToModel(book);
        Book repositoryBook = bookRepo.updateBook(id, modelBook);

        if (repositoryBook == null) {
            throw new ApiException("Not found book by id: " + id, HttpStatus.NOT_FOUND);
        }

        return MapperBook.modelToResponseDto(repositoryBook);
    }

    public void deleteBook(String id){
        bookRepo.deleteBook(id);
    }
}
