import axios from "axios";
import {CheckUserDto} from "@/mixins/auth/dto/check.user.dto";
import {RegisterDto} from "@/mixins/auth/dto/register.dto";
import {VerifyPasswordDto} from "@/mixins/auth/dto/verify.password.dto";
import {ResetPasswordDto} from "@/mixins/auth/dto/reset.password.dto";

export default {
    methods: {
        async checkUser(checkUserDto: CheckUserDto): Promise<void> {
            await axios.post("http://localhost:8081/auth/check-username", {checkUserDto}, {withCredentials: true});
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
