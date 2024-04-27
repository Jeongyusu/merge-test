package shop.mtcoding.marketkurly.waitingproduct;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProductRequest.WProductDTO;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WaitingProductRestController {

    private final WaitingProductService waitingProductService;
    private final HttpSession session;

    @PostMapping("/seller/product/submit/save")
    public ResponseEntity<?> 상품승인요청(@ModelAttribute WProductDTO wProductDTO) {
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        waitingProductService.상품승인요청(wProductDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success("통신 성공"));
    }

    @PostMapping("/admin/product/waiting/accept/{wProductId}")
    public ResponseEntity<?> 상품승인(@PathVariable Integer wProductId) {

        waitingProductService.상품승인(wProductId);
        return ResponseEntity.ok().body(ApiUtils.success(true));
    }

    @PostMapping("/admin/product/waiting/reject/{wProductId}")
    public ResponseEntity<?> 상품거절(@PathVariable Integer wProductId) {

        waitingProductService.상품거절(wProductId);
        return ResponseEntity.ok().body(ApiUtils.success(true));
    }

}
