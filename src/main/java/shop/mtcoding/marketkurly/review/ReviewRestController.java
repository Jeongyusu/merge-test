package shop.mtcoding.marketkurly.review;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.review.ReviewRequest.ReviewSaveDTO;
import shop.mtcoding.marketkurly.review.ReviewResponse.SavedReviewListDTO;
import shop.mtcoding.marketkurly.user.User;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService ReviewService;
    private final HttpSession session;

    @GetMapping("/api/product/reviews/{productId}")
    public ResponseEntity<?> 상품리뷰메인(@PathVariable Integer productId) {
        log.info("상품리뷰메인 controller 호출");
        ReviewResponse.ReviewMainDTO reviewMainDTO = ReviewService.상품리뷰메인(productId);
        return ResponseEntity.ok().body(ApiUtils.success(reviewMainDTO));
    }

    @PostMapping("/api/reviews/save")
    public ResponseEntity<?> 상품리뷰저장(@RequestBody ReviewSaveDTO reviewSaveDTO) {
        // log.info("상품리뷰저장 controller 호출");
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        ReviewService.상품리뷰저장(reviewSaveDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(reviewSaveDTO));
    }

    @GetMapping("/api/reviews/saved")
    public ResponseEntity<?> 작성리뷰조회() {
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        SavedReviewListDTO dto = ReviewService.작성리뷰조회(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(dto));
    }

}
