package ch.lu.bbzw.backendpersonenverwaltung.entity;

import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.QueryPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.SinglePersonDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public AddressEntity getAddress(){
        return address;
    }

    public void setAddress(AddressEntity address){
        this.address = address;
    }

    public String getPhonenumber(){
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public void setAdmin(boolean admin){
        isAdmin = admin;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public QueryPersonDto toQueryPersonDto(){
        return new QueryPersonDto(
                id,
                firstname,
                lastname,
                email
        );
    }

    public SinglePersonDto toSinglePersonDto(){
        return new SinglePersonDto(
                id,
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
