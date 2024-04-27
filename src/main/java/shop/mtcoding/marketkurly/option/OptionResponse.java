package shop.mtcoding.marketkurly.option;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class OptionResponse {

    @ToString
    @Getter
    @NoArgsConstructor
    public static class OptionSelectMainDTO {

        private Integer productId;
        private String productSellerName;
        private Integer productSellerId;
        private String productName;
        private List<OptionMainDTO> optionMainDTOs;

        public OptionSelectMainDTO(List<Option> options) {
            this.productId = options.get(0).getProduct().getId();
            this.productSellerId = options.get(0).getProduct().getSeller().getId();
            this.productSellerName = options.get(0).getProduct().getSeller().getUsername();
            this.optionMainDTOs = options.stream()
                    .map(option -> new OptionMainDTO(productSellerName, option))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class OptionMainDTO {

            private Integer optionId;
            private String productSellerName;
            private String optionName;
            private Integer optionPrice;

            public OptionMainDTO(String productSellerName, Option option) {
                this.optionId = option.getId();
                this.productSellerName = productSellerName;
                this.optionName = option.getOptionName();
                this.optionPrice = option.getOptionPrice();
            }
        }
    }
}