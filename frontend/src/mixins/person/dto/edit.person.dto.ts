import {AddressDto} from "@/mixins/person/dto/address.dto";
export interface EditPersonDto {
    id: string;
    firstname: string;
    lastname: string;
    email: string;
    address: AddressDto;
    phonenumber: string;
    position: string;
    isAdmin: boolean;
}
