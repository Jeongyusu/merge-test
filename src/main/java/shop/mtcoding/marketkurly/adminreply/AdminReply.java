package shop.mtcoding.marketkurly.adminreply;

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
import shop.mtcoding.marketkurly.adminquestion.AdminQuestion;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin_reply_tb")
public class AdminReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String aReplyContent;

    @CreationTimestamp
    private Timestamp aReplyCreatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    private AdminQuestion adminQuestion;

    @Builder
    public AdminReply(Integer id, String aReplyContent, Timestamp aReplyCreatedAt, AdminQuestion adminQuestion) {
        this.id = id;
        this.aReplyContent = aReplyContent;
        this.aReplyCreatedAt = aReplyCreatedAt;
        this.adminQuestion = adminQuestion;
    }

}
