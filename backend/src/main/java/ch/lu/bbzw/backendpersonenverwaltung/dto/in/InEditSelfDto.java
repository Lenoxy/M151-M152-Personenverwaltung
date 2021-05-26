package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import lombok.Getter;

@Getter
public class InEditSelfDto{
    String firstname;
    String lastname;
    InAddressDto address;
}
