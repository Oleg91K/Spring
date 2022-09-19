package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import org.springframework.lang.Nullable;

public interface Storage {
    Book addBook(Book book,Long userId);
    User addUser(User user);
    void deleteUser(Long userId);
    void deleteBook(Long bookId);
    User updateUser(User user);
    Book updateBook(Book book, Long userId);
    @Nullable
    User getUserById(Long id);
    @Nullable
    Book getBookById(Long id);
}
