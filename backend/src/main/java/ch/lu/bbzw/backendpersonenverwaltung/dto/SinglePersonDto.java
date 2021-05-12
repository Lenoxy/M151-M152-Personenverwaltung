package ch.lu.bbzw.backendpersonenverwaltung.dto;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;

import java.util.Optional;

public class SinglePersonDto{
    String id;
    String firstname;
    String lastname;
    String email;
    AddressDto address;
    String phonenumber;
    String position;
    boolean isAdmin;
    String username;
    // String password; TODO: not in dto but in entity


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

    public AddressDto getAddress(){
        return address;
    }

    public void setAddress(AddressDto address){
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


    public PersonEntity toEntity(){
        return new PersonEntity(
                id,
                firstname,
                lastname,
                email,
                address != null ? address.toEntity() : null,
                phonenumber,
                position,
                isAdmin,
                username
        );
    }
}
