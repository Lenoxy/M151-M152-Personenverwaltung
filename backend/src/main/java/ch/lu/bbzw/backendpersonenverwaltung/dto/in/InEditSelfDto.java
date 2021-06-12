package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import lombok.Getter;

@Getter
public class InEditSelfDto{
    String firstname;
    String lastname;
    String email;
    String phonenumber;
    InAddressDto address;

    // Only for admins
    String position;
    String username;

    public PersonEntity toEntityWithUserPermission(PersonEntity personEntity){
        return new PersonEntity(
                personEntity.getId(),
                firstname,
                lastname,
                email,
                address.toEntity(),
                phonenumber,
                personEntity.getPosition(),
                personEntity.isAdmin(),
                personEntity.getUsername(),
                personEntity.getPassword()
        );
    }

    public PersonEntity toEntityWithAdminPermission(PersonEntity personEntity){
        return new PersonEntity(
                personEntity.getId(),
                firstname,
                lastname,
                email,
                address.toEntity(),
                phonenumber,
                position,
                personEntity.isAdmin(),
                username,
                personEntity.getPassword()
        );
    }
}
