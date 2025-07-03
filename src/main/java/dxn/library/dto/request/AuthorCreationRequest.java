package dxn.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorCreationRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private Date dateOfBirth;

    private String nationality;

    private String description;
}
