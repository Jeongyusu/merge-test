package shop.mtcoding.marketkurly.productreply;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.productquestion.ProductQuestion;
import shop.mtcoding.marketkurly.productquestion.ProductQuestionJPARepository;
import shop.mtcoding.marketkurly.productreply.ProductReplyRequest.PReplySaveDTO;

@Service
@RequiredArgsConstructor
public class ProductReplyService {

    private final ProductQuestionJPARepository productQuestionJPARepository;
    private final ProductReplyJPARepository productReplyJPARepository;

    @Transactional
    public void 답변저장(PReplySaveDTO pReplySaveDTO) {

        ProductQuestion productQuestion = productQuestionJPARepository.findById(pReplySaveDTO.getSellerQuestionId())
                .get();
        ProductReply productReply = null;
        if (productQuestion.getProductReply() == null) {
            productReply = ProductReply.builder()
                    .productQuestion(productQuestion)
                    .pReplyContent(pReplySaveDTO.getReplyContent())
                    .build();
        } else {
            productReply = ProductReply.builder()
                    .id(productQuestion.getProductReply().getId())
                    .productQuestion(productQuestion)
                    .pReplyContent(pReplySaveDTO.getReplyContent())
                    .build();
        }
        if (!productQuestion.getIsAnswered()) {
            productReplyJPARepository.updateStateById(pReplySaveDTO.getSellerQuestionId());
        }
        productReplyJPARepository.save(productReply);
        productQuestionJPARepository.saveProductReplyId(productReply.getId(), productQuestion.getId());
    }
}
