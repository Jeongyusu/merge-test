package shop.mtcoding.marketkurly.notice;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice_tb")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String noticeTitle;
    private String noticeType;
    private String noticeContent;

    @CreationTimestamp
    private Timestamp noticeCreatedAt;

    @Builder
    public Notice(Integer id, String noticeTitle, String noticeType, String noticeContent, Timestamp noticeCreatedAt) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeType = noticeType;
        this.noticeContent = noticeContent;
        this.noticeCreatedAt = noticeCreatedAt;
    }

}
