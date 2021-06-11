package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.dto.in.ChangePasswordDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InLoginDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutLoginResponseDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.service.AuthenticationService;
import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.ProtectedForRole;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController{

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(
            final AuthenticationService authenticationService,
            final JwtService jwtService){
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/check-username")
    public OutLoginResponseDto checkUsername(@RequestBody String username){
        // Checks if the users password has been set
        return authenticationService.checkRegistrationStatus(username);
    }

    @PostMapping("/register")
    public String register(@RequestBody InLoginDto loginDto){
        // When the user has no Password set (initial Login)
        PersonEntity person = authenticationService.initialLogin(loginDto.getUsername(), loginDto.getPassword());

        return jwtService.createJwt(person);
    }

    @PostMapping("/verify-password")
    public String verifyPassword(@RequestBody InLoginDto loginDto){
        // Actually logging in by Username and password
        PersonEntity person = authenticationService.login(loginDto.getUsername(), loginDto.getPassword());
        if(person == null){
            return null;
        }else{
            return jwtService.createJwt(person);
        }

    }

    @ProtectedForRole(UserRole.USER)
    @PutMapping("/reset-password")
    public boolean resetPassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        authenticationService.changePassword(
                jwtService.getUserNameFromClaim(jwt),
                changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword());
        return true;
    }

}
