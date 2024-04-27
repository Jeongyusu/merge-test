package shop.mtcoding.marketkurly.product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdcutJPARepository extends JpaRepository<Product, Integer> {

    int countByProductUploadedAtBetween(LocalDate monthAgo, LocalDate now);

    int countByDiscountExpiredAtBetween(LocalDate now, LocalDate oneWeekAfter);

    Page<Product> findByProductUploadedAtBetweenOrderByProductUploadedAtDesc(LocalDate monthAgo, LocalDate now,
            Pageable pageable);

    Page<Product> findByDiscountExpiredAtBetween(LocalDate now, LocalDate oneWeekAfter, Pageable pageable);

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    int countByCategoryId(Integer categoryId);

    @EntityGraph(attributePaths = { "seller" })
    Optional<Product> findById(Integer productId);

    List<Product> findBySellerId(Integer userId);

    @Query(value = "SELECT * FROM product_tb WHERE product_name LIKE %:keyword%", nativeQuery = true)
    List<Product> findBySearchKeyword(@Param("keyword") String keyword);
}
