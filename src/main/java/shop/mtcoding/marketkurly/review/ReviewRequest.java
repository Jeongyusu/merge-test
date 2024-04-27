package shop.mtcoding.marketkurly.review;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ReviewRequest {

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReviewSaveDTO {

        private Integer productId;
        private Integer writeableReviewId;
        private String reviewTitle;
        private String reviewContent;
        private Integer starCount;

        // reviewPics의 pic은 Base64로 변환해서 String으로
        private List<ReviewBase> reviewPics;

        public ReviewSaveDTO(Integer productId, Integer writeableReviewId, String reviewTitle, String reviewContent,
                Integer starCount, List<ReviewBase> reviewPics) {
            this.productId = productId;
            this.writeableReviewId = writeableReviewId;
            this.reviewTitle = reviewTitle;
            this.reviewContent = reviewContent;
            this.starCount = starCount;
            this.reviewPics = reviewPics;
        }

    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReviewBase {

        String reviewBase;

        public ReviewBase(String reviewBase) {
            this.reviewBase = reviewBase;
        }

    }
}
