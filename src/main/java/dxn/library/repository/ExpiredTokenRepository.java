package dxn.library.repository;

import dxn.library.model.ExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiredTokenRepository extends JpaRepository<ExpiredToken, String> {
}
