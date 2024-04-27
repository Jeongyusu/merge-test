package shop.mtcoding.marketkurly.notice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.notice.NoticeRequest.NoticeSaveDTO;
import shop.mtcoding.marketkurly.notice.NoticeResponse.NoticeMainDTO;
import shop.mtcoding.marketkurly.notice.NoticeResponse.WebNoticeMainDTO;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeJPARepository noticeJPARepository;

    public NoticeMainDTO 공지목록() {
        List<Notice> notices = noticeJPARepository.findAll();
        return new NoticeResponse.NoticeMainDTO(notices);
    }

    public WebNoticeMainDTO 웹공지목록() {
        List<Notice> notices = noticeJPARepository.findAll();
        return new NoticeResponse.WebNoticeMainDTO(notices);
    }

    public void 공지등록(NoticeSaveDTO noticeSaveDTO) {
        Notice notice = Notice.builder()
                .noticeType(noticeSaveDTO.getNoticeType())
                .noticeTitle(noticeSaveDTO.getNoticeTitle())
                .noticeContent(noticeSaveDTO.getNoticeContent()).build();
        noticeJPARepository.save(notice);
    }

    public Notice 공지상세보기(Integer noticeId) {
        Notice notice = noticeJPARepository.findById(noticeId).get();
        return notice;
    }
}