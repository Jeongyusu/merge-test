package shop.mtcoding.marketkurly.option;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.product.Product;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "option_tb")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String optionName;
    private Integer optionPrice;
    private Integer optionStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @Builder
    public Option(Integer id, String optionName, Integer optionPrice, Integer optionStack, Product product) {
        this.id = id;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.optionStack = optionStack;
        this.product = product;
    }

}
