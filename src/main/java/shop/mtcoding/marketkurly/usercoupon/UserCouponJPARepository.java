package shop.mtcoding.marketkurly.usercoupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponJPARepository extends JpaRepository<UserCoupon, Integer> {

    List<UserCoupon> findByUserId(Integer sessionUserId);

}
