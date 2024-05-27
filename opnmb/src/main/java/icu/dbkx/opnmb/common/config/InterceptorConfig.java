package icu.dbkx.opnmb.common.config;

import icu.dbkx.opnmb.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/api/user/send_captcha")
//                .excludePathPatterns("/api/user/login")
//                .excludePathPatterns("/api/user/register")
//                .excludePathPatterns("/api/piece/get_thread/**")
//                .excludePathPatterns("/api/piece/get_category/**")
//                .excludePathPatterns("/api/piece/get**")
//                .excludePathPatterns("/api/category/list")
//                .excludePathPatterns("/view/**");
                .addPathPatterns("/api/biscuit/**")
                .addPathPatterns("/api/piece/post")
                .addPathPatterns("/api/piece/reply");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
