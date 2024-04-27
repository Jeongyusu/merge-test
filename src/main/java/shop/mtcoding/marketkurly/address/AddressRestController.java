package shop.mtcoding.marketkurly.address;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.address.AddressRequest.AddressSaveReqDTO;
import shop.mtcoding.marketkurly.user.User;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;
    private final HttpSession session;

    @GetMapping("/api/users/addresses")
    public ResponseEntity<?> 모든주소찾기() {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        List<Address> addresses = addressService.모든주소찾기(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(addresses));
    }

    @PostMapping("/api/users/addresses/save")
    public ResponseEntity<?> 배송지저장(@RequestBody AddressSaveReqDTO addressSaveReqDTO) {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        Address result = addressService.배송지저장(addressSaveReqDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(result));
    }
}
