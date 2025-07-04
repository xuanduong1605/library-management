package dxn.library.util.mapper;

import dxn.library.dto.request.BookOrderCreationRequest;
import dxn.library.dto.response.BookOrderResponse;
import dxn.library.model.BookOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookOrderMapper {
    BookOrder toBookOrder(BookOrderCreationRequest request);
    BookOrderResponse toBookOrderResponse(BookOrder bookOrder);
}
