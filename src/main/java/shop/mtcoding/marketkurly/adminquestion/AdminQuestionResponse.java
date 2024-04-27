package shop.mtcoding.marketkurly.adminquestion;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.adminreply.AdminReply;

public class AdminQuestionResponse {

    @ToString
    @Getter
    @Setter
    public static class AdminQuestionListDTO {

        List<AdminQuestionDTO> adminQuestionAnsweredDTOs;
        List<AdminQuestionDTO> adminQuestionWaitingDTOs;

        public AdminQuestionListDTO(List<AdminQuestion> adminQuestions) {
            this.adminQuestionAnsweredDTOs = adminQuestions.stream()
                    .filter(adminQuestion -> Boolean.TRUE.equals(adminQuestion.getIsAnswered()))
                    .map(t -> new AdminQuestionDTO(t))
                    .collect(Collectors.toList());

            this.adminQuestionWaitingDTOs = adminQuestions.stream()
                    .filter(adminQuestion -> Boolean.FALSE.equals(adminQuestion.getIsAnswered()))
                    .map(t -> new AdminQuestionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Getter
        @Setter
        public class AdminQuestionDTO {

            private Integer id;
            private Integer userId;
            private String adminQuestionType;
            private String username;
            private String adminQuestionTitle;
            private Boolean isAnswered;
            private Boolean isEmailAccepted;
            private LocalDate adminQuestionCreatedAt;

            public AdminQuestionDTO(AdminQuestion t) {
                this.id = t.getId();
                this.userId = t.getUser().getId();
                this.adminQuestionType = t.getAdminQuestionType();
                this.username = t.getUser().getUsername();
                this.adminQuestionTitle = t.getAdminQuestionTitle();
                this.isAnswered = t.getIsAnswered();
                this.isEmailAccepted = t.getIsEmailAccepted();
                this.adminQuestionCreatedAt = t.getAdminQuestionCreatedAt().toLocalDateTime().toLocalDate();
            }

        }
    }

    @ToString
    @Getter
    @Setter
    public static class AdminQuestionDetailDTO {

        AdminQuestionDTO adminQuestionDTO;
        AdminReplyDTO adminReplyDTO;

        public AdminQuestionDetailDTO(AdminQuestion adminQuestion) {
            this.adminQuestionDTO = new AdminQuestionDTO(adminQuestion);
            this.adminReplyDTO = new AdminReplyDTO(adminQuestion.getAdminReply());
        }

        @ToString
        @Getter
        @Setter
        public class AdminQuestionDTO {

            private Integer id;
            private String adminQuestionType;
            private String username;
            private String adminQuestionTitle;
            private String adminQuestionContent;
            private Boolean isAnswered;
            private Boolean isEmailAccepted;
            private LocalDate adminQuestionCreatedAt;

            public AdminQuestionDTO(AdminQuestion t) {
                this.id = t.getId();
                this.adminQuestionType = t.getAdminQuestionType();
                this.username = t.getUser().getUsername();
                this.adminQuestionTitle = t.getAdminQuestionTitle();
                this.adminQuestionContent = t.getAdminQuestionContent();
                this.isAnswered = t.getIsAnswered();
                this.isEmailAccepted = t.getIsEmailAccepted();
                this.adminQuestionCreatedAt = t.getAdminQuestionCreatedAt().toLocalDateTime().toLocalDate();
            }

        }

        @ToString
        @Getter
        @Setter
        public class AdminReplyDTO {

            private Integer id = 0;
            private String aReplyContent = "댓글 없음";

            public AdminReplyDTO(AdminReply t) {
                if (t != null) {
                    this.id = t.getId();
                    this.aReplyContent = t.getAReplyContent();
                }
            }

        }
    }
}
