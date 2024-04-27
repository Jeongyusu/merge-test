package shop.mtcoding.marketkurly.review;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception404;
import shop.mtcoding.marketkurly.notice.Notice;
import shop.mtcoding.marketkurly.notice.NoticeJPARepository;
import shop.mtcoding.marketkurly.product.ProdcutJPARepository;
import shop.mtcoding.marketkurly.product.Product;
import shop.mtcoding.marketkurly.review.ReviewRequest.ReviewSaveDTO;
import shop.mtcoding.marketkurly.review.ReviewResponse.ReviewMainDTO;
import shop.mtcoding.marketkurly.review.ReviewResponse.SavedReviewListDTO;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.user.UserJPARepository;
import shop.mtcoding.marketkurly.writeablereview.WriteableReview;
import shop.mtcoding.marketkurly.writeablereview.WriteableReviewJPARepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewJPARepository reivewJPARepository;
    private final NoticeJPARepository noticeJPARepository;
    private final ProdcutJPARepository prodcutJPARepository;
    private final UserJPARepository userJPARepository;
    private final WriteableReviewJPARepository writeableReviewJPARepository;

    public ReviewMainDTO 상품리뷰메인(Integer productId) {

        System.out.println("상품리뷰메인 호출");
        List<Review> reviews = reivewJPARepository.findByProductId(productId);
        List<Notice> Notices = noticeJPARepository.findByNoticeType("공지");

        return new ReviewResponse.ReviewMainDTO(Notices, reviews);
    }

    public void 상품리뷰저장(ReviewSaveDTO reviewSaveDTO, Integer userId) {
        Product product = prodcutJPARepository.findById(reviewSaveDTO.getProductId()).get();
        User user = userJPARepository.findById(userId).get();

        Optional<WriteableReview> writeableReviewOP = writeableReviewJPARepository
                .findById(reviewSaveDTO.getWriteableReviewId());
        if (writeableReviewOP.isEmpty()) {
            throw new Exception404("리뷰 권한이 없습니다.");
        }

        Review review = Review.builder()
                .user(user)
                .product(product)
                .reviewContent(reviewSaveDTO.getReviewContent())
                .reviewTitle(reviewSaveDTO.getReviewTitle())
                .starCount(reviewSaveDTO.getStarCount())
                .build();
        reivewJPARepository.save(review);

        // base 변환 로직 들어가야함

        // writeableReviewJPARepository에서 삭제하는 로직 들어가야함
        // writeableReviewJPARepository.deleteById(reviewSaveDTO.getWriteableReviewId());
    }

    public SavedReviewListDTO 작성리뷰조회(Integer userId) {
        List<Review> reviews = reivewJPARepository.findByUserId(userId);
        return new SavedReviewListDTO(reviews);
    }
}
