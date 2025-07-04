package dxn.library.service;

import dxn.library.dto.request.BookOrderCreationRequest;
import dxn.library.dto.request.BookOrderUpdateRequest;
import dxn.library.dto.response.BookOrderResponse;

public interface BookOrderService {
    BookOrderResponse saveBookOrder(BookOrderCreationRequest request);
    BookOrderResponse updateBookOrder(BookOrderUpdateRequest request);
}
