import axios from "axios";
import {LoginDto} from "@/mixins/auth/dto/login.dto";
import {RegisterDto} from "@/mixins/auth/dto/register.dto";
import {VerifyPasswordDto} from "@/mixins/auth/dto/verify.password.dto";
import {ResetPasswordDto} from "@/mixins/auth/dto/reset.password.dto";

export default {
    methods: {
        async login(login: LoginDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/login", {login}, {withCredentials: true});
        },
        async register(register: RegisterDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/register", {register}, {withCredentials: true});

        },
        async verifyPassword(verify: VerifyPasswordDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/verfy-password", {verify}, {withCredentials: true});

        },
        async resetPassword(reset: ResetPasswordDto): Promise<void> {
            await axios.put("http://localhost:8081/auth/reset-password", {reset}, {withCredentials: true});
        }
    }
}