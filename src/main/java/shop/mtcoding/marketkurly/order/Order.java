package shop.mtcoding.marketkurly.order;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.address.Address;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderNumber;
    private String state;
    private Integer deliveryFee;

    @CreationTimestamp
    private Timestamp orderedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Builder
    public Order(Integer id, String orderNumber, Integer deliveryFee, String state, Timestamp orderedAt, User user,
            Address address) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.deliveryFee = deliveryFee;
        this.state = state;
        this.orderedAt = orderedAt;
        this.user = user;
        this.address = address;
    }

}
