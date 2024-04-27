package shop.mtcoding.marketkurly.user;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserResponse {

    @Getter
    @AllArgsConstructor
    public static class UserFindUsernameDTO {
        private String username;

        public UserFindUsernameDTO(User user) {
            this.username = user.getUsername();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class LoginDTO {
        private Integer id;
        private String userId;
        private String username;
        private String userEmail;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate userBirth;
        private String userGender;
        private String role;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Timestamp userCreatedAt;

        public LoginDTO(User user) {
            this.id = user.getId();
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.userEmail = user.getUserEmail();
            this.userBirth = user.getUserBirth();
            this.userGender = user.getUserGender().name();
            this.role = user.getRole().name();
            this.userCreatedAt = user.getUserCreatedAt();
        }

    }

    @Getter
    @AllArgsConstructor
    public static class JoinDTO {
        private Integer id;
        private String userId;
        private String userPassword;
        private String username;
        private String userEmail;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate userBirth;
        private String userGender;
        private String role;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.userId = user.getUserId();
            this.userPassword = null;
            this.username = user.getUsername();
            this.userEmail = user.getUserEmail();
            this.userBirth = user.getUserBirth();
            this.userGender = user.getUserGender().name();
            this.role = user.getRole().name();
        }

    }

    @Getter
    public static class TokenDTO {
        private String jwt;
        private User user;

        public TokenDTO(String jwt, User user) {
            this.jwt = jwt;
            this.user = UserPSDTO.hideUserPS(user);
        }

    }

    @Getter
    @AllArgsConstructor
    public static class UserPSDTO {

        public static User hideUserPS(User user) {
            User userPS = User.builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .userPassword(null)
                    .username(user.getUsername())
                    .userEmail(user.getUserEmail())
                    .userGender(user.getUserGender())
                    .userBirth(user.getUserBirth())
                    .role(user.getRole()).build();
            return userPS;
        }

    }

}
