package dxn.library.controller;

import dxn.library.dto.response.ApiResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import dxn.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/search")
public class SearchController {
    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    ApiResponse<List<BookResponse>> bookSearch(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "query") String query
    ){
        var result = bookService.searchBook(query, page, size);
        if (result.isEmpty()) {
            throw new ApiException(ResponseCode.NO_CONTENT);
        }
        return ApiResponse.<List<BookResponse>>builder()
                .result(result)
                .build();
    }
}
