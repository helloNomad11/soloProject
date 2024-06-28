package co.kr.mysite.nevergoback.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String path = request.getRequestURI();
                if (path.equals("/login") || path.equals("/logout")) {
                    return true;
                }

                if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
                    response.sendRedirect("/not-found");
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/**");
    }
}
