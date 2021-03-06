package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import lombok.Getter;

@Getter
public class InCreatePersonDto{
    String firstname;
    String lastname;
    String email;
    InAddressDto address;
    String phonenumber;
    String position;
    boolean admin;
    String username;

    public InCreatePersonDto(String firstname, String lastname, String email, InAddressDto address, String phonenumber, String position, boolean admin, String username){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.position = position;
        this.admin = admin;
        this.username = username;
    }

    public InCreatePersonDto(){
    }

    public PersonEntity toEntity(){
        return new PersonEntity(
                null,
                firstname,
                lastname,
                email,
                address != null ? address.toEntity() : null,
                phonenumber,
                position,
                admin,
                username
        );
    }
}
