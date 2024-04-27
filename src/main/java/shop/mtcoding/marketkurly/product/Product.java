package shop.mtcoding.marketkurly.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.category.Category;
import shop.mtcoding.marketkurly.option.Option;
import shop.mtcoding.marketkurly.review.Review;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "product_tb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productThumbnail;

    @Nullable
    private String productDetailPic; // 상품정보

    private String productName;

    @Nullable
    private String productContent; // 상품설명

    // 할인율
    private Integer discountRate;

    @Nullable
    private LocalDate discountExpiredAt; // 할인만료기간

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<Review>();

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User seller; // 판매자

    private LocalDate productUploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String productOrigin;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Option> options = new ArrayList<Option>();

    @Builder
    public Product(Integer id, String productThumbnail, String productDetailPic, String productName,
            String productContent, Integer discountRate, LocalDate discountExpiredAt, User seller,
            LocalDate productUploadedAt, Category category, String productOrigin, List<Review> reviews) {
        this.id = id;
        this.productThumbnail = productThumbnail;
        this.productDetailPic = productDetailPic;
        this.productName = productName;
        this.productContent = productContent;
        this.discountRate = discountRate;
        this.discountExpiredAt = discountExpiredAt;
        this.seller = seller;
        this.productUploadedAt = productUploadedAt;
        this.category = category;
        this.productOrigin = productOrigin;
        this.reviews = reviews;
    }

}
