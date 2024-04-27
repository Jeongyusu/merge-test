package shop.mtcoding.marketkurly.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.notice.Notice;
import shop.mtcoding.marketkurly.reviewpic.ReviewPic;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewResponse {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class ReviewMainDTO {

        // private List<ReviewNoticeDTO> reviewNotices;
        private List<ReviewDetailDTO> reviewDetails;

        public ReviewMainDTO(List<Notice> notices, List<Review> reviews) {
            // this.reviewNotices = notices.stream().map(t -> new
            // ReviewNoticeDTO(t)).collect(Collectors.toList());
            this.reviewDetails = reviews.stream().map(t -> new ReviewDetailDTO(t)).collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class ReviewNoticeDTO {
            private String noticeType;
            private String noticeTitle;
            private String noticeAdmin; // 공지 작성자
            private Timestamp noticeCreatedAt;

            public ReviewNoticeDTO(Notice notice) {
                this.noticeType = notice.getNoticeType();
                this.noticeTitle = notice.getNoticeTitle();
                this.noticeAdmin = "마켓컬리 관리자";
                this.noticeCreatedAt = notice.getNoticeCreatedAt();
            }
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class ReviewDetailDTO {
            private String username;
            private Integer starCount;
            private String productName;
            private LocalDate reviewCreatedAt;
            private List<ReviewPic> reviewPics;
            private String reviewContent;

            public ReviewDetailDTO(Review review) {
                this.username = review.getUser().getUsername();
                this.starCount = review.getStarCount();
                this.productName = review.getProduct().getProductName();
                this.reviewCreatedAt = review.getReviewCreatedAt().toLocalDateTime().toLocalDate();
                this.reviewPics = review.getReviewPics();
                this.reviewContent = review.getReviewContent();
            }

        }
    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SavedReviewListDTO {

        private List<SavedReview> savedReviews;

        public SavedReviewListDTO(List<Review> reviews) {
            this.savedReviews = reviews.stream().map(t -> new SavedReview(t)).collect(Collectors.toList());
        }

        @ToString
        @Getter
        @Setter
        @NoArgsConstructor
        public class SavedReview {

            private Integer reviewId;
            private Integer userId;
            private Integer productId;
            private String productName;
            private String productThumbnail;
            private String reviewTitle;
            private String reviewContent;
            private Integer starCount;
            private LocalDate reviewCreatedAt;
            private List<ReviewPic> reviewPics = null;

            public SavedReview(Review review) {
                this.reviewId = review.getId();
                this.userId = review.getUser().getId();
                this.productId = review.getProduct().getId();
                this.productName = review.getProduct().getProductName();
                this.productThumbnail = review.getProduct().getProductThumbnail();
                this.reviewTitle = review.getReviewTitle();
                this.reviewContent = review.getReviewContent();
                this.starCount = review.getStarCount();
                this.reviewCreatedAt = review.getReviewCreatedAt().toLocalDateTime().toLocalDate();
                this.reviewPics = review.getReviewPics();
            }
        }

    }

}