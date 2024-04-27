package shop.mtcoding.marketkurly.adminreply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AdminReplyRequest {

    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AReplySaveDTO {

        private Integer adminQuestionId;
        private String replyContent;

        public AReplySaveDTO(Integer adminQuestionId, String replyContent) {
            this.adminQuestionId = adminQuestionId;
            this.replyContent = replyContent;
        }

    }
}
