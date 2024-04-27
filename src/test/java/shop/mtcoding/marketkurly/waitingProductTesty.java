package shop.mtcoding.marketkurly;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import shop.mtcoding.marketkurly.waitingoption.WaitingOption;
import shop.mtcoding.marketkurly.waitingoption.WaitingOptionJPARepository;

// test환경에서는 Autowired해주지 않으면 매개변수 인식을 못함
// @RequiredArgsConstructor
@SpringBootTest
public class waitingProductTesty {

    @Autowired
    private WaitingOptionJPARepository waitingOptionJPARepository;

    private Integer userid = 1;

    @Test
    public void 대기상품디테일() {
        List<WaitingOption> waitingOptions = waitingOptionJPARepository.findByWaitingProductId(userid);
    }

}
