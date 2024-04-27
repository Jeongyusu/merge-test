package shop.mtcoding.marketkurly.coupon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CouponRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CouponSaveDTO {

        private String couponName;
        private String couponContent;
        private Integer reduceAmount;
        private String couponCreatedAt;
        private String couponExpiredAt;
        private Boolean isExpired;

        @Builder
        public CouponSaveDTO(String couponName, String couponContent, Integer reduceAmount, String couponCreatedAt,
                String couponExpiredAt, Boolean isExpired) {
            this.couponName = couponName;
            this.couponContent = couponContent;
            this.reduceAmount = reduceAmount;
            this.couponCreatedAt = couponCreatedAt;
            this.couponExpiredAt = couponExpiredAt;
            this.isExpired = isExpired;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class CouponRegisterDTO {
            private String couponNumber;
        }
    }
}
