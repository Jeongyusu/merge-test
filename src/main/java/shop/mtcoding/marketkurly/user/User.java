package shop.mtcoding.marketkurly.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId; // 인증시 필요한 필드
    private String username;
    private String userPassword;
    private String userEmail;
    private String userPic;
    @Nullable
    private LocalDate userBirth;
    @Enumerated(EnumType.STRING)
    private Gender userGender;
    @Nullable
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Timestamp userCreatedAt;

    @Builder
    public User(Integer id, String userId, String username, String userPassword, String userEmail, String userPic,
            LocalDate userBirth, Gender userGender, Role role, Timestamp userCreatedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPic = userPic;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.role = role;
        this.userCreatedAt = userCreatedAt;
    }

}
