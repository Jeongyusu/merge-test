package shop.mtcoding.marketkurly.orderedoption;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.orderedoption.OrderedOptionResponse.OrderedOptionListDTO;
import shop.mtcoding.marketkurly.orderedoption.OrderedOptionResponse.OrderedOptionListMainDTO;

@Service
@RequiredArgsConstructor
public class OrderedOptionService {

    private final OrderOptionJAPRepository orderOptionJAPRepository;

    public OrderedOptionListMainDTO 주문상세보기(Integer orderId) {
        List<OrderedOption> orderdOptions = orderOptionJAPRepository.findByOrderId(orderId);
        return new OrderedOptionListMainDTO(orderdOptions);
    }

    public OrderedOptionListDTO 판매된상품찾기(Integer sellerId) {

        List<OrderedOption> orderedOptions = orderOptionJAPRepository.findByOrderedProductSellerId(sellerId);
        System.out.println("orderOPtion List : " + orderedOptions);
        return new OrderedOptionListDTO(orderedOptions);
    }

}
