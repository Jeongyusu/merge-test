package shop.mtcoding.marketkurly.waitingoption;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.waitingoption.WaitingOptionResponse.WOptionListDTO;

@Service
@RequiredArgsConstructor
public class WaitingOptionService {

    private final WaitingOptionJPARepository waitingOptionJPARepository;

    public WOptionListDTO 대기상품디테일(Integer wProductId) {
        List<WaitingOption> waitingOptions = waitingOptionJPARepository.findByWaitingProductId(wProductId);
        return new WOptionListDTO(waitingOptions);
    }

}
