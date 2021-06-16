package ch.lu.bbzw.backendpersonenverwaltung.config;

import ch.lu.bbzw.backendpersonenverwaltung.security.RolesInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    private final RolesInterceptor rolesInterceptor;

    @Autowired
    public InterceptorConfig(RolesInterceptor rolesInterceptor){
        this.rolesInterceptor = rolesInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(rolesInterceptor)
                .excludePathPatterns("/auth/check-username")
                .excludePathPatterns("/auth/register")
                .excludePathPatterns("/auth/verify-password");
    }

}
