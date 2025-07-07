package dxn.library.repository;

import dxn.library.model.BookOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
    Page<BookOrder> findBookOrderByUserId(Long userId, Pageable pageable);

    @Query("SELECT b FROM BookOrder b WHERE b.returnedDate IS NULL AND b.dueDate < :currentDate")
    Page<BookOrder> findOverdueBooks(Date currentDate, Pageable pageable);
}
