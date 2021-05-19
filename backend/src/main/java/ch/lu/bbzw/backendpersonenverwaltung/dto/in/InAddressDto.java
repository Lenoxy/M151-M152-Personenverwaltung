package ch.lu.bbzw.backendpersonenverwaltung.dto.in;

import ch.lu.bbzw.backendpersonenverwaltung.entity.AddressEntity;
import lombok.Getter;

@Getter
public class InAddressDto{
    String street;
    String number;
    String zipcode;
    String city;

    public InAddressDto(String street, String number, String zipcode, String city){
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
