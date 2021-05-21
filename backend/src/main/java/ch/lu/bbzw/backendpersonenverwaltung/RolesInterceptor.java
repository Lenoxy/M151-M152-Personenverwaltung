package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotAuthorizedException;
import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.ProtectedForRole;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwt == null || ! jwtService.isJwtValid(jwt)){
            throw new NotAuthorizedException();
        }else{
            Method method = ((HandlerMethod) handler).getMethod();
            if(method.isAnnotationPresent(ProtectedForRole.class)){
                UserRole role = method.getAnnotation(ProtectedForRole.class).value();
                Boolean isAdmin = jwtService.isAdminFromClaim(jwt);

                if(isAdmin == null){
                    return false;
                }else if(isAdmin == true){
                    return true;
                }else if (isAdmin == false){
                    return role == UserRole.USER;
                }
            }
        }
        // True to proceed and false to stop the method call
        return false;
    }
}
