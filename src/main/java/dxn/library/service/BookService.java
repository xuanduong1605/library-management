package dxn.library.service;

import dxn.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void deleteByBook(Book book);
}