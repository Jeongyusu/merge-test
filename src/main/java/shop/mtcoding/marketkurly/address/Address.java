package shop.mtcoding.marketkurly.address;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "address_tb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String destination;
    private String destinationDetail;
    private String receiverName;
    private String receiverTel;
    private Boolean isDefaultAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Address(Integer id, String destination, String destinationDetail, String receiverName, String receiverTel,
            Boolean isDefaultAddress,
            User user) {
        this.id = id;
        this.destination = destination;
        this.destinationDetail = destinationDetail;
        this.receiverName = receiverName;
        this.receiverTel = receiverTel;
        this.isDefaultAddress = isDefaultAddress;
        this.user = user;
    }

}
