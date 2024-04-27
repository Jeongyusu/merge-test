package shop.mtcoding.marketkurly.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryType;
    private String categoryPic;

    @Builder
    public Category(Integer id, String categoryType, String categoryPic) {
        this.id = id;
        this.categoryType = categoryType;
        this.categoryPic = categoryPic;
    }

}
