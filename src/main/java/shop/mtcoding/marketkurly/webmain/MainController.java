package shop.mtcoding.marketkurly.webmain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly.category.Category;
import shop.mtcoding.marketkurly.category.CategoryService;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProduct;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProductService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final WaitingProductService waitingProductService;
    private final HttpSession session;

    @GetMapping({ "/", "/login" })
    public String main() {
        System.out.println("테스트 : 로그인 호출");
        return "loginForm";
    }

    @GetMapping("/join")
    public String join() {
        System.out.println("테스트 : 조인 호출");
        return "joinForm";
    }

    @GetMapping("/admin")
    public String 대기상품전체(HttpServletRequest request) {
        log.info("admin 대기상품 페이지");
        List<WaitingProduct> waitingproducts = waitingProductService.대기상품전체();
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        Boolean isAdmin = false;
        if (user.getRole().toString().equals("ADMIN")) {
            isAdmin = true;
        }
        request.setAttribute("allWaitingProduct", waitingproducts);
        request.setAttribute("isAdmin", isAdmin);
        return "admin/adminMain";
    }

    // productController에 있음
    // @GetMapping("/seller/product")
    // public String sellerProduct() {
    // System.out.println("테스트 : sellerProduct 호출");
    // return "seller/sellerProduct";
    // }

}
