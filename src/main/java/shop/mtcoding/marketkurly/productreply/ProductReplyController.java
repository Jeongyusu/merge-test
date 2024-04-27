package shop.mtcoding.marketkurly.productreply;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.productreply.ProductReplyRequest.PReplySaveDTO;

@RestController
@RequiredArgsConstructor
public class ProductReplyController {

    private final ProductReplyService productReplyService;

    @PostMapping("/seller/question/reply/save")
    public ResponseEntity<?> 답변저장(@RequestBody PReplySaveDTO pReplySaveDTO) {
        System.out.println("판매자 답변 저장 : " + pReplySaveDTO.getReplyContent());
        System.out.println("판매자 답변 저장 : " + pReplySaveDTO.getSellerQuestionId());
        productReplyService.답변저장(pReplySaveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(pReplySaveDTO));
    }

}
