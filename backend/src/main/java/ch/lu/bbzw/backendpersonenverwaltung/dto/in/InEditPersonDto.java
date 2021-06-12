package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutValidationAnswerDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class InEditPersonDto{
    String firstname;
    String lastname;
    String email;
    InAddressDto address;
    String phonenumber;
    String position;
    String username;
    boolean admin;

    public PersonEntity toEntity(PersonEntity personEntity){
        return new PersonEntity(
                personEntity.getId(),
                firstname,
                lastname,
                email,
                address.toEntity(),
                phonenumber,
                position,
                admin,
                username,
                personEntity.getPassword()
        );
    }
}
