package shop.mtcoding.marketkurly.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.cart.CartRequest.CartDeleteListDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.CartSaveDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.SelectedCartListDTO;
import shop.mtcoding.marketkurly.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CartRestController {

    private final CartService cartService;
    private final HttpSession session;

    @GetMapping("/api/carts")
    public ResponseEntity<?> 장바구니목록조회(HttpServletRequest request) {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        CartResponse.FindAllDTO findAllDTO = cartService.장바구니목록조회(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(findAllDTO));
    }

    @PostMapping("/api/carts/order")
    public ResponseEntity<?> 장바구니주문검증(@RequestBody SelectedCartListDTO selectedCartListDTO) {

        System.out.println("SelectedCartProductDTO 호출됨 : " + selectedCartListDTO);

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        cartService.장바구니주문검증(selectedCartListDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(selectedCartListDTO));
    }

    @PostMapping("/api/carts/insert")
    public ResponseEntity<?> 장바구니담기(@RequestBody CartSaveDTO cartSaveDTO) {

        User user = (User) session.getAttribute("sessionUser");

        cartService.장바구니담기(cartSaveDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(cartSaveDTO));
    }

    @PostMapping("/api/carts/delete")
    public ResponseEntity<?> 장바구니선택삭제(@RequestBody CartDeleteListDTO cartDeleteListDTO) {

        User user = (User) session.getAttribute("sessionUser");
        cartService.장바구니선택삭제(cartDeleteListDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success("삭제 완료"));
    }
}
