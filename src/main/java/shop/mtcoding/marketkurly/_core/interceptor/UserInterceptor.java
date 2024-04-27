package shop.mtcoding.marketkurly._core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly._core.errors.exception.Exception404;
import shop.mtcoding.marketkurly._core.utils.Script;

import shop.mtcoding.marketkurly.user.User;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    // return값이 boolean인 이유
    // ture 이면 컨트롤러 메서드 진입
    // false 이면 요청이 종료됨
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // preHandle에 인증 안된 사용자는 팅
        log.info("UserInterceptor PreHandle");
        System.out.println("UserInterceptor PreHandle");
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 요청 주소에 /api/가 있으면 응답을 JSON으로 해야한다. << api 요청에 대한 분기가 필요함
        // request.getRequestURI() << request의 요청주소를 찾아주는 메서드
        String startEndPoint = request.getRequestURI().split("/")[1];

        // api요청
        if (startEndPoint.equals("api")) {

            // 인증이 X
            if (sessionUser == null) {
                System.out.println("유저인터셉터 : API 인증없음");
                response.setHeader("Content-Type", "text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                Exception404 error = new Exception404("인증되지 않은 사용자 입니다.");
                String responseBody = new ObjectMapper().writeValueAsString(error);
                out.println(responseBody);
                return false;
            }
            if (!sessionUser.getRole().toString().equals("NORMAL")) {
                System.out.println("유저인터셉터 : API 권한없음");
                response.setHeader("Content-Type", "text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                Exception404 error = new Exception404("권한이 없습니다.");
                String responseBody = new ObjectMapper().writeValueAsString(error);
                out.println(responseBody);
                return false;
            }

        }

        // 웹 요청
        else {

            // 웹 요청에 인증이 X
            if (sessionUser == null) {
                System.out.println("유저인터셉터 : 웹 인증없음");
                response.setHeader("Content-Type", "text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println(Script.href("redirect:/login", "인증이 필요합니다."));
                return false;
            }
            // 웹 요청에 권한이 X
            if (!sessionUser.getRole().toString().equals("NORMAL")) {
                System.out.println("유저인터셉터 : 웹 권한없음");
                response.setHeader("Content-Type", "text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println(Script.back("권한이 없습니다."));
                return false;
            }
        }

        return true;
    }

}
