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
    AddressEntity address;
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

    public PersonEntity(
            String id,
            String firstname,
            String lastname,
            String email,
            AddressEntity address,
            String phonenumber,
            String position,
            boolean isAdmin,
            String username
    ){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.position = position;
        this.isAdmin = isAdmin;
        this.username = username;
    }
}
