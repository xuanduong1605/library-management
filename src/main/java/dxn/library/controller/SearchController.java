package dxn.library.controller;

import dxn.library.dto.response.ApiResponse;
import dxn.library.dto.response.UserResponse;
import dxn.library.exception.ResponseCode;
import dxn.library.service.BookOrderService;
import dxn.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/search")
public class SearchController {
    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    ApiResponse<Map<String, Object>> searchQuery(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "query") String query
    ){
        var bookResult = bookService.getBooksByName(page, size, query);
        var authorResult = bookService.getAuthorByName(page, size, query);
        var categoryResult = bookService.getCategoryByName(page, size, query);

        if (bookResult.isEmpty() && authorResult.isEmpty() && categoryResult.isEmpty()) {
            return ApiResponse.<Map<String, Object>>builder()
                    .code(ResponseCode.NO_CONTENT.getCode())
                    .message(ResponseCode.NO_CONTENT.getMessage())
                    .result(null)
                    .build();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("books", bookResult);
        result.put("authors", authorResult);
        result.put("categories", categoryResult);

        return ApiResponse.<Map<String, Object>>builder()
                .result(result)
                .build();
    }
}
