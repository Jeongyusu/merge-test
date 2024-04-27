package shop.mtcoding.marketkurly.waitingoption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProduct;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "waiting_option_tb")
public class WaitingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Integer id;
    private String wOptionName;
    private Integer wOptionPrice;
    private Integer wOptionStack;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private WaitingProduct waitingProduct;

    @Builder
    public WaitingOption(Integer id, String wOptionName, Integer wOptionPrice, Integer wOptionStack,
            WaitingProduct waitingProduct) {
        this.id = id;
        this.wOptionName = wOptionName;
        this.wOptionPrice = wOptionPrice;
        this.wOptionStack = wOptionStack;
        this.waitingProduct = waitingProduct;
    }

}
