package shop.mtcoding.marketkurly.coupon;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.coupon.CouponRequest.CouponSaveDTO.CouponRegisterDTO;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.usercoupon.UserCoupon;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponRestController {

    private final CouponService couponService;
    private final HttpSession session;

    @PostMapping("/api/users/coupon/register")
    public ResponseEntity<?> 쿠폰등록(@RequestBody CouponRegisterDTO couponRegisterDTO) {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        UserCoupon userCoupon = couponService.쿠폰등록(couponRegisterDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(userCoupon));
    }

}
