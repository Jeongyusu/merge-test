package shop.mtcoding.marketkurly.waitingoption;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingOptionJPARepository extends JpaRepository<WaitingOption, Integer> {

    List<WaitingOption> findByWaitingProductId(Integer wProductId);

}
