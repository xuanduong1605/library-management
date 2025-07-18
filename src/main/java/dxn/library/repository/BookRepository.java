package dxn.library.repository;

import dxn.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String name);

    @Query("""
      SELECT DISTINCT b
      FROM Book b
      LEFT JOIN b.author a
      LEFT JOIN b.categories c
      WHERE (
            LOWER(b.name) LIKE LOWER(CONCAT('%',:q,'%'))
         OR LOWER(a.name) LIKE LOWER(CONCAT('%',:q,'%'))
         OR LOWER(c.name) = LOWER(:q)
      )
    """
    )
    Page<Book> findAllByQuery(@Param("q") String query, Pageable pageable);
}
