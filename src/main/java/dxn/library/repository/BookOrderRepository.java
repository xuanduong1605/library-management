package dxn.library.repository;

import dxn.library.model.BookOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    Page<BookOrder> findBookOrderByUserId(Long userId, Pageable pageable);
}
