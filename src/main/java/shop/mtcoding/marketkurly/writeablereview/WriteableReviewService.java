package shop.mtcoding.marketkurly.writeablereview;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.writeablereview.WriteableReviewResponse.WriteableReviewListDTO;

@RequiredArgsConstructor
@Service
public class WriteableReviewService {

    private final WriteableReviewJPARepository writeableReviewJPARepository;

    public WriteableReviewListDTO 작성가능리뷰(Integer userId) {
        List<WriteableReview> writeableReviews = writeableReviewJPARepository.findByUserId(userId);
        return new WriteableReviewListDTO(writeableReviews);
    }

}
