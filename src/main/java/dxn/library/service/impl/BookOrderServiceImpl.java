package dxn.library.service.impl;

import dxn.library.dto.request.BookOrderCreationRequest;
import dxn.library.dto.request.BookOrderUpdateRequest;
import dxn.library.dto.response.BookOrderResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import dxn.library.model.Book;
import dxn.library.model.BookOrder;
import dxn.library.model.User;
import dxn.library.repository.BookOrderRepository;
import dxn.library.repository.BookRepository;
import dxn.library.repository.UserRepository;
import dxn.library.service.BookOrderService;
import dxn.library.util.mapper.BookMapper;
import dxn.library.util.mapper.BookOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookOrderServiceImpl implements BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final BookOrderMapper bookOrderMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository,
                                BookOrderMapper bookOrderMapper,
                                BookRepository bookRepository,
                                UserRepository userRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookOrderMapper = bookOrderMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookOrderResponse saveBookOrder(BookOrderCreationRequest request) {
        BookOrder bookOrder = bookOrderMapper.toBookOrder(request);

        Optional<User> user = userRepository.findById(bookOrder.getUserId());
        if (user.isEmpty()) {
            throw new ApiException(ResponseCode.UNKNOWN_USER);
        }

        Optional<Book> book = bookRepository.findById(bookOrder.getBookId());
        if (book.isEmpty()) {
            throw new ApiException(ResponseCode.UNKNOWN_BOOK);
        }

        return bookOrderMapper.toBookOrderResponse(bookOrderRepository.save(bookOrder));
    }

    public BookOrderResponse updateBookOrder(BookOrderUpdateRequest request) {
        Optional<BookOrder> bookOrder = bookOrderRepository.findById(request.getId());

        if (bookOrder.isEmpty()) {
            throw new ApiException(ResponseCode.UNKNOWN_BOOK_ORDER);
        }

        bookOrder.get().setReturnedDate(request.getReturnedDate());

        return bookOrderMapper.toBookOrderResponse(bookOrderRepository.save(bookOrder.get()));
    }
}
