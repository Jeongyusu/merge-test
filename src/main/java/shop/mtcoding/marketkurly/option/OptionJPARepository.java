package shop.mtcoding.marketkurly.option;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.marketkurly.product.Product;

public interface OptionJPARepository extends JpaRepository<Option, Integer> {
    Optional<Option> findTopByProductOrderByOptionPriceAsc(Product product);

    List<Option> findByProductId(Integer ProductId);

}