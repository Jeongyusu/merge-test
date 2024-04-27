package shop.mtcoding.marketkurly.usercoupon;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.coupon.Coupon;

public class UserCouponResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class UserCouponListDTO {

        private List<UserCouponDTO> userCouponDTOs;

        public UserCouponListDTO(List<UserCoupon> userCoupons) {
            this.userCouponDTOs = userCoupons.stream()
                    .map(t -> new UserCouponDTO(t.getCoupon()))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @ToString
        public class UserCouponDTO {
            private Integer couponId;
            private String couponName;
            private String couponContent;
            private Integer reduceAmount;
            private Boolean isExpired;

            public UserCouponDTO(Coupon coupon) {
                this.couponId = coupon.getId();
                this.couponName = coupon.getCouponName();
                this.couponContent = coupon.getCouponContent();
                this.reduceAmount = coupon.getReduceAmount();
                this.isExpired = coupon.getIsExpired();
            }

        }
    }
}
