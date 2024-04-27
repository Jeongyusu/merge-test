package shop.mtcoding.marketkurly.productquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductQuestionJPARepository extends JpaRepository<ProductQuestion, Integer> {
    List<ProductQuestion> findByProductId(Integer productId);

    @Query(value = "SELECT * FROM PRODUCT_QUESTION_TB pq inner join product_tb pt on pq.product_id = pt.id where pt.user_id = :id", nativeQuery = true)
    List<ProductQuestion> findByUserId(@Param("id") Integer userId);

    @Modifying
    @Query(value = "update product_question_tb set product_reply_id = :id where id = :questionId", nativeQuery = true)
    void saveProductReplyId(@Param("id") Integer id, @Param("questionId") Integer questionId);
}
