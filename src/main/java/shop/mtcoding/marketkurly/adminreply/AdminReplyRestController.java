package shop.mtcoding.marketkurly.adminreply;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;
import shop.mtcoding.marketkurly.adminreply.AdminReplyRequest.AReplySaveDTO;

@RestController
@RequiredArgsConstructor
public class AdminReplyRestController {

    private final AdminReplyService adminReplyService;

    @PostMapping("/admin/question/reply/save")
    public ResponseEntity<?> 답변저장(@RequestBody AReplySaveDTO aReplySaveDTO) {
        adminReplyService.답변저장(aReplySaveDTO);
        return ResponseEntity.ok().body(ApiUtils.success(aReplySaveDTO));
    }

}
