package dxn.library.controller;

import dxn.library.dto.request.AuthorCreationRequest;
import dxn.library.dto.request.BookCreationRequest;
import dxn.library.dto.request.CategoryCreationRequest;
import dxn.library.dto.response.ApiResponse;
import dxn.library.dto.response.AuthorResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.dto.response.CategoryResponse;
import dxn.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    ApiResponse<BookResponse> createBook(@RequestBody @Valid BookCreationRequest request) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.saveBook(request))
                .build();
    }

    @PostMapping("/author")
    ApiResponse<AuthorResponse> createBook(@RequestBody @Valid AuthorCreationRequest request) {
        return ApiResponse.<AuthorResponse>builder()
                .result(bookService.saveAuthor(request))
                .build();
    }

    @PostMapping("/category")
    ApiResponse<CategoryResponse> createBook(@RequestBody @Valid CategoryCreationRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(bookService.saveCategory(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BookResponse>> getBooks(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.getAllBooks(page, size))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BookResponse> getBook(@PathVariable("id") Long id) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.findBookById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ApiResponse.<String>builder()
                .result("Book deleted successfully")
                .build();
    }
}