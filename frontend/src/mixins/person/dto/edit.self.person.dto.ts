import {AddressDto} from "@/mixins/person/dto/address.dto";

export interface EditSelfPersonDto{
    firstname: string;
    lastname: string;
    address: AddressDto;
}
