import axios from "axios";
import {QueryPersonDto} from "@/mixins/person/dto/query.person.dto";
import {GetPersonDto} from "@/mixins/person/dto/get.person.dto";
import {EditPersonDto} from "@/mixins/person/dto/edit.person.dto";
import {EditSelfPersonDto} from "@/mixins/person/dto/edit.self.person.dto";
import {CreatePersonDto} from "@/mixins/person/dto/create.person.dto";

export default {
    methods: {
        async getPersonById(id: string): Promise<GetPersonDto>{
            const response = await axios.get("http://localhost:8081/person/" + id, {withCredentials: true});
            return response.data;
        },
        async removePerson(id: string): Promise<void>{
          await axios.delete("http://localhost:8081/person/" + id, {withCredentials: true});
        },
        async editPerson(id: string, person: EditPersonDto): Promise<void>{
          await axios.put("http://localhost:8081/person/" + id, {person},{withCredentials: true});
        },
        async createPerson(person: CreatePersonDto): Promise<void>{
            await axios.post("http://localhost:8081/person/", {person},{withCredentials: true});
        },
        async editSelf(person: EditSelfPersonDto): Promise<void>{
            await axios.put("http://localhost:8081/person/self", {person},{withCredentials: true});
        },
        async getQuery(property: string, value: string): Promise<QueryPersonDto[]>{
            const response = await axios.put("http://localhost:8081/person/query/" + property + "/" + value, {},{withCredentials: true});
            return response.data;
        },

    }
}
