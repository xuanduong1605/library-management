package dxn.library.util.mapper;

import dxn.library.dto.request.AuthorCreationRequest;
import dxn.library.dto.request.BookCreationRequest;
import dxn.library.dto.request.CategoryCreationRequest;
import dxn.library.dto.response.AuthorResponse;
import dxn.library.dto.response.BookResponse;
import dxn.library.dto.response.CategoryResponse;
import dxn.library.model.Author;
import dxn.library.model.Book;
import dxn.library.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book toBook(BookCreationRequest request);

    BookResponse toBookResponse(Book book);

    @Mapping(target = "id", ignore = true)
    Author toAuthor(AuthorCreationRequest request);

    AuthorResponse toAuthorResponse(Author author);

    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryCreationRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
