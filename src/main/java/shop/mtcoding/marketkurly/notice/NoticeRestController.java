package shop.mtcoding.marketkurly.notice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.notice.NoticeResponse.NoticeMainDTO;

@RestController
@RequiredArgsConstructor
public class NoticeRestController {

    private final NoticeService noticeService;

    @GetMapping("/api/users/notices")
    public ResponseEntity<?> 공지목록() {
        NoticeMainDTO dto = noticeService.공지목록();
        return ResponseEntity.ok().body(ApiUtils.success(dto));
    }
}
