package shop.mtcoding.marketkurly.adminquestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestionResponse.AdminQuestionDetailDTO;
import shop.mtcoding.marketkurly.adminquestion.AdminQuestionResponse.AdminQuestionListDTO;
import shop.mtcoding.marketkurly.user.User;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminQuestionController {

    private final AdminQuestionService adminQuestionService;
    private final HttpSession session;

    @GetMapping("/admin/question")
    public String 관리자문의목록(HttpServletRequest request) {

        System.out.println("문의 컨트롤러");
        AdminQuestionListDTO dto = adminQuestionService.관리자문의목록();

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        Boolean isAdmin = false;
        if (user.getRole().toString().equals("ADMIN")) {
            isAdmin = true;
        }

        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("anwseredAQ", dto.getAdminQuestionAnsweredDTOs());
        request.setAttribute("noAnwseredAQ", dto.getAdminQuestionWaitingDTOs());

        return "admin/questionList";
    }

    @GetMapping("/admin/question/detail/{questionId}")
    public String 관리자문의상세(@PathVariable Integer questionId, HttpServletRequest request) {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        Boolean isAdmin = false;
        if (user.getRole().toString().equals("ADMIN")) {
            isAdmin = true;
        }

        request.setAttribute("isAdmin", isAdmin);
        AdminQuestionDetailDTO dto = adminQuestionService.관리자문의상세(questionId);
        request.setAttribute("adminQuestionDTO", dto.getAdminQuestionDTO());
        request.setAttribute("areplies", dto.getAdminReplyDTO());
        return "admin/questionDetail";
    }

}
