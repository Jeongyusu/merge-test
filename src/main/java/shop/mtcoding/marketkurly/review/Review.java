package shop.mtcoding.marketkurly.review;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.marketkurly.product.Product;
import shop.mtcoding.marketkurly.reviewpic.ReviewPic;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review_tb")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reviewTitle;
    private String reviewContent;
    private Integer starCount;

    @CreationTimestamp
    private Timestamp reviewCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReviewPic> reviewPics = new ArrayList<ReviewPic>();

    @Builder
    public Review(Integer id, String reviewTitle, String reviewContent, Integer starCount, Timestamp reviewCreatedAt,
            User user, Product product) {
        this.id = id;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.starCount = starCount;
        this.reviewCreatedAt = reviewCreatedAt;
        this.user = user;
        this.product = product;
    }

}