import axios from "axios";
import {QueryPersonDto} from "@/mixins/person/dto/query.person.dto";
import {GetPersonDto} from "@/mixins/person/dto/get.person.dto";
import {EditPersonDto} from "@/mixins/person/dto/edit.person.dto";
import {EditSelfPersonDto} from "@/mixins/person/dto/edit.self.person.dto";
import {CreatePersonDto} from "@/mixins/person/dto/create.person.dto";

export default {
    methods: {
        async getPersonById(id: string): Promise<GetPersonDto>{
            const response = await axios.get("http://localhost:8081/person/" + id);
            return response.data;
        },
        async removePerson(id: string): Promise<void>{
          await axios.delete("http://localhost:8081/person/" + id);
        },
        async editPerson(id: string, person: EditPersonDto): Promise<void>{
          await axios.put("http://localhost:8081/person/" + id, {person});
        },
        async createPerson(person: CreatePersonDto): Promise<void>{
            await axios.post("http://localhost:8081/person/", {person});
        },
        async editSelf(person: EditSelfPersonDto): Promise<void>{
            await axios.put("http://localhost:8081/person/self", {person});
        },
        async getQuery(property: string, value: string): Promise<QueryPersonDto[]>{
            const response = await axios.get("http://localhost:8081/person/query/" + property + "/" + value, {headers: {
                    'Authorisation': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpcy1hZG1pbiI6ZmFsc2UsImlzcyI6Im0xNTEtbTE1Mi1wZXJzb25lbnZlcndhbHR1bmciLCJ1c2VyIjoibGVvLnNjaGVyZXIifQ._zi_7VDWcdmkG_o3aIe85E75Gq_zDWvBbXk75hBjMxY'
                }
            });
            return response.data;
        },

    }
}
