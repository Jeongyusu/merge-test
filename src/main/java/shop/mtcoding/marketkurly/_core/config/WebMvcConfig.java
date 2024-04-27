package shop.mtcoding.marketkurly._core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import shop.mtcoding.marketkurly._core.interceptor.AdminInterceptor;
import shop.mtcoding.marketkurly._core.interceptor.SellerInterceptor;
import shop.mtcoding.marketkurly._core.interceptor.UserInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

        @Value("${file.path}")
        private String filePath; // ./images/

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                WebMvcConfigurer.super.addResourceHandlers(registry);

                // 1. 절대경로 file:///c:/upload/
                // 2. 상대경로 file:./upload/
                registry
                                .addResourceHandler("/images/**")
                                .addResourceLocations("file:" + filePath)
                                .setCachePeriod(60 * 60) // 초 단위 => 한시간
                                .resourceChain(true)
                                .addResolver(new PathResourceResolver());

                registry.addResourceHandler("/assets/**")
                                .addResourceLocations("classpath:/static/assets/");
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new UserInterceptor())
                                // addPathPatterns( "해당 주소로 갈때" )
                                .addPathPatterns("/api/users/**")
                                .excludePathPatterns("/api/users/login", "/api/users/join");
                // .excludePathPatterns ( "해당주소로 갈때는 예외적 허용" )
                // .excludePathPatterns("/board/{id:[0-9]+}");

                registry.addInterceptor(new SellerInterceptor())
                                // addPathPatterns( "해당 주소로 갈때" )
                                .addPathPatterns("/seller/**");
                // .excludePathPatterns ( "해당주소로 갈때는 예외적 허용" )
                // .excludePathPatterns("/board/{id:[0-9]+}");

                registry.addInterceptor(new AdminInterceptor())
                                // addPathPatterns( "해당 주소로 갈때" )
                                .addPathPatterns("/admin/**");
                // .excludePathPatterns ( "해당주소로 갈때는 예외적 허용" )
                // .excludePathPatterns("/board/{id:[0-9]+}");
        }
}