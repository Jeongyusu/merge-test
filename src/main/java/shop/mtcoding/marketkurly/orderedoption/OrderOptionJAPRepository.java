package shop.mtcoding.marketkurly.orderedoption;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.mtcoding.marketkurly.product.Product;

// TODO 어케쓰는겨??
public interface OrderOptionJAPRepository extends JpaRepository<OrderedOption, Integer> {
        @Query("select distinct oo.option.product " +
                        "from OrderedOption oo " +
                        "group by oo.option " +
                        "order by count(*) desc")
        Page<Product> findBestProducts(Pageable pageable);

        @Query("select  count(oo.option.product) " +
                        "from OrderedOption oo " +
                        "group by oo.option.product " +
                        "order by count(*) desc")
        Integer countBestProducts();

        List<OrderedOption> findByOrderId(Integer orderId);

        List<OrderedOption> findByOrderedProductSellerId(Integer sellerId);
}
