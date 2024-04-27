package shop.mtcoding.marketkurly.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AddressRequest {

    @Getter
    @NoArgsConstructor
    @Setter
    public static class AddressSaveReqDTO {
        private String destination;
        private String destinationDetail;
        private String receiverName;
        private String receiverTel;
        private Boolean isDefaultAddress;

    }

}