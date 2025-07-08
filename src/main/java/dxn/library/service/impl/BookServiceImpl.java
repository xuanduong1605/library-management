package dxn.library.service.impl;

import dxn.library.dto.request.AuthorCreationRequest;
import dxn.library.dto.request.BookCreationRequest;
import dxn.library.dto.request.CategoryCreationRequest;
import dxn.library.dto.response.AuthorResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.dto.response.CategoryResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import dxn.library.model.Author;
import dxn.library.model.Book;
import dxn.library.model.Category;
import dxn.library.repository.AuthorRepository;
import dxn.library.repository.BookRepository;
import dxn.library.repository.CategoryRepository;
import dxn.library.repository.UserRepository;
import dxn.library.service.BookService;
import dxn.library.util.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           CategoryRepository categoryRepository,
                           BookMapper bookMapper,
                           UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponse saveBook(BookCreationRequest request) {
        Book book = bookMapper.toBook(request);

        if (bookRepository.existsByIsbn(book.getIsbn()) ) {
            throw new ApiException(ResponseCode.BOOK_EXISTED);
        }

        Author currentAuthor = book.getAuthor();
        Optional<Author> author = authorRepository.findByName(currentAuthor.getName());
        if (author.isPresent()) {
            book.setAuthor(author.get());
        } else {
            authorRepository.save(currentAuthor);
        }

        Set<Category> categories = new HashSet<Category>();
        for (Category requestCategories : request.getCategories()) {
            Optional<Category> category = categoryRepository.findByName(requestCategories.getName());
            if (category.isPresent()) {
                categories.add(category.get());
            } else {
                categories.add(categoryRepository.save(requestCategories));
            }
        }
        book.setCategories(categories);

        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    @Override
    public AuthorResponse saveAuthor(AuthorCreationRequest request) {
        Author author = bookMapper.toAuthor(request);
        if (authorRepository.existsByName(author.getName())) {
            throw new ApiException(ResponseCode.AUTHOR_EXISTED);
        }
        return bookMapper.toAuthorResponse(authorRepository.save(author));
    }

    @Override
    public CategoryResponse saveCategory(CategoryCreationRequest request) {
        Category category = bookMapper.toCategory(request);
        if (categoryRepository.existsByName(category.getName())) {
            throw new ApiException(ResponseCode.CATEGORY_EXISTED);
        }
        return bookMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public List<BookResponse> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public List<BookResponse> getBooksByCategory(int page, int size, String categoryName) {
        Pageable pageable = PageRequest.of(page, size);
        Long categoryId = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new ApiException(ResponseCode.UNKNOWN_CATEGORY)
        ).getId();
        return bookRepository.findAllByCategories_Id(categoryId, pageable)
                .stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public List<BookResponse> getBooksByName(int page, int size, String bookName) {
        Pageable pageable = PageRequest.of(page, size);
        var result = bookRepository.findByNameContainingIgnoreCase(bookName, pageable);
        return result.stream()
                .map(bookMapper::toBookResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> getCategoryByName(int page, int size, String categoryName) {
        Pageable pageable = PageRequest.of(page, size);
        var result = categoryRepository.findByNameContainingIgnoreCase(categoryName, pageable);
        return result.stream()
                .map(bookMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public List<AuthorResponse> getAuthorByName(int page, int size, String authorName) {
        Pageable pageable = PageRequest.of(page, size);
        var result = authorRepository.findByNameContainingIgnoreCase(authorName, pageable);
        return result.stream()
                .map(bookMapper::toAuthorResponse)
                .toList();
    }


    @Override
    public BookResponse findBookById(Long id) {
        return bookMapper.toBookResponse(bookRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.UNKNOWN_BOOK)));
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ApiException(ResponseCode.UNKNOWN_BOOK);
        }
        bookRepository.deleteById(id);
    }
}