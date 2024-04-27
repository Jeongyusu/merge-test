package shop.mtcoding.marketkurly.usercoupon;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.usercoupon.UserCouponResponse.UserCouponListDTO;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserCouponRestController {

    private final UserCouponService userCouponService;
    private final HttpSession session;

    @GetMapping("/api/users/coupon")
    public ResponseEntity<?> 쿠폰조회() {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        UserCouponListDTO dto = userCouponService.쿠폰조회(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(dto));
    }

}
