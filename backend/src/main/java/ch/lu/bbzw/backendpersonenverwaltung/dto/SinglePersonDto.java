package ch.lu.bbzw.backendpersonenverwaltung.dto;

public class SinglePersonDto{
    int id;
    String firstname;
    String lastname;
    String email;
    AddressDto address;
    String phonenumber;
    String position;
    boolean isAdmin;
    String username;
    // String password; TODO: not in dto but in entity
}
