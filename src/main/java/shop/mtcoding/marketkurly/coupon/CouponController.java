package shop.mtcoding.marketkurly.coupon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly.coupon.CouponRequest.CouponSaveDTO;
import shop.mtcoding.marketkurly.coupon.CouponResponse.CouponListDTO;
import shop.mtcoding.marketkurly.user.User;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;
    private final HttpSession session;

    @GetMapping("/admin/coupon")
    public String 쿠폰목록(HttpServletRequest request) {
        CouponListDTO dto = couponService.쿠폰목록();

        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        Boolean isAdmin = false;

        if (user.getRole().toString().equals("ADMIN")) {
            isAdmin = true;
        }
        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("eCouponListDTOs", dto.getECouponListDTOs());
        request.setAttribute("couponListDTOs", dto.getCouponListDTOs());
        return "admin/couponList";
    }

    @GetMapping("/admin/coupon/detail/{CouponId}")
    public String 쿠폰상세(@PathVariable Integer CouponId, HttpServletRequest request) {
        Coupon coupon = couponService.쿠폰상세(CouponId);
        request.setAttribute("coupon", coupon);
        return "admin/couponDetail";
    }

    @GetMapping("/admin/coupon/save")
    public String 쿠폰등록페이지(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        log.info("sessionUser number : " + user.getId());
        Boolean isAdmin = false;
        if (user.getRole().toString().equals("ADMIN")) {
            isAdmin = true;
        }
        request.setAttribute("isAdmin", isAdmin);
        return "admin/couponSave";
    }

    @PostMapping("/admin/coupon/save")
    public String 쿠폰생성(CouponSaveDTO couponSaveDTO) {
        couponService.쿠폰생성(couponSaveDTO);
        return "redirect:/admin/coupon";
    }

    @GetMapping("/admin/coupon/delete/{couponId}")
    public void 쿠폰삭제(@PathVariable Integer couponId) {
        System.out.println("테스트 : 쿠폰 삭제 요청 CouponId" + couponId);
        couponService.쿠폰삭제(couponId);
    }
}
