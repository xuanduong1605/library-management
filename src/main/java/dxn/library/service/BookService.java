package dxn.library.service;

import dxn.library.dto.request.AuthorCreationRequest;
import dxn.library.dto.request.BookCreationRequest;
import dxn.library.dto.request.CategoryCreationRequest;
import dxn.library.dto.response.AuthorResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.dto.response.CategoryResponse;


import java.util.List;

public interface BookService {
    BookResponse saveBook(BookCreationRequest request);
    AuthorResponse saveAuthor(AuthorCreationRequest request);
    CategoryResponse saveCategory(CategoryCreationRequest request);

    List<BookResponse> getAllBooks(int page, int size);
    List<BookResponse> getBooksByCategory(int page, int size, String categoryName);
    List<BookResponse> getBooksByName(int page, int size, String bookName);
    List<CategoryResponse> getCategoryByName(int page, int size, String categoryName);
    List<AuthorResponse> getAuthorByName(int page, int size, String authorName);

    BookResponse findBookById(Long id);

    void deleteBookById(Long id);
}