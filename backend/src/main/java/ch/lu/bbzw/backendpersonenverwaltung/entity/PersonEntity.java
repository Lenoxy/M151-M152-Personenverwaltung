package ch.lu.bbzw.backendpersonenverwaltung.entity;

import ch.lu.bbzw.backendpersonenverwaltung.dto.AddressDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class PersonEntity{
    @Id
    String id;
    @Field
    String firstname;
    @Field
    String lastname;
    @Field
    String email;
    @Field
    AddressDto address;
    @Field
    String phonenumber;
    @Field
    String position;
    @Field
    boolean isAdmin;
    @Field
    String username;
    @Field
    String password;
}
