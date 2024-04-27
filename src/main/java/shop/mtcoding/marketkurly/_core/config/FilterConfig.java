package shop.mtcoding.marketkurly._core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shop.mtcoding.marketkurly._core.filter.JwtAuthorizationFilter;

@Configuration
public class FilterConfig {
    @Bean
    FilterRegistrationBean<JwtAuthorizationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthorizationFilter());
        bean.addUrlPatterns("/api/users/*");
        bean.addUrlPatterns("/api/reviews/*");
        bean.addUrlPatterns("/api/carts/*");
        bean.addUrlPatterns("/seller/*");
        bean.addUrlPatterns("/admin/*");
        bean.addUrlPatterns("/notice/*");
        bean.setOrder(0); // 낮은 번호부터 실행됨
        return bean;
    }

}
