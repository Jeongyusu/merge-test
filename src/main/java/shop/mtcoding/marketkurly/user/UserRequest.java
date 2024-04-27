package shop.mtcoding.marketkurly.user;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequest {
    @Getter
    @NoArgsConstructor
    @Setter
    public static class UserFindUsernameDTO {
        private String username;
        private String userEmail;

    }

    @Getter
    @NoArgsConstructor
    public static class LoginDTO {
        private String userId;
        private String userPassword;

        public LoginDTO(String userId, String userPassword) {
            this.userId = userId;
            this.userPassword = userPassword;
        }

    }

    @Getter
    @Setter
    public static class UserJoinDTO {
        private String userId;
        private String userPassword;
        private String username;
        private String userEmail;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate userBirth;
        private String userGender;
        private String role;

        public UserJoinDTO(String userId, String userPassword, String username, String userEmail, LocalDate userBirth,
                String userGender, String role) {
            this.userId = userId;
            this.userPassword = userPassword;
            this.username = username;
            this.userEmail = userEmail;
            this.userBirth = userBirth;
            this.userGender = userGender;
            this.role = role;
        }

        // enum.valueOf (String)이 String 변수를 읽어서 enum 타입으로 바꿔준다.
        // toUpperCase는 전부 대문자로 바꿔준다
        public Gender getUserGender() {
            return Gender.valueOf(userGender.toUpperCase());
        }

        public Role getRole() {
            return Role.valueOf(role.toUpperCase());
        }

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .userPassword(userPassword)
                    .username(username)
                    .userEmail(userEmail)
                    .userBirth(userBirth)
                    .userGender(Gender.valueOf(userGender))
                    .role(Role.valueOf(role))
                    .build();
        }

    }

    @NoArgsConstructor
    @Getter
    public static class UserIdDuplicatedDTO {
        private String userId;
    }

    @Getter
    @Setter
    public static class SellerJoinDTO {
        private String userId;
        private String userPassword;
        private String username;
        private String userEmail;

        public SellerJoinDTO(String userId, String userPassword, String username, String userEmail) {
            this.userId = userId;
            this.userPassword = userPassword;
            this.username = username;
            this.userEmail = userEmail;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserUpdateDTO {
        private Integer id;
        private String userId;
        private String userPassword;
        private String username;
        private String userEmail;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate userBirth;
        private String userGender;
        private String role;

        public UserUpdateDTO(Integer id, String userId, String userPassword, String username, String userEmail,
                LocalDate userBirth, String userGender, String role) {
            this.id = id;
            this.userId = userId;
            this.userPassword = userPassword;
            this.username = username;
            this.userEmail = userEmail;
            this.userBirth = userBirth;
            this.userGender = userGender;
            this.role = role;
        }

        // enum.valueOf (String)이 String 변수를 읽어서 enum 타입으로 바꿔준다.
        // toUpperCase는 전부 대문자로 바꿔준다
        public Gender getUserGender() {
            return Gender.valueOf(userGender.toUpperCase());
        }

        public Role getRole() {
            return Role.valueOf(role.toUpperCase());
        }

        public User toIdEntity() {
            return User.builder()
                    .id(id)
                    .userId(userId)
                    .userPassword(userPassword)
                    .username(username)
                    .userEmail(userEmail)
                    .userBirth(userBirth)
                    .userGender(Gender.valueOf(userGender))
                    .role(Role.valueOf(role))
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor
    public static class UpdateCheckDTO {
        private String userId;
        private String userPassword;

        public UpdateCheckDTO(String userId, String userPassword) {
            this.userId = userId;
            this.userPassword = userPassword;
        }

    }
}