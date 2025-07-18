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
    BookResponse getBookById(Long id);

    void deleteBookById(Long id);

    List<BookResponse> searchBook(String query, int page, int size);
}