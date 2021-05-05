package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.dto.LoginResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController{

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody String username){
        return null;
    }

    @PostMapping("/register")
    public String register(@RequestBody String newPassword){
        return null;
    }

    @PostMapping("/verify-password")
    public String verifyPassword(@RequestBody String password){
        return null;
    }

    @PostMapping("reset-password")
    public boolean resetPassword(@RequestBody String oldPassword, @RequestBody String newPassword){
        return false;
    }



}
