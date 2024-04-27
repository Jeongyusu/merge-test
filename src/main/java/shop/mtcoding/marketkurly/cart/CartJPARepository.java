package shop.mtcoding.marketkurly.cart;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartJPARepository extends JpaRepository<Cart, Integer> {

    @EntityGraph(attributePaths = { "user", "option" })
    List<Cart> findByUser_Id(Integer userId);

    void deleteByIdAndUserId(Integer cartId, Integer userId);
}
