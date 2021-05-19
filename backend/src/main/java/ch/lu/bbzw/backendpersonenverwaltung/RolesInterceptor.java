package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RolesInterceptor implements HandlerInterceptor{
    private final JwtService jwtService;

    @Autowired
    public RolesInterceptor(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("preHandle");
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(! jwtService.isJwtValid(jwt)){
            throw new RuntimeException("Authentication failed");
        }else{
            return true;
        }
        // True to proceed and false to stop the method call
    }
}
