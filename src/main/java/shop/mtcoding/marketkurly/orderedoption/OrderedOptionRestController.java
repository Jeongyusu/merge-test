package shop.mtcoding.marketkurly.orderedoption;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.orderedoption.OrderedOptionResponse.OrderedOptionListMainDTO;
import shop.mtcoding.marketkurly.product.Product;

@RestController
@RequiredArgsConstructor
public class OrderedOptionRestController {

    private final OrderOptionJAPRepository orderOptionJAPRepository;

    private final OrderedOptionService orderedOptionService;

    @GetMapping("/api/carts/selectoo")
    public Page<Product> 주문옵션찾기() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Order.desc("fieldName")));

        Page<Product> products = orderOptionJAPRepository.findBestProducts(pageable);
        System.out.println(products);
        return products;
    }

    @GetMapping("/api/carts/order/detail")
    public ResponseEntity<?> 주문상세보기() {

        // TODO 나중에 orderId 받아서 넣기
        Integer orderId = 1;
        OrderedOptionListMainDTO dto = orderedOptionService.주문상세보기(orderId);
        return ResponseEntity.ok().body(ApiUtils.success(dto));
    }

}
