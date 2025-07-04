package dxn.library.service;

import dxn.library.dto.request.AuthorCreationRequest;
import dxn.library.dto.request.BookCreationRequest;
import dxn.library.dto.request.CategoryCreationRequest;
import dxn.library.dto.response.AuthorResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.dto.response.CategoryResponse;
import dxn.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookResponse saveBook(BookCreationRequest request);
    AuthorResponse saveAuthor(AuthorCreationRequest request);
    CategoryResponse saveCategory(CategoryCreationRequest request);

    List<BookResponse> getAllBooks(int page, int size);

    BookResponse findBookById(Long id);

    void deleteBookById(Long id);
}