package shop.mtcoding.marketkurly.waitingproduct;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.marketkurly.category.Category;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "waiting_product_tb")
public class WaitingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nullable
    private Integer productId;

    private String wProductThumbnail;

    private String wProductDetailPic; // 상품정보

    private String wProductName;

    private String wProductContent; // 상품설명

    // 할인율
    private Integer wDiscountRate;

    @Nullable
    private LocalDate wDiscountExpiredAt; // 할인만료기간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User seller; // 판매자

    @CreationTimestamp
    private Timestamp wProductUploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Nullable
    private String state = "대기중";

    @Builder
    public WaitingProduct(Integer id, Integer productId, String wProductThumbnail, String wProductDetailPic,
                          String wProductName, String wProductContent, Integer wDiscountRate, LocalDate wDiscountExpiredAt,
                          User seller, Timestamp wProductUploadedAt, Category category, String state) {
        this.id = id;
        this.productId = productId;
        this.wProductThumbnail = wProductThumbnail;
        this.wProductDetailPic = wProductDetailPic;
        this.wProductName = wProductName;
        this.wProductContent = wProductContent;
        this.wDiscountRate = wDiscountRate;
        this.wDiscountExpiredAt = wDiscountExpiredAt;
        this.seller = seller;
        this.wProductUploadedAt = wProductUploadedAt;
        this.category = category;
        this.state = state;
    }

}
