package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.generator.BookIdGenerator;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final Storage storage;

    public BookServiceImpl(@Autowired Storage storage) {
        this.storage = storage;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        long id = BookIdGenerator.getId();
        Book book = new Book();
        book.setId(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPageCount(bookDto.getPageCount());
        // сгенерировать идентификатор
        // создать книгу
        // вернуть сохраненную книгу со всеми необходимыми полями id
        Book savedBook = storage.addBook(book, bookDto.getUserId());
        return convert(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return convert(storage.updateBook(convert(bookDto),bookDto.getUserId()));
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = storage.getBookById(id);
        return book == null ? null : convert(book);
    }

    @Override
    public void deleteBookById(Long id) {
        storage.deleteBook(id);
    }

    public BookDto convert(Book book) {
        BookDto bookDto1 = new BookDto();
        bookDto1.setTitle(book.getTitle());
        bookDto1.setAuthor(book.getAuthor());
        bookDto1.setPageCount(book.getPageCount());
        bookDto1.setUserId(book.getId());
        return bookDto1;
    }

    public Book convert(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPageCount(bookDto.getPageCount());
        return book;
    }
}
