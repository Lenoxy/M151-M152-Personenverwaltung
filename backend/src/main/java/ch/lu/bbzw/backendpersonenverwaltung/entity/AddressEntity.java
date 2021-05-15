package ch.lu.bbzw.backendpersonenverwaltung.entity;

import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class AddressEntity{

    @Field
    private String street;
    @Field
    private String number;
    @Field
    private String zipcode;
    @Field
    private String city;

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
