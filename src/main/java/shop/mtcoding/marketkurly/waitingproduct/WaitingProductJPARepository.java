package shop.mtcoding.marketkurly.waitingproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaitingProductJPARepository extends JpaRepository<WaitingProduct, Integer> {

    List<WaitingProduct> findBySellerId(Integer userId);

    @Modifying
    @Query(value = "update WAITING_PRODUCT_TB  set STATE = '거절됨'  where id = :wProductId", nativeQuery = true)
    void rejectProduct(@Param("wProductId") Integer wProductId);

    List<WaitingProduct> findByState(String state);
}
