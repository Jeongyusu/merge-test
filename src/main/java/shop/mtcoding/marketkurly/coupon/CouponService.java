package shop.mtcoding.marketkurly.coupon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception404;
import shop.mtcoding.marketkurly.coupon.CouponRequest.CouponSaveDTO;
import shop.mtcoding.marketkurly.coupon.CouponRequest.CouponSaveDTO.CouponRegisterDTO;
import shop.mtcoding.marketkurly.coupon.CouponResponse.CouponListDTO;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.user.UserJPARepository;
import shop.mtcoding.marketkurly.usercoupon.UserCoupon;
import shop.mtcoding.marketkurly.usercoupon.UserCouponJPARepository;

@Controller
@RequiredArgsConstructor
public class CouponService {

    private final CouponJPARepository couponJPARepository;
    private final UserCouponJPARepository userCouponJPARepository;
    private final UserJPARepository userJPARepository;

    public void 쿠폰생성(CouponSaveDTO couponSaveDTO) {

        UUID uuid = UUID.randomUUID();
        String couponNumber = uuid.toString().substring(0, 18);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate couponCreatedAt = LocalDate.parse(couponSaveDTO.getCouponCreatedAt(), formatter);
        LocalDate couponExpiredAt = LocalDate.parse(couponSaveDTO.getCouponExpiredAt(), formatter);

        Coupon coupon = Coupon.builder()
                .couponName(couponSaveDTO.getCouponName())
                .couponNumber(couponNumber)
                .couponContent(couponSaveDTO.getCouponContent())
                .isExpired(couponSaveDTO.getIsExpired())
                .reduceAmount(couponSaveDTO.getReduceAmount())
                .couponCreatedAt(couponCreatedAt)
                .couponExpiredAt(couponExpiredAt).build();

        couponJPARepository.save(coupon);
    }

    public CouponListDTO 쿠폰목록() {
        List<Coupon> coupons = couponJPARepository.findAll();
        return new CouponListDTO(coupons);
    }

    @Transactional
    public UserCoupon 쿠폰등록(CouponRegisterDTO couponRegisterDTO, Integer sessionUserId) {

        Optional<Coupon> couponOP = couponJPARepository.findByCouponNumber(couponRegisterDTO.getCouponNumber());
        if (couponOP.isEmpty()) {
            throw new Exception404("쿠폰을 찾을수 없습니다.");
        }

        Coupon coupon = couponOP.get();
        User user = userJPARepository.findById(sessionUserId).get();
        UserCoupon userCoupon = UserCoupon.builder().coupon(coupon).user(user).build();
        UserCoupon savedUserCoupon = userCouponJPARepository.save(userCoupon);
        return savedUserCoupon;
    }

    public Coupon 쿠폰상세(Integer couponId) {
        Optional<Coupon> couponOP = couponJPARepository.findById(couponId);
        if (couponOP.isEmpty()) {
            throw new Exception404("존재하지 않는 쿠폰입니다.");
        }
        Coupon coupon = couponOP.get();
        return coupon;
    }

    @Transactional
    public void 쿠폰삭제(Integer couponId) {
        System.out.println("쿠폰 삭제 서비스");
        couponJPARepository.deleteById(couponId);
    }
}
