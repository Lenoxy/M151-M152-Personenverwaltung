import {AddressDto} from "@/mixins/person/dto/address.dto";
export interface EditPersonDto {
    firstname: string;
    lastname: string;
    email: string;
    address: AddressDto;
    phonenumber: string;
    position: string;
    username: string;
    admin: boolean;
}
