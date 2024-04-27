package shop.mtcoding.marketkurly.coupon;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJPARepository extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCouponNumber(String couponNumber);

}
