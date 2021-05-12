import {AddressDto} from "@/mixins/person/dto/address.dto";

export interface EditSelfPersonDto{
    id: string;
    firstname: string;
    lastname: string;
    address: AddressDto;
}