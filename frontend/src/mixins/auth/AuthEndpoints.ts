import axios from "axios";
import {RegisterDto} from "@/mixins/auth/dto/register.dto";
import {VerifyPasswordDto} from "@/mixins/auth/dto/verify.password.dto";
import {ResetPasswordDto} from "@/mixins/auth/dto/reset.password.dto";
import {LoginResponseDto} from "@/mixins/auth/dto/login.response.dto";

export default {
    methods: {
        async checkUser(username: string): Promise<LoginResponseDto> {
            const answer = await axios.post<LoginResponseDto>("http://localhost:8081/auth/check-username", username, {
                headers: {
                    'Content-Type': 'text/plain'
                }
            });
            return answer.data;
        },
        async register(registerDto: RegisterDto): Promise<string> {
            const answer = await axios.post<string>("http://localhost:8081/auth/register", registerDto, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            return answer.data;
        },
        async verifyPassword(verifyPasswordDto: VerifyPasswordDto): Promise<string> {
            return (await axios.post<string>("http://localhost:8081/auth/verify-password", verifyPasswordDto, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
            ).data;
        },
        // TODO
        async resetPassword(resetPasswordDto: ResetPasswordDto): Promise<string> {
            return await axios.put("http://localhost:8081/auth/reset-password", {resetPasswordDto});
        }
    }
}
