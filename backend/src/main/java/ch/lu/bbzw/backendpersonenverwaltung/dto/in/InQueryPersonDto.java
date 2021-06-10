package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import lombok.Getter;

@Getter
public class InQueryPersonDto{
    String id;
    String name;
    String email;
    boolean isAdmin;

    public InQueryPersonDto(String id, String firstname, String lastname, String email, boolean isAdmin){
        this.id = id;
        this.name = firstname + " " + lastname;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
