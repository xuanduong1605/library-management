package dxn.library.controller;

import dxn.library.dto.request.*;
import dxn.library.dto.response.*;
import dxn.library.model.BookOrder;
import dxn.library.service.BookOrderService;
import dxn.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/books")
public class BookController {

    private final BookService bookService;
    private final BookOrderService bookOrderService;

    @Autowired
    public BookController(BookService bookService, BookOrderService bookOrderService) {
        this.bookService = bookService;
        this.bookOrderService = bookOrderService;
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

    @PostMapping("/order")
    ApiResponse<BookOrderResponse> createBookOrder(@RequestBody @Valid BookOrderCreationRequest request) {
        return ApiResponse.<BookOrderResponse>builder()
                .result(bookOrderService.saveBookOrder(request))
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

    @PutMapping("/order")
    ApiResponse<BookOrderResponse> updateBookOrder(@RequestBody @Valid BookOrderUpdateRequest request) {
        return ApiResponse.<BookOrderResponse>builder()
                .result(bookOrderService.updateBookOrder(request))
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