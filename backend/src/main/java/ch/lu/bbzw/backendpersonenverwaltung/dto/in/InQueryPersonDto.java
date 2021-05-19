package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import lombok.Getter;

@Getter
public class InQueryPersonDto{
    String id;
    String firstname;
    String lastname;
    String email;

    public InQueryPersonDto(String id, String firstname, String lastname, String email){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
