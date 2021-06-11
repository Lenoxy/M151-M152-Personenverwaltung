import axios from "axios";
import {QueryPersonDto} from "@/mixins/person/dto/query.person.dto";
import {GetPersonDto} from "@/mixins/person/dto/get.person.dto";
import {EditPersonDto} from "@/mixins/person/dto/edit.person.dto";
import {EditSelfPersonDto} from "@/mixins/person/dto/edit.self.person.dto";
import {CreatePersonDto} from "@/mixins/person/dto/create.person.dto";
import store from '@/store'

export default {
    methods: {
        async getPersonById(id: string): Promise<GetPersonDto> {
            return (await axios.get(process.env.VUE_APP_BACKEND + "person/" + id, {
                headers: {
                    'Authorization': store.getters.getJwt,
                }
            })).data;
        },
        async removePerson(id: string): Promise<void> {
            await axios.delete(process.env.VUE_APP_BACKEND + "person/" + id);
        },
        async editPerson(id: string, person: EditPersonDto): Promise<string[]> {
            return (await axios.put(process.env.VUE_APP_BACKEND + "person/" + id, person, {
                headers: {
                    'Authorization': store.getters.getJwt,
                }
            })).data;
        },
        async createPerson(person: CreatePersonDto): Promise<string[]> {
            return (await axios.post(process.env.VUE_APP_BACKEND + "person/", person, {
                headers: {
                    'Authorization': store.getters.getJwt,
                }
            })).data;
        },
        async editSelf(person: EditSelfPersonDto): Promise<void> {
            await axios.put(process.env.VUE_APP_BACKEND + "person/self", person);
        },
        async queryAll(): Promise<QueryPersonDto[]>{
            return (await axios.get(process.env.VUE_APP_BACKEND + "person/query/", {
                headers: {
                    'Authorization': store.getters.getJwt,
                }
            })).data;
        },
        async getQuery(property: string, value: string): Promise<QueryPersonDto[]> {
            return (await axios.get(process.env.VUE_APP_BACKEND + "person/query/" + property + "/" + value, {
                headers: {
                    'Authorization': store.getters.getJwt,
                }
            })).data;
        },

    }
}
