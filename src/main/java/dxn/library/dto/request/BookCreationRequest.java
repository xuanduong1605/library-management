package dxn.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreationRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Author is required")
    private AuthorCreationRequest author;

    @NotNull(message = "Categories are required")
    private Set<CategoryCreationRequest> categories;

    private String description;
}
