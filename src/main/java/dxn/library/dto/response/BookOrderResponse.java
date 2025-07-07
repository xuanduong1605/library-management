package dxn.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOrderResponse {
    private Long id;
    private Long bookId;
    private Long userId;
    private Long librarianId;
    private String borrowedDate;
    private String dueDate;
    private String returnedDate;
}
