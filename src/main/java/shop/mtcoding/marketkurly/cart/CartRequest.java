package shop.mtcoding.marketkurly.cart;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CartRequest {

    @ToString
    @Setter
    @Getter
    public static class SelectedCartDTO {

        private Integer selectedAddressId;
        private List<SelectedCartListDTO> selectedCartDTOs;

        public SelectedCartDTO(Integer selectedAddressId, List<SelectedCartListDTO> selectedCartListDTOs) {
            this.selectedAddressId = selectedAddressId;
            this.selectedCartDTOs = selectedCartListDTOs;
        }

    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SelectedCartListDTO {

        private List<SelectedCartProductDTO> selectedCartProducts;
        private Integer totalBeforePrice;
        private Integer totalDiscountPrice;
        private Integer deliveryFee;
        private Integer totalPrice;
        private Integer finalPrice;
        private Integer userCouponId;
        private Integer addressId;

        @Builder
        public SelectedCartListDTO(List<SelectedCartProductDTO> selectedCartProducts, Integer totalBeforePrice,
                Integer totalDiscountPrice,
                Integer deliveryFee, Integer totalPrice, Integer finalPrice, Integer userCouponId, Integer addressId) {
            this.selectedCartProducts = selectedCartProducts;
            this.totalBeforePrice = totalBeforePrice;
            this.totalDiscountPrice = totalDiscountPrice;
            this.deliveryFee = deliveryFee;
            this.totalPrice = totalPrice;
            this.finalPrice = finalPrice;
            this.userCouponId = userCouponId;
            this.addressId = addressId;
        }

    }

    @ToString
    @Setter
    @Getter
    @NoArgsConstructor
    public static class SelectedCartProductDTO {
        private Integer cartId;
        private Integer productId;
        private String productPic;
        private String productName;
        private Integer optionId;
        private String optionName;
        private String sellerName;
        private Integer totalOptionOriginPrice;
        private Integer totalOptionDiscountedPrice;
        private Integer optionQuantity;

        public SelectedCartProductDTO(Integer cartId, Integer productId, String productPic, String productName,
                Integer optionId, String optionName, String sellerName, Integer totalOptionOriginPrice,
                Integer totalOptionDiscountedPrice, Integer optionQuantity) {
            this.cartId = cartId;
            this.productId = productId;
            this.productPic = productPic;
            this.productName = productName;
            this.optionId = optionId;
            this.optionName = optionName;
            this.sellerName = sellerName;
            this.totalOptionOriginPrice = totalOptionOriginPrice;
            this.totalOptionDiscountedPrice = totalOptionDiscountedPrice;
            this.optionQuantity = optionQuantity;
        }
    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartSaveDTO {

        List<SelectedOptionDTO> selectedOptionDTOs;

        public CartSaveDTO(List<SelectedOptionDTO> selectedOptionDTOs) {
            this.selectedOptionDTOs = selectedOptionDTOs;
        }

    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SelectedOptionDTO {

        Integer optionId;
        Integer optionQuantity;

        public SelectedOptionDTO(Integer optionId, Integer optionQuantity) {
            this.optionId = optionId;
            this.optionQuantity = optionQuantity;
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CartDeleteListDTO {

        private List<Integer> cartDeleteList;
    }
}
