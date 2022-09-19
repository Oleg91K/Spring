package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;
import org.springframework.lang.Nullable;

public interface BookService {
    BookDto createBook(BookDto userDto);

    BookDto updateBook(BookDto userDto);
    @Nullable
    BookDto getBookById(Long id);

    void deleteBookById(Long id);
}
