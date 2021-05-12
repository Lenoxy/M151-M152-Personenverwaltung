package ch.lu.bbzw.backendpersonenverwaltung.entity;

import ch.lu.bbzw.backendpersonenverwaltung.dto.AddressDto;
import org.springframework.data.mongodb.core.mapping.Field;

public class AddressEntity{

    @Field
    String street;
    @Field
    String number;
    @Field
    String zipcode;
    @Field
    String city;

    public AddressEntity(String street, String number, String zipcode, String city){
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
    }

    public AddressDto toDto(){
        return new AddressDto(
                street,
                number,
                zipcode,
                city
        );
    }
}
