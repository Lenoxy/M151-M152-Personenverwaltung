import {AddressDto} from "@/mixins/person/dto/address.dto";

export interface EditSelfPersonDto{
    firstname: string;
    lastname: string;
    email: string;
    phonenumber: string;
    address: AddressDto;

    // Only for admins
    position: string;
    username: string;

}
