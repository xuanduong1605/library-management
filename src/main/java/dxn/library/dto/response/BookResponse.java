package dxn.library.dto.response;

import dxn.library.model.Author;
import dxn.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private String isbn;
    private AuthorResponse author;
    private Set<CategoryResponse> categories;
}
