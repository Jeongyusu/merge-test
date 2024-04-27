package shop.mtcoding.marketkurly.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NoticeResponse {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class NoticeMainDTO {

        private List<NoticeDTO> noticeDTOs;

        public NoticeMainDTO(List<Notice> notices) {
            this.noticeDTOs = notices.stream().map(t -> new NoticeDTO(t)).collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class NoticeDTO {
            private Integer noticeId;
            private String noticeTitle;
            private String noticeType;
            private String noticeWriter = "MarketKurly";
            private LocalDate noticeCreatedAt;

            public NoticeDTO(Notice notice) {
                this.noticeId = notice.getId();
                this.noticeTitle = notice.getNoticeTitle();
                this.noticeType = notice.getNoticeType();
                this.noticeCreatedAt = notice.getNoticeCreatedAt().toLocalDateTime().toLocalDate();
            }
        }
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class WebNoticeMainDTO {

        private List<WebNoticeDTO> webNoticeDTOs;

        public WebNoticeMainDTO(List<Notice> notices) {
            this.webNoticeDTOs = notices.stream().map(t -> new WebNoticeDTO(t)).collect(Collectors.toList());
        }

        @ToString
        @Getter
        @NoArgsConstructor
        public class WebNoticeDTO {
            private Integer noticeId;
            private String noticeTypeAndTitle;
            private LocalDate noticeCreatedAt;

            public WebNoticeDTO(Notice notice) {
                this.noticeId = notice.getId();
                this.noticeTypeAndTitle = "[" + notice.getNoticeType() + "] " + notice.getNoticeTitle();
                this.noticeCreatedAt = notice.getNoticeCreatedAt().toLocalDateTime().toLocalDate();
            }
        }
    }
}