package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import lombok.Getter;

@Getter
public class ChangePasswordDto{
    private String newPassword;
    private String oldPassword;
}
