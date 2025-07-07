package dxn.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "expired_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpiredToken {
    @Id
    private String token;
    private Date expiryDate;
}
