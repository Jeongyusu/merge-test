package shop.mtcoding.marketkurly.order;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class OrderResponse {

    // 나중에 작성용 복붙
    @ToString
    @Getter
    @NoArgsConstructor
    public static class OptionSelectMainDTO {

        private Integer productId;
        private String productSellerName;
        private Integer productSellerId;
        private String productName;
        private List<OptionMainDTO> optionMainDTOs;

        @ToString
        @Getter
        @NoArgsConstructor
        public class OptionMainDTO {

            private Integer optionId;
            private String productSellerName;
            private String optionName;
            private Integer optionPrice;

        }
    }

}
