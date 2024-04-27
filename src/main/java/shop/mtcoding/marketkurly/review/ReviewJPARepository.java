package shop.mtcoding.marketkurly.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.marketkurly.product.Product;

public interface ReviewJPARepository extends JpaRepository<Review, Integer> {

    public List<Review> findByProduct(Product product);

    public List<Review> findByProductId(Integer productId);

    public List<Review> findByUserId(Integer userId);

}
