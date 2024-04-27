package shop.mtcoding.marketkurly.writeablereview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteableReviewJPARepository extends JpaRepository<WriteableReview, Integer> {

    List<WriteableReview> findByUserId(Integer sessionUserId);

}