package com.example.web_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private String id;

    private String name;
    
    private String author;
    
    private String price;

    public Book(){};
}
