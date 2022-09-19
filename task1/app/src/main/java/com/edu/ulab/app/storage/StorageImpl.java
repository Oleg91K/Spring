package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.Entity;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.BookIsUsedException;
import com.edu.ulab.app.exception.NotFoundException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class StorageImpl implements Storage{
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции - entity

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Book> books = new ArrayList<>();

    public StorageImpl() {
    }

    public void addBookToUser(Long bookId, Long userId){
        Book book = getBookById(bookId);
        if(book == null){
            throw new NotFoundException("Book not found by Id: " + bookId);
        }
        if(book.getUser() != null){
            throw new BookIsUsedException("Book: " + book.getId() + "is used by User: " + book.getUser().getId());
        }
        User user = getUserById(userId);
        if(user == null){
            throw new NotFoundException("User not found by Id:" + userId);
        }
        user.addBook(book);
        book.setUser(user);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = getBookById(bookId);
        if(book == null){
            throw new NotFoundException("Book not found id");
        }
        if(book.getUser() != null){
            book.getUser().getBooks().remove(book);
        }
        books.remove(book);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        if(user == null){
            throw new NotFoundException("User not found id");
        }
        if(user.getBooks() != null){
            user.getBooks().forEach(b-> b.setUser(null));
        }
       users.remove(user);
    }


    public Book addBook(Book book,Long userId){
        if(userId!= null){
            User user = getUserById(userId);
            if(user !=null){
                book.setUser(user);
                user.addBook(book);
            }else{
                throw new NotFoundException("User not find by ID: " + userId);
            }
        }
        books.add(book);
        return book;
    }
    public Book updateBook(Book book, Long userId){
        Book savedBook = getBookById(book.getId());
        if(savedBook == null){
            throw new NotFoundException("SavedBook not found:");
        }
        if(userId != null){
           User user = getUserById(userId);
           if(user == null){
               throw new NotFoundException("User not found");
           }else{
               savedBook.getUser().getBooks().remove(book);
               savedBook.setUser(user);
               user.addBook(book);
           }
        }
        if(book.getTitle() != null){
            savedBook.setTitle(book.getTitle());
        }
        if(book.getAuthor() != null){
            savedBook.setAuthor(book.getTitle());
        }
        if(book.getPageCount() != null){
            savedBook.setPageCount(book.getPageCount());
        }
        return savedBook;
    }
    public User updateUser(User user) {
        User savedUser = getUserById(user.getId());
        if (savedUser == null) {
            throw new NotFoundException("User not found");
        }
        if (user.getFullName() != null) {
            savedUser.setFullName(user.getFullName());
        }
        if (user.getTitle() != null) {
            savedUser.setTitle(user.getTitle());
        }
        if (user.getAge() != null){
            savedUser.setAge(user.getAge());
        }
        return savedUser;
    }

    public User addUser(User user){
        users.add(user);
        return user;
    }

    @Nullable
    public User getUserById(Long id){
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Nullable
    public Book getBookById(Long id){
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void init(){
        Book book = new Book();
        book.setAuthor("Федор Достоевский");
        book.setTitle("Преступелние и наказание");
        book.setPageCount(200L);
        Book book1 = new Book();
        book1.setAuthor("Джордж Оруэлл");
        book1.setTitle("1984");
        book1.setPageCount(2100L);
        Book book2 = new Book();
        book2.setAuthor("Джон Стейнбек");
        book2.setTitle("Гроздья гнева");
        book2.setPageCount(250L);

        User user = new User();
        user.setFullName("Антон Иванов");
        user.setTitle("Человек");
        user.setAge(20);
        user.addBook(book);
        book.setUser(user);

        User user2 = new User();
        user2.setFullName("Дмитрий Чистота");
        user2.setTitle("Человек");
        user2.setAge(30);
        user2.addBook(book1);
        book.setUser(user2);

        User user3 = new User();
        user3.setFullName("Николай Сковорцов");
        user3.setTitle("Человек");
        user3.setAge(35);
        user3.addBook(book2);
        book.setUser(user3);

        users.add(user);
        users.add(user2);
        users.add(user3);

        books.add(book);
        books.add(book1);
        books.add(book2);

    }
}
