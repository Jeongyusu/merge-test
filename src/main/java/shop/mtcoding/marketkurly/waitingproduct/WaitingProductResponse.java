package shop.mtcoding.marketkurly.waitingproduct;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

public class WaitingProductResponse {

    @ToString
    @Getter
    public static class WaitingProductListDTO {

        private List<WaitingProductDTO> waitingProductDTOs;

        public WaitingProductListDTO(List<WaitingProduct> waitingProducts) {
            this.waitingProductDTOs = waitingProducts.stream().map(t -> new WaitingProductDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        public static class WaitingProductDTO {
            private int productId;
            private String productName;
            private String categoryType;
            private Integer discountRate;
            private LocalDate discountExpiredAt;
            private LocalDate productUploadedAt;
            private String state;

            public WaitingProductDTO(WaitingProduct waitingProduct) {
                this.productId = waitingProduct.getId();
                this.productName = waitingProduct.getWProductName();
                this.categoryType = waitingProduct.getCategory().getCategoryType();
                this.discountExpiredAt = waitingProduct.getWDiscountExpiredAt();
                this.productUploadedAt = waitingProduct.getWProductUploadedAt().toLocalDateTime().toLocalDate();
                this.discountRate = waitingProduct.getWDiscountRate();
                this.state = waitingProduct.getState();
            }
        }
    }

}
