package shop.mtcoding.marketkurly.adminquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminQuestionJPARepository extends JpaRepository<AdminQuestion, Integer> {

    @Modifying
    @Query(value = "UPDATE ADMIN_QUESTION_TB SET IS_ANSWERED = true WHERE id = :id", nativeQuery = true)
    void updateStateById(@Param("id") Integer adminQuestionId);

    @Modifying
    @Query(value = "update admin_question_tb set admin_reply_id = :id where id = :questionId", nativeQuery = true)
    void saveProductReplyId(@Param("id") Integer id, @Param("questionId") Integer questionId);

}
