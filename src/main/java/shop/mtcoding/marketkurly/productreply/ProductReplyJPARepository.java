package shop.mtcoding.marketkurly.productreply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductReplyJPARepository extends JpaRepository<ProductReply, Integer> {

    @Modifying
    @Query(value = "UPDATE PRODUCT_QUESTION_TB SET IS_ANSWERED = true WHERE id = :id", nativeQuery = true)
    void updateStateById(@Param("id") Integer productQuestionId);

}
