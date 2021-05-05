package ch.lu.bbzw.backendpersonenverwaltung.dto;

import java.util.Optional;

public class SinglePersonDto{
    String id;
    String firstname;
    String lastname;
    String email;
    AddressDto address;
    Optional<String> phonenumber;
    String position;
    boolean isAdmin;
    String username;
    // String password; TODO: not in dto but in entity
}
