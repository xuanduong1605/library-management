package dxn.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOrderUpdateRequest {
    @NotNull(message = "An order_id is required")
    private Long id;

    @NotNull(message = "Returned date is required")
    private Date returnedDate;
}