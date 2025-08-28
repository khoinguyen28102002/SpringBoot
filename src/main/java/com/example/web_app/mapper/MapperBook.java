package com.example.web_app.mapper;

import org.modelmapper.ModelMapper;

import com.example.web_app.dto.BookRequestDTO;
import com.example.web_app.dto.BookResponseDTO;
import com.example.web_app.models.Book;

public class MapperBook {

    public static final ModelMapper mapper = new ModelMapper();

    public static Book dtoRequestToModel(BookRequestDTO dto){
        return mapper.map(dto, Book.class);
    }

    public static BookResponseDTO modelToResponseDto(Book book) {
        return mapper.map(book, BookResponseDTO.class);
    }
}
