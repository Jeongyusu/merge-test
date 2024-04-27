package shop.mtcoding.marketkurly.productquestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly.productquestion.ProductQuestionResponse.ProductQuestionDetailDTO;
import shop.mtcoding.marketkurly.productquestion.ProductQuestionResponse.ProductQuestionListDTO;
import shop.mtcoding.marketkurly.user.User;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductQuestionController {

    private final ProductQuestionService productQuestionService;
    private final HttpSession session;

    @GetMapping("/seller/question")
    public String 판매자문의목록(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        ProductQuestionListDTO dto = productQuestionService.판매자문의목록(user.getId());

        Boolean isSeller = false;
        if (user.getRole().toString().equals("SELLER")) {
            isSeller = true;
        }

        request.setAttribute("isSeller", isSeller);
        request.setAttribute("anwseredPQ", dto.getProductQuestionAnsweredDTOs());
        request.setAttribute("noAnwseredPQ", dto.getProductQuestionWaitingDTOs());

        return "seller/questionList";
    }

    @GetMapping("/seller/question/detail/{questionId}")
    public String 판매자문의상세(@PathVariable Integer questionId, HttpServletRequest request) {

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());

        Boolean isSeller = false;
        if (user.getRole().toString().equals("SELLER")) {
            isSeller = true;
        }

        request.setAttribute("isSeller", isSeller);
        ProductQuestionDetailDTO dto = productQuestionService.판매자문의상세(questionId);
        request.setAttribute("productQuestionDTO", dto.getProductQuestionDTO());
        request.setAttribute("preplies", dto.getProductReplyDTO());
        return "seller/questionDetail";
    }
}
