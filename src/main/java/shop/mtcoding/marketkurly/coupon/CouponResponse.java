package shop.mtcoding.marketkurly.coupon;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CouponResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CouponListDTO {

        private List<CouponDTO> eCouponListDTOs;
        private List<CouponDTO> couponListDTOs;

        public CouponListDTO(List<Coupon> coupons) {
            this.couponListDTOs = coupons.stream()
                    .filter(coupon -> !coupon.getIsExpired())
                    .map(coupon -> new CouponDTO(coupon))
                    .collect(Collectors.toList());

            this.eCouponListDTOs = coupons.stream()
                    .filter(coupon -> coupon.getIsExpired())
                    .map(coupon -> new CouponDTO(coupon))
                    .collect(Collectors.toList());
        }

        @ToString
        @Setter
        @Getter
        @NoArgsConstructor
        public static class CouponDTO {

            private Integer id;
            private String couponName;
            private String couponNumber;
            private Integer reduceAmount;
            private Boolean isExpired;
            private LocalDate couponCreatedAt;
            private LocalDate couponExpiredAt;
            private Integer couponCount;

            public CouponDTO(Coupon coupon) {
                this.id = coupon.getId();
                this.couponName = coupon.getCouponName();
                this.couponNumber = coupon.getCouponNumber();
                this.reduceAmount = coupon.getReduceAmount();
                this.isExpired = coupon.getIsExpired();
                this.couponCreatedAt = coupon.getCouponCreatedAt();
                this.couponExpiredAt = coupon.getCouponExpiredAt();
                this.couponCount = coupon.getCouponCount();
            }

        }
    }
}
