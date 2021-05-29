import axios from "axios";
import {RegisterDto} from "@/mixins/auth/dto/register.dto";
import {VerifyPasswordDto} from "@/mixins/auth/dto/verify.password.dto";
import {ResetPasswordDto} from "@/mixins/auth/dto/reset.password.dto";
import {LoginResponseDto} from "@/mixins/auth/dto/login.response.dto";

export default {
    methods: {
        async checkUser(username: string): Promise<LoginResponseDto> {
           return await axios.post("http://localhost:8081/auth/check-username", username, {withCredentials: true});
        },
        async register(registerDto: RegisterDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/register", {registerDto}, {withCredentials: true});

        },
        async verifyPassword(verifyPasswordDto: VerifyPasswordDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/verfy-password", {verifyPasswordDto}, {withCredentials: true});

        },
        async resetPassword(resetPasswordDto: ResetPasswordDto): Promise<void> {
            await axios.put("http://localhost:8081/auth/reset-password", {resetPasswordDto}, {withCredentials: true});
        }
    }
}
