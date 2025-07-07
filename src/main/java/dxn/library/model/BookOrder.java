package dxn.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "book_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "librarian_id")
    private Long librarianId;

    private Date borrowedDate;

    private Date dueDate;

    private Date returnedDate;
}
