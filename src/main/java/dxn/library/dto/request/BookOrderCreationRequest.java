package dxn.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOrderCreationRequest {
    @NotNull(message = "An user_id is required")
    private Long userId;

    @NotNull(message = "A book_id is required")
    private Long bookId;

    @NotNull(message = "A librarian_id is required")
    private Long librarianId;

    @NotNull(message = "Borrowed date is required")
    private Date borrowedDate;

    @NotNull(message = "Due date is required")
    private Date dueDate;

    private Date returnedDate;
}
