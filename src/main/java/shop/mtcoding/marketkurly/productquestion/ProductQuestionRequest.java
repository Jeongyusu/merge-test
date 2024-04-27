package shop.mtcoding.marketkurly.productquestion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProductQuestionRequest {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class ProductQuestionSaveDTO {

        private Integer productId;
        private String productQuestionTitle;
        private String productQuestionContent;
        private Boolean isSecreted;

        public ProductQuestionSaveDTO(Integer productId, String productQuestionTitle, String productQuestionContent,
                Boolean isSecreted) {
            this.productId = productId;
            this.productQuestionTitle = productQuestionTitle;
            this.productQuestionContent = productQuestionContent;
            this.isSecreted = isSecreted;
        }
    }
}