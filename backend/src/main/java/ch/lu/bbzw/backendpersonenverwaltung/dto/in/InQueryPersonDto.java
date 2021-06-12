package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import lombok.Getter;

@Getter
public class InQueryPersonDto{
    String id;
    String name;
    String email;
    boolean admin;

    public InQueryPersonDto(String id, String firstname, String lastname, String email, boolean admin){
        this.id = id;
        this.name = firstname + " " + lastname;
        this.email = email;
        this.admin = admin;
    }
}
