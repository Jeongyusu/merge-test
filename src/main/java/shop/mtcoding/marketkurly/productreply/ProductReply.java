package shop.mtcoding.marketkurly.productreply;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.productquestion.ProductQuestion;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product_reply_tb")
public class ProductReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pReplyContent;

    @CreationTimestamp
    private Timestamp pReplyCreatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductQuestion ProductQuestion;

    @Builder
    public ProductReply(Integer id, String pReplyContent, Timestamp pReplyCreatedAt,
            shop.mtcoding.marketkurly.productquestion.ProductQuestion productQuestion) {
        this.id = id;
        this.pReplyContent = pReplyContent;
        this.pReplyCreatedAt = pReplyCreatedAt;
        ProductQuestion = productQuestion;
    }

}
