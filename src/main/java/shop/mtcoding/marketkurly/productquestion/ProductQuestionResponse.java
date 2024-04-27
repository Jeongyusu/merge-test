package shop.mtcoding.marketkurly.productquestion;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.product.Product;
import shop.mtcoding.marketkurly.productreply.ProductReply;

public class ProductQuestionResponse {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class ProductQuestionMainDTO {

        private Integer productId;
        private String productName;
        private List<ProductQuestionDTO> productQuestionDTOs;

        public ProductQuestionMainDTO(Product product, List<ProductQuestion> productQuestions) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productQuestionDTOs = productQuestions.stream().map(t -> new ProductQuestionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class ProductQuestionDTO {

            private Integer productQuestionId;
            private String productQuestionTitle;
            private String productQuestionContent;
            private Boolean isAnswered;
            private Boolean isSecreted;
            private String userName;
            private String pReplyContent;
            private LocalDate productQuestionCreatedAt;

            public ProductQuestionDTO(ProductQuestion productQuestion) {
                this.productQuestionId = productQuestion.getId();
                this.productQuestionTitle = productQuestion.getProductQuestionTitle();
                this.productQuestionContent = productQuestion.getProductQuestionContent();
                this.isAnswered = productQuestion.getIsAnswered();
                this.isSecreted = productQuestion.getIsSecreted();
                this.userName = productQuestion.getUser().getUsername();
                if (productQuestion.getProductReply() != null) {
                    this.pReplyContent = productQuestion.getProductReply().getPReplyContent();
                }
                if (productQuestion.getProductReply() == null) {
                    this.pReplyContent = null;
                }
                this.productQuestionCreatedAt = productQuestion.getProductQuestionCreatedAt().toLocalDateTime()
                        .toLocalDate();
            }
        }
    }

    public static String ProductQuestionMainDTO(Product product, List<ProductQuestion> productQuestions) {
        return null;
    }

    @ToString
    @Getter
    @Setter
    public static class ProductQuestionListDTO {

        List<ProductQuestionDTO> productQuestionAnsweredDTOs;
        List<ProductQuestionDTO> productQuestionWaitingDTOs;

        public ProductQuestionListDTO(List<ProductQuestion> productQuestions) {
            this.productQuestionAnsweredDTOs = productQuestions.stream()
                    .filter(t -> Boolean.TRUE.equals(t.getIsAnswered()))
                    .map(t -> new ProductQuestionDTO(t))
                    .collect(Collectors.toList());

            this.productQuestionWaitingDTOs = productQuestions.stream()
                    .filter(t -> Boolean.FALSE.equals(t.getIsAnswered()))
                    .map(t -> new ProductQuestionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        @Setter
        public class ProductQuestionDTO {

            private Integer id;
            private Integer userId;
            private Integer productId;
            private String productName;
            private String username;
            private String productQuestionTitle;
            private Boolean isAnswered;
            private Boolean isSecreted;
            private LocalDate productQuestionCreatedAt;

            public ProductQuestionDTO(ProductQuestion t) {
                this.id = t.getId();
                this.userId = t.getUser().getId();
                this.userId = t.getUser().getId();
                this.username = t.getUser().getUsername();
                this.productQuestionTitle = t.getProductQuestionTitle();
                this.productName = t.getProduct().getProductName();
                this.isAnswered = t.getIsAnswered();
                this.isSecreted = t.getIsSecreted();
                this.productQuestionCreatedAt = t.getProductQuestionCreatedAt().toLocalDateTime().toLocalDate();
            }

        }
    }

    @ToString
    @Getter
    @Setter
    public static class ProductQuestionDetailDTO {

        ProductQuestionDTO productQuestionDTO;
        ProductReplyDTO productReplyDTO;

        public ProductQuestionDetailDTO(ProductQuestion productQuestion) {
            this.productQuestionDTO = new ProductQuestionDTO(productQuestion);
            this.productReplyDTO = new ProductReplyDTO(productQuestion.getProductReply());
        }

        @ToString
        @Getter
        @Setter
        public class ProductQuestionDTO {

            private Integer id;
            private String username;
            private String productName;
            private String productQuestionTitle;
            private String productQuestionContent;
            private Boolean isAnswered;
            private Boolean isSecreted;
            private LocalDate productQuestionCreatedAt;

            public ProductQuestionDTO(ProductQuestion t) {
                this.id = t.getId();
                this.username = t.getUser().getUsername();
                this.productName = t.getProduct().getProductName();
                this.productQuestionTitle = t.getProductQuestionTitle();
                this.productQuestionContent = t.getProductQuestionContent();
                this.isAnswered = t.getIsAnswered();
                this.isSecreted = t.getIsSecreted();
                this.productQuestionCreatedAt = t.getProductQuestionCreatedAt().toLocalDateTime().toLocalDate();
            }

        }

        @ToString
        @Getter
        @Setter
        public class ProductReplyDTO {

            private Integer id;
            private String pReplyContent;

            public ProductReplyDTO(ProductReply t) {
                if (t != null) {
                    this.id = t.getId();
                    this.pReplyContent = t.getPReplyContent();
                }
                if (t == null) {
                    this.id = 0;
                    this.pReplyContent = "댓글 없음";
                }
            }
        }
    }

}