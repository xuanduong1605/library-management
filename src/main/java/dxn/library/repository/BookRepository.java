package dxn.library.repository;

import dxn.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String name);
    Page<Book> findAllByCategories_Id(Long categoriesId, Pageable pageable);
}
