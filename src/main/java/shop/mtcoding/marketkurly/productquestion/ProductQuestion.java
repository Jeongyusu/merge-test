package shop.mtcoding.marketkurly.productquestion;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.product.Product;
import shop.mtcoding.marketkurly.productreply.ProductReply;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product_question_tb")
public class ProductQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productQuestionTitle;
    private String productQuestionContent;

    private Boolean isAnswered = false;
    private Boolean isSecreted = false;

    @CreationTimestamp
    private Timestamp productQuestionCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Nullable
    @OneToOne(fetch = FetchType.LAZY)
    private ProductReply productReply;

    @Builder
    public ProductQuestion(Integer id, String productQuestionTitle, String productQuestionContent, Boolean isAnswered,
            Boolean isSecreted, Timestamp productQuestionCreatedAt, User user, Product product,
            ProductReply productReply) {
        this.id = id;
        this.productQuestionTitle = productQuestionTitle;
        this.productQuestionContent = productQuestionContent;
        this.isAnswered = isAnswered;
        this.isSecreted = isSecreted;
        this.productQuestionCreatedAt = productQuestionCreatedAt;
        this.user = user;
        this.product = product;
        this.productReply = productReply;
    }

}
