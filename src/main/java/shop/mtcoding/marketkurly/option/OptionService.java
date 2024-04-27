package shop.mtcoding.marketkurly.option;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.option.OptionResponse.OptionSelectMainDTO;
import shop.mtcoding.marketkurly.product.ProductResponse.ProductDetailListDTO;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionJPARepository optionJPARepository;

    public OptionSelectMainDTO 상품옵션목록(Integer productId) {

        List<Option> options = optionJPARepository.findByProductId(productId);
        return new OptionSelectMainDTO(options);
    }

    public ProductDetailListDTO 대기상품디테일(Integer productId) {
        List<Option> options = optionJPARepository.findByProductId(productId);
        return new ProductDetailListDTO(options);
    }

}
