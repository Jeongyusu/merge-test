package shop.mtcoding.marketkurly.orderedoption;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.option.Option;
import shop.mtcoding.marketkurly.order.Order;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ordered_option_tb")
public class OrderedOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderedProductName;
    private Integer orderedProductSellerId;
    private String orderedProductSellerName;
    private String orderedProductThumbnail;
    private String orderedOptionName;
    private Integer orderedOptionQuantity;
    private Integer orderedOptionPrice;
    private Integer orderedDiscountRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderedOption(Integer id, Integer orderedOptionQuantity, Integer orderedOptionPrice,
            String orderedProductName, String orderedProductThumbnail, String orderedOptionName,
            String orderedProductSellerName, Integer orderedProductSellerId,
            Integer orderedDiscountRate, Option option, Order order) {
        this.id = id;
        this.orderedOptionQuantity = orderedOptionQuantity;
        this.orderedProductName = orderedProductName;
        this.orderedProductThumbnail = orderedProductThumbnail;
        this.orderedOptionName = orderedOptionName;
        this.orderedOptionPrice = orderedOptionPrice;
        this.orderedDiscountRate = orderedDiscountRate;
        this.orderedProductSellerName = orderedProductSellerName;
        this.orderedProductSellerId = orderedProductSellerId;
        this.option = option;
        this.order = order;
    }

}
