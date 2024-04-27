package shop.mtcoding.marketkurly.usercoupon;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.usercoupon.UserCouponResponse.UserCouponListDTO;

@RequiredArgsConstructor
@Service
public class UserCouponService {

    private final UserCouponJPARepository userCouponJPARepository;

    @Transactional
    public UserCouponListDTO 쿠폰조회(Integer sessionUserId) {
        List<UserCoupon> userCoupons = userCouponJPARepository.findByUserId(sessionUserId);
        return new UserCouponListDTO(userCoupons);
    }

}
