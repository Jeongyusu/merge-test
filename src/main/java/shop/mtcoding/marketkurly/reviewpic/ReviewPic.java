package shop.mtcoding.marketkurly.reviewpic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.review.Review;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review_pic_tb")
public class ReviewPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reviewPicUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @Builder
    public ReviewPic(Integer id, String reviewPicUrl, Review review) {
        this.id = id;
        this.reviewPicUrl = reviewPicUrl;
        this.review = review;
    }

}
