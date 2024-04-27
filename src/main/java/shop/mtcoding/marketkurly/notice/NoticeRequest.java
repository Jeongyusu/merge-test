package shop.mtcoding.marketkurly.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class NoticeRequest {

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class NoticeSaveDTO {

        private String noticeTitle;
        private String noticeType;
        private String noticeContent;

        public NoticeSaveDTO(String noticeTitle, String noticeType, String noticeContent) {
            this.noticeTitle = noticeTitle;
            this.noticeType = noticeType;
            this.noticeContent = noticeContent;
        }
    }

}
