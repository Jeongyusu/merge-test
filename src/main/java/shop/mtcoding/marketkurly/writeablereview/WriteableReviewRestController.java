package shop.mtcoding.marketkurly.writeablereview;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.writeablereview.WriteableReviewResponse.WriteableReviewListDTO;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WriteableReviewRestController {

    private final WriteableReviewService writeableReviewService;
    private final HttpSession session;

    @GetMapping("/api/reviews/writeable")
    public ResponseEntity<?> 작성가능리뷰() {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        WriteableReviewListDTO dto = writeableReviewService.작성가능리뷰(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(dto));

    }

}
