package shop.mtcoding.marketkurly.adminreply;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestion;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestionJPARepository;
import shop.mtcoding.marketkurly.adminreply.AdminReplyRequest.AReplySaveDTO;

@Service
@RequiredArgsConstructor
public class AdminReplyService {

    private final AdminQuestionJPARepository adminQuestionJPARepository;
    private final AdminReplyJPARepository adminReplyJPARepository;

    @Transactional
    public void 답변저장(AReplySaveDTO aReplySaveDTO) {

        AdminQuestion adminQuestion = adminQuestionJPARepository.findById(aReplySaveDTO.getAdminQuestionId()).get();
        AdminReply adminReply = null;
        if (adminQuestion.getAdminReply() == null) {
            adminReply = AdminReply.builder()
                    .adminQuestion(adminQuestion)
                    .aReplyContent(aReplySaveDTO.getReplyContent())
                    .build();
        } else {
            adminReply = AdminReply.builder()
                    .id(adminQuestion.getAdminReply().getId())
                    .adminQuestion(adminQuestion)
                    .aReplyContent(aReplySaveDTO.getReplyContent())
                    .build();
        }
        if (!adminQuestion.getIsAnswered()) {
            adminQuestionJPARepository.updateStateById(aReplySaveDTO.getAdminQuestionId());
        }
        adminReplyJPARepository.save(adminReply);
        adminQuestionJPARepository.saveProductReplyId(adminReply.getId(), adminQuestion.getId());
    }

}
