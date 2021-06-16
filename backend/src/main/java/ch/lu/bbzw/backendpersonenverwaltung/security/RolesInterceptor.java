package ch.lu.bbzw.backendpersonenverwaltung.security;

import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotAuthorizedException;
import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import ch.lu.bbzw.backendpersonenverwaltung.security.ProtectedForRole;
import ch.lu.bbzw.backendpersonenverwaltung.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class RolesInterceptor implements HandlerInterceptor{
    private final JwtService jwtService;

    @Autowired
    public RolesInterceptor(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // Allow preflight
        if(request.getMethod().equals(HttpMethod.OPTIONS.name())){
            return true;
        }

        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwt == null || ! jwtService.isJwtValid(jwt)){
            throw new NotAuthorizedException();
        }else{
            Method method = ((HandlerMethod) handler).getMethod();
            if(method.isAnnotationPresent(ProtectedForRole.class)){
                UserRole role = method.getAnnotation(ProtectedForRole.class).value();
                Boolean admin = jwtService.isAdminFromClaim(jwt);

                if(role == UserRole.ADMIN){
                    return admin;
                }
                if(role == UserRole.USER){
                    return admin != null;
                }
            } // Interceptor returns false if no Annotation is set -> Configure unwanted paths in InterceptorConfig.java
        }
        // True to proceed and false to stop the method call
        return false;
    }
}
