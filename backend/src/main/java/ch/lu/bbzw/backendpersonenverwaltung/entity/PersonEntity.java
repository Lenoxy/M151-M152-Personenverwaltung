package ch.lu.bbzw.backendpersonenverwaltung.entity;

import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InQueryPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InCreatePersonDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class PersonEntity{
    @Id
    private String id;
    @Field
    private String firstname;
    @Field
    private String lastname;
    @Field
    @Indexed(unique=true)
    private String email;
    @Field
    private AddressEntity address;
    @Field
    private String phonenumber;
    @Field
    private String position;
    @Field
    private boolean isAdmin;
    @Field
    @Indexed(unique=true)
    private String username;
    @Field
    private String password;

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

    public PersonEntity(
            String id,
            String firstname,
            String lastname,
            String email,
            AddressEntity address,
            String phonenumber,
            String position,
            boolean isAdmin,
            String username,
            String password
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
        this.password = password;
    }

    public PersonEntity(){}

    public InQueryPersonDto toQueryPersonDto(){
        return new InQueryPersonDto(
                id,
                firstname,
                lastname,
                email,
                isAdmin
        );
    }

    public InCreatePersonDto toCreatePersonDto(){
        return new InCreatePersonDto(
                firstname,
                lastname,
                email,
                address.toDto(),
                phonenumber,
                position,
                isAdmin,
                username
        );
    }
}
