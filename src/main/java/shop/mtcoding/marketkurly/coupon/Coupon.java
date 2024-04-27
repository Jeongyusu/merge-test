package shop.mtcoding.marketkurly.coupon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "coupon_tb")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String couponNumber;
    private String couponName;
    private String couponContent;
    private Integer reduceAmount; // 할인금액
    private Boolean isExpired; // 쿠폰 만료
    // 쿠폰 생성일
    private LocalDate couponCreatedAt;
    // 쿠폰 만료일
    private LocalDate couponExpiredAt;
    private Integer couponCount; // 쿠폰 사용횟수

    @Builder
    public Coupon(Integer id, String couponNumber, String couponName, String couponContent, Integer reduceAmount,
            Boolean isExpired, LocalDate couponCreatedAt, LocalDate couponExpiredAt, Integer couponCount) {
        this.id = id;
        this.couponNumber = couponNumber;
        this.couponName = couponName;
        this.couponContent = couponContent;
        this.reduceAmount = reduceAmount;
        this.isExpired = isExpired;
        this.couponCreatedAt = couponCreatedAt;
        this.couponExpiredAt = couponExpiredAt;
        this.couponCount = (couponCount != null) ? couponCount : 0;
    }

}
