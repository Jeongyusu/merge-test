package shop.mtcoding.marketkurly.productquestion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.productquestion.ProductQuestionRequest.ProductQuestionSaveDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductQuestionRestController {

    private final ProductQuestionService ProductQuestionService;

    @GetMapping("/api/product/question/main/{productId}")
    public ResponseEntity<?> 상품문의메인(@PathVariable Integer productId) {
        log.info("상품문의메인 controller 호출");
        ProductQuestionResponse.ProductQuestionMainDTO productQuestionMainDTO = ProductQuestionService
                .상품문의메인(productId);
        return ResponseEntity.ok().body(ApiUtils.success(productQuestionMainDTO));
    }

    @PostMapping("/api/users/product/question/save")
    public ResponseEntity<?> 상품문의작성(@RequestBody ProductQuestionSaveDTO productQuestionSaveDTO) {
        log.info("상품문의작성 controller 호출");
        ProductQuestionSaveDTO productQuestionMainDTO = ProductQuestionService
                .상품문의작성(productQuestionSaveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(productQuestionMainDTO));
    }

}
