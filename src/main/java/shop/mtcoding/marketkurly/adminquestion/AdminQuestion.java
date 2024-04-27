package shop.mtcoding.marketkurly.adminquestion;

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
import shop.mtcoding.marketkurly.adminreply.AdminReply;
import shop.mtcoding.marketkurly.user.User;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin_question_tb")
public class AdminQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String adminQuestionType;
    private String adminQuestionTitle;
    private String adminQuestionContent;
    private Boolean isAnswered;
    private Boolean isEmailAccepted;

    @CreationTimestamp
    private Timestamp adminQuestionCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Nullable
    @OneToOne(fetch = FetchType.LAZY)
    private AdminReply AdminReply;

    @Builder
    public AdminQuestion(Integer id, String adminQuestionType, String adminQuestionTitle, String adminQuestionContent,
            Boolean isAnswered, Boolean isEmailAccepted, Timestamp adminQuestionCreatedAt, User user) {
        this.id = id;
        this.adminQuestionType = adminQuestionType;
        this.adminQuestionTitle = adminQuestionTitle;
        this.adminQuestionContent = adminQuestionContent;
        this.isAnswered = isAnswered;
        this.isEmailAccepted = isEmailAccepted;
        this.adminQuestionCreatedAt = adminQuestionCreatedAt;
        this.user = user;
    }

}
