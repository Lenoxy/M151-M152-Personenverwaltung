package ch.lu.bbzw.backendpersonenverwaltung.controller.dto;

import ch.lu.bbzw.backendpersonenverwaltung.entity.AddressEntity;

public class AddressDto{
    String street;
    String number;
    String zipcode;
    String city;

    public AddressDto(String street, String number, String zipcode, String city){
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
    }

    public AddressEntity toEntity(){
        return new AddressEntity(
                street,
                number,
                zipcode,
                city
        );
    }
}
