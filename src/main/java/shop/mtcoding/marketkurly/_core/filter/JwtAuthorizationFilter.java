package shop.mtcoding.marketkurly._core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import shop.mtcoding.marketkurly._core.errors.exception.Exception401;
import shop.mtcoding.marketkurly._core.utils.JwtTokenUtils;
import shop.mtcoding.marketkurly.user.Role;
import shop.mtcoding.marketkurly.user.User;

public class JwtAuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String startEndPoint = request.getRequestURI().split("/")[1];
        String requestUri = request.getRequestURI();
        String jwt = null;

        if (requestUri.equals("/users/login")) {
            System.out.println("/users/login 요청 건너뜀");
            chain.doFilter(request, response);
            return;
        }
        if (requestUri.equals("/users/join")) {
            System.out.println("/users/join 요청 건너뜀");
            chain.doFilter(request, response);
            return;
        }
        if (requestUri.equals("/api/users/join")) {
            System.out.println("/api/users/join 요청 건너뜀");
            chain.doFilter(request, response);
            return;
        }
        if (requestUri.equals("/api/users/login")) {
            System.out.println("/api/users/login 요청 건너뜀");
            chain.doFilter(request, response);
            return;
        }

        boolean foundTokenCookie = false;
        if (startEndPoint.equals("api")) {
            // JwtAuthorizationFilter 에서는 쿠키에 넣어야함
            jwt = request.getHeader("Authorization");
            if (jwt == null || jwt.isEmpty()) {
                System.out.println("토큰이 없습니다");
                onError(response, "토큰이 없습니다");
                return;
            }
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        // Found the "jwt" cookie
                        jwt = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        foundTokenCookie = true;
                        break;
                    }
                }
            }
            if (!foundTokenCookie) {
                response.setHeader("Location", "/login");
                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); // 302 상태 코드
                return;
            }
        }

        try {
            DecodedJWT decodedJWT = JwtTokenUtils.verify(jwt);
            int id = decodedJWT.getClaim("id").asInt();
            String userEmail = decodedJWT.getClaim("userEmail").asString();
            String role = decodedJWT.getClaim("role").asString();
            Role userRole = null;

            if (role.equals("NORMAL")) {
                userRole = Role.NORMAL;
                System.out.println("토큰 : " + userRole + " 담김");
            }
            if (role.equals("SELLER")) {
                userRole = Role.SELLER;
                System.out.println("토큰 : " + userRole + " 담김");
            }
            if (role.equals("ADMIN")) {
                userRole = Role.ADMIN;
                System.out.println("토큰 : " + userRole + " 담김");
            }

            User sessionUser = User.builder().id(id).userEmail(userEmail).role(userRole).build();
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", sessionUser);

            chain.doFilter(request, response);
        } catch (SignatureVerificationException | JWTDecodeException e1) {
            onError(response, "토큰 검증 실패");
        } catch (TokenExpiredException e2) {
            onError(response, "토큰 시간 만료");
        }
    }

    private void onError(HttpServletResponse response, String msg) {
        Exception401 e401 = new Exception401(msg);

        try {
            String body = new ObjectMapper().writeValueAsString(e401.body());
            response.setStatus(e401.status().value());
            // response.setHeader("Content-Type", "application/json; charset=utf-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter out = response.getWriter();
            out.println(body);
        } catch (Exception e) {
            System.out.println("파싱 에러가 날 수 없음");
        }
    }
}
