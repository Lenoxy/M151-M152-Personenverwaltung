package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutLoginResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController{

    @PostMapping("/login")
    public OutLoginResponseDto login(@RequestBody String username){
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

    @PutMapping("reset-password")
    public boolean resetPassword(@RequestBody String oldPassword, @RequestBody String newPassword){
        return false;
    }

}
