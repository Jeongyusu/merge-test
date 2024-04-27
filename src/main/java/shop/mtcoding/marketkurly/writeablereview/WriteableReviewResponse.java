package shop.mtcoding.marketkurly.writeablereview;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class WriteableReviewResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class WriteableReviewListDTO {

        private List<WriteableReviewDTO> writeableReviewDTOs;

        public WriteableReviewListDTO(List<WriteableReview> writeableReviews) {
            this.writeableReviewDTOs = writeableReviews.stream().map(t -> new WriteableReviewDTO(t))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @ToString
        public class WriteableReviewDTO {
            private Integer writeableReviewId;
            private Integer productId;
            private String sellerName;
            private String productName;
            private String productThumbnail;
            private Timestamp orderedAt;

            public WriteableReviewDTO(WriteableReview writeableReview) {
                this.writeableReviewId = writeableReview.getId();
                this.productId = writeableReview.getId();
                this.sellerName = writeableReview.getProduct().getSeller().getUsername();
                this.productName = writeableReview.getProduct().getProductName();
                this.productThumbnail = writeableReview.getProduct().getProductThumbnail();
                this.orderedAt = writeableReview.getOrderedAt();
            }

        }
    }
}
