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
    boolean isAdmin;

    public PersonEntity toEntity(){
        return new PersonEntity(
                null,
                firstname,
                lastname,
                email,
                address.toEntity(),
                phonenumber,
                position,
                isAdmin,
                null
        );
    }

    public Set<OutValidationAnswerDto> validateSelf(){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = new HashSet<>();



        return validationAnswerDtoSet;
    }

}
