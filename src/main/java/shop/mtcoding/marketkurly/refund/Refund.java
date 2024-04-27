package shop.mtcoding.marketkurly.refund;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.payment.Payment;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "refund_tb")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer refundPayment;

    @CreationTimestamp
    private Timestamp refundedAt;

    @OneToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @Builder
    public Refund(Integer id, Integer refundPayment, Timestamp refundedAt, Payment payment) {
        this.id = id;
        this.refundPayment = refundPayment;
        this.refundedAt = refundedAt;
        this.payment = payment;
    }

}
