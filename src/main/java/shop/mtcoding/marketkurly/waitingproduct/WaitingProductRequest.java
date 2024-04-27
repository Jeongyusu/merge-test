package shop.mtcoding.marketkurly.waitingproduct;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class WaitingProductRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class WProductDTO {
        private String productName;
        private String productContent;
        private Integer discountRate;
        private String discountExpiredAt;
        private Integer categoryId;
        private MultipartFile productThumbnail;
        private MultipartFile productDetailPic;
        private String optionList;
    }

}
