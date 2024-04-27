package shop.mtcoding.marketkurly.waitingoption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class WaitingOptionRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class WOptionSaveDTO {

        private String optionName;
        private Integer optionPrice;
        private Integer optionStack;

    }

}
