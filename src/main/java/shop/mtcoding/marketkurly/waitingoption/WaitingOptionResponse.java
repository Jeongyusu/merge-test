package shop.mtcoding.marketkurly.waitingoption;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProduct;

public class WaitingOptionResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class WOptionListDTO {

        private WaitingProduct waitingProduct;
        private List<WaitingOptionDTO> waitingOptionDTOs;

        public WOptionListDTO(List<WaitingOption> waitingOptions) {
            this.waitingProduct = waitingOptions.get(0).getWaitingProduct();
            this.waitingOptionDTOs = waitingOptions.stream().map(t -> new WaitingOptionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Setter
        @Getter
        public static class WaitingOptionDTO {
            private Integer waitingOptionId;
            private String wOptionName;
            private Integer wOptionPrice;
            private Integer wOptionStack;

            public WaitingOptionDTO(WaitingOption waitingOption) {
                this.waitingOptionId = waitingOption.getId();
                this.wOptionName = waitingOption.getWOptionName();
                this.wOptionPrice = waitingOption.getWOptionPrice();
                this.wOptionStack = waitingOption.getWOptionStack();
            }
        }
    }
}
