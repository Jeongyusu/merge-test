package shop.mtcoding.marketkurly.option;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.option.OptionResponse.OptionSelectMainDTO;

@RestController
@RequiredArgsConstructor
public class OptionRestController {

    private final OptionService optionService;

    @GetMapping("/api/product/options/{productId}")
    public ResponseEntity<?> 상품옵션목록(@PathVariable Integer productId) {

        OptionSelectMainDTO dto = optionService.상품옵션목록(productId);
        return ResponseEntity.ok().body(ApiUtils.success(dto));
    }
}
