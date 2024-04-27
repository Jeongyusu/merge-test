package shop.mtcoding.marketkurly.productreply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ProductReplyRequest {

    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PReplySaveDTO {

        private Integer sellerQuestionId;
        private String replyContent;

        public PReplySaveDTO(Integer sellerQuestionId, String replyContent) {
            this.sellerQuestionId = sellerQuestionId;
            this.replyContent = replyContent;
        }

    }
}
