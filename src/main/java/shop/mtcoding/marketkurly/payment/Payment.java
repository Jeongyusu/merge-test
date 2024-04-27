package shop.mtcoding.marketkurly.payment;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.coupon.Coupon;
import shop.mtcoding.marketkurly.order.Order;
import shop.mtcoding.marketkurly.paymenttype.PaymentType;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payment_tb")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer paymentNumber;
    private Integer totalOriginPrice;
    private Integer deliveryFee;
    private Integer finalPrice;
    private Integer finalPayment;
    private String state;

    @CreationTimestamp
    private Timestamp orderedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;

    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @Builder
    public Payment(Integer id, Integer paymentNumber, Integer totalOriginPrice, Integer deliveryFee, Integer finalPrice,
            Integer finalPayment, String state, Timestamp orderedAt, Order order, PaymentType paymentType,
            Coupon coupon) {
        this.id = id;
        this.paymentNumber = paymentNumber;
        this.totalOriginPrice = totalOriginPrice;
        this.deliveryFee = deliveryFee;
        this.finalPrice = finalPrice;
        this.finalPayment = finalPayment;
        this.state = state;
        this.orderedAt = orderedAt;
        this.order = order;
        this.paymentType = paymentType;
        this.coupon = coupon;
    }

}
