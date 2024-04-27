package shop.mtcoding.marketkurly.adminquestion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception400;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestionResponse.AdminQuestionDetailDTO;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestionResponse.AdminQuestionListDTO;

@RequiredArgsConstructor
@Service
public class AdminQuestionService {

    private final AdminQuestionJPARepository adminQuestionJPARepository;

    public AdminQuestionListDTO 관리자문의목록() {
        System.out.println("문의 서비스");
        List<AdminQuestion> adminQuestions = adminQuestionJPARepository.findAll();

        System.out.println("문의 : 받은 리스트 " + adminQuestions);
        return new AdminQuestionListDTO(adminQuestions);
    }

    public AdminQuestionDetailDTO 관리자문의상세(Integer questionId) {
        Optional<AdminQuestion> adminQuestionOP = adminQuestionJPARepository.findById(questionId);
        if (adminQuestionOP.isEmpty()) {
            throw new Exception400("존재하지 않는 문의입니다.");
        }
        return new AdminQuestionDetailDTO(adminQuestionOP.get());
    }

}
