package shop.mtcoding.marketkurly.orderedoption;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class OrderedOptionResponse {

    @ToString
    @Getter
    @NoArgsConstructor
    public static class OrderedOptionListMainDTO {

        private Integer orderId;
        private String orderNumber;
        private List<OrderedOptionMainDTO> orderedOptionMainDTOs;

        private Integer totalPrice;
        private Integer deliveryFee;
        private Integer finalPrice;

        public OrderedOptionListMainDTO(List<OrderedOption> orderedOptions) {
            this.orderId = orderedOptions.get(0).getOrder().getId();
            this.orderNumber = orderedOptions.get(0).getOrder().getOrderNumber();

            this.orderedOptionMainDTOs = orderedOptions.stream()
                    .map(t -> new OrderedOptionMainDTO(t.getOrder().getState(), t))
                    .collect(Collectors.toList());
            this.totalPrice = orderedOptions.stream()
                    .mapToInt(t -> t.getOrderedOptionPrice() * t.getOrderedOptionQuantity()).sum();
            this.deliveryFee = orderedOptions.get(0).getOrder().getDeliveryFee();
            this.finalPrice = (orderedOptions.stream()
                    .mapToInt(t -> t.getOrderedOptionPrice()
                            * t.getOrderedOptionQuantity() * (100 - t.getOrderedDiscountRate()) / 100)
                    .sum()) - deliveryFee;
            ;
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class OrderedOptionMainDTO {
            private Integer orderedOptionID;
            private String orderedProductThumbnail;
            private String orderedProductSellerName;
            private String orderedOptionName;
            private Integer orderedOptionQuantity;
            private Integer orderedOptionPrice;
            private Integer discountedPrice;
            private String state;

            public OrderedOptionMainDTO(String state, OrderedOption orderedOption) {
                this.orderedOptionID = orderedOption.getId();
                this.orderedProductThumbnail = orderedOption.getOrderedProductThumbnail();
                this.orderedProductSellerName = orderedOption.getOrderedProductSellerName();
                this.orderedOptionName = orderedOption.getOrderedOptionName();
                this.orderedOptionQuantity = orderedOption.getOrderedOptionQuantity();
                this.orderedOptionPrice = orderedOption.getOrderedOptionPrice();
                this.discountedPrice = orderedOption
                        .getOrderedOptionPrice() * (100 - orderedOption.getOrderedDiscountRate()) / 100;
                this.state = state;
            }

        }
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class OrderedOptionListDTO {

        private List<OrderedOptionDTO> orderedOptionDTOs;

        public OrderedOptionListDTO(List<OrderedOption> orderedOptions) {
            this.orderedOptionDTOs = orderedOptions.stream().map(t -> new OrderedOptionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class OrderedOptionDTO {

            private Integer orderedOptionId;
            private Integer productId;
            private String productName;
            private Integer optionId;
            private String optionName;
            private Integer optionQuantity;
            private Integer optionPrice;
            private String orderNumber;
            private Integer userId;
            private String username;
            private String state;
            private LocalDate orderAt;

            public OrderedOptionDTO(OrderedOption t) {
                this.orderedOptionId = t.getId();
                this.productName = t.getOption().getProduct().getProductName();
                this.optionName = t.getOption().getOptionName();
                this.optionQuantity = t.getOrderedOptionQuantity();
                this.optionPrice = t.getOrderedOptionPrice();
                this.orderNumber = t.getOrder().getOrderNumber();
                this.username = t.getOrder().getUser().getUsername();
                this.userId = t.getOrder().getUser().getId();
                this.state = "배송완료";
                this.orderAt = t.getOrder().getOrderedAt().toLocalDateTime().toLocalDate();
            };

        }
    }
}